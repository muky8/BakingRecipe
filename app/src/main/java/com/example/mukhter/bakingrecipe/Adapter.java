package com.example.mukhter.bakingrecipe;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mukhter.bakingrecipe.model.RecipeCardModel;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by MUKHTER on 17/06/2017.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.Viewholder>   {
 private ArrayList<RecipeCardModel>recipeCardArrayList;

    private int[]image ={
      R.drawable.recipes
    };
    private String[]titles ={
          "Recipe"
    };
    Context context;

    public Adapter(Context context,ArrayList<RecipeCardModel> recipeArrayList) {
        this.recipeCardArrayList = recipeArrayList;
        this.context=context;

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
    public void onBindViewHolder(Viewholder holder, int position) {
        Viewholder myHolder= (Viewholder) holder;

        RecipeCardModel current=recipeCardArrayList.get(position);
        myHolder.retitle.setText(current.title);

        holder.reimage.setImageResource(image[0]);
    }

    @Override
    public int getItemCount() {
        if (recipeCardArrayList == null) {
            return 0;
        } else {
            return recipeCardArrayList.size();
        }
    }


    public class Viewholder extends RecyclerView.ViewHolder{
        ImageView reimage;
        TextView retitle;
        public Viewholder(View itemView) {
            super(itemView);
            reimage = (ImageView)itemView.findViewById(R.id.thumbnail);
            retitle = (TextView)itemView.findViewById(R.id.title);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    RecipeCardModel recipe = recipeCardArrayList.get(getAdapterPosition());

                    Intent intent = new Intent(context, RecipeStepActivity.class);
                    intent.putExtra("key", recipe);
                    context.startActivity(intent);
                    Snackbar.make(v, "Click detected on item " + position,
                            Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
    }


    }


}
