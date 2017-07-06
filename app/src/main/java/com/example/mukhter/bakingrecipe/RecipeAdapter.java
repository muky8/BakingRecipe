package com.example.mukhter.bakingrecipe;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mukhter.bakingrecipe.model.RecipeCardModel;

import java.util.ArrayList;

/**
 * Created by MUKHTER on 24/06/2017.
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewholder>   {
    private ArrayList<RecipeCardModel> recipeStepArrayList;

    private String[]titles ={
            "Ingredients"
    };

    public RecipeAdapter(ArrayList<RecipeCardModel> recipeStepArrayList) {
        this.recipeStepArrayList = recipeStepArrayList;
    }


    @Override
    public RecipeViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.recipe_step_cardlayout, parent, false);

        RecipeViewholder view = new RecipeViewholder(itemView);
        return view;
    }

    @Override
    public void onBindViewHolder(RecipeViewholder holder, int position) {
        RecipeViewholder myHolder= (RecipeViewholder) holder;

        RecipeCardModel current=recipeStepArrayList.get(position);
        myHolder.title.setText((CharSequence) current.getmInstructions());


    }

    @Override
    public int getItemCount() {

        return recipeStepArrayList.size();
    }



    public class RecipeViewholder extends RecyclerView.ViewHolder{
        TextView title;
        public RecipeViewholder(View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.recipestep);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    Snackbar.make(v, "Click detected on item " + position,
                            Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
        }


    }


}
