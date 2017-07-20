package com.example.mukhter.bakingrecipe.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mukhter.bakingrecipe.R;
import com.example.mukhter.bakingrecipe.ui.RecipeStepFragment;
import com.example.mukhter.bakingrecipe.ui.RecipeStepActivity;
import com.example.mukhter.bakingrecipe.model.RecipeCardModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by MUKHTER on 17/06/2017.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.Viewholder> {
    private ArrayList<RecipeCardModel> recipeCardArrayList;
    private ArrayList<RecipeCardModel.RecipeInstructionModel> recipestepArrayList;
    private int[] image = {
            R.drawable.recipes
    };
    private String[] titles = {
            "Recipe"
    };
    Context context;

 boolean mTwoPane;

    public Adapter(Context context, ArrayList<RecipeCardModel> recipeArrayList, ArrayList<RecipeCardModel.RecipeInstructionModel> recipestepArrayList) {
        this.recipeCardArrayList = recipeArrayList;
        this.context = context;
        this.recipestepArrayList = recipestepArrayList;

    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.card_layout, parent, false);
        Viewholder view = new Viewholder(itemView);
        return view;
    }

    @Override
    public void onBindViewHolder(final Viewholder holder, final int position) {
        final Viewholder myHolder = (Viewholder) holder;

        final RecipeCardModel current = recipeCardArrayList.get(position);

        myHolder.retitle.setText(current.title);

        Picasso.with(context).load(current.getImage()).placeholder(image[0])
                .error(R.drawable.errorimage)
                .into(holder.reimage);
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTwoPane) {


                    FragmentManager fragmentManager = ((RecipeStepActivity) context).getSupportFragmentManager();
                    RecipeStepFragment recipeDetailsFragment;
                    recipeDetailsFragment = new RecipeStepFragment();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.item_detail_container, recipeDetailsFragment);
                    transaction.commit();

                } else {
                    RecipeCardModel recipe = recipeCardArrayList.get(position);
                    RecipeCardModel.RecipeInstructionModel recipeInstructionModel = recipestepArrayList.get(position);
                    Intent intent = new Intent(context, RecipeStepActivity.class);
                    intent.putExtra("quantity", recipe.getmIngredients());
                    intent.putExtra("next", recipe.getmInstructions());
                    intent.putExtra("videourl", recipeInstructionModel.getMvideoUrl());
                    context.startActivity(intent);


                }
            }
        });

    }

    @Override
    public int getItemCount() {
        if (recipeCardArrayList == null) {
            return 0;
        } else {
            return recipeCardArrayList.size();
        }
    }


    public class Viewholder extends RecyclerView.ViewHolder {
        ImageView reimage;
        TextView retitle;
        public final View mView;

        public Viewholder(View itemView) {
            super(itemView);
            mView = itemView;
            reimage = (ImageView) itemView.findViewById(R.id.thumbnail);
            retitle = (TextView) itemView.findViewById(R.id.title);

        }


    }


}