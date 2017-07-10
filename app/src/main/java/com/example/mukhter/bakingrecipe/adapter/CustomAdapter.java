package com.example.mukhter.bakingrecipe.adapter;

/**
 * Created by MUKHTER on 07/07/2017.
 */


import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mukhter.bakingrecipe.R;
import com.example.mukhter.bakingrecipe.model.RecipeCardModel;


public class CustomAdapter extends ArrayAdapter<RecipeCardModel.RecipeInstructionModel> {
    int Resource;
    Context context;
    LayoutInflater vi;
    private ArrayList<RecipeCardModel.RecipeInstructionModel> recipeInstructionArrayList;

    public CustomAdapter(Context context, int resource, ArrayList<RecipeCardModel.RecipeInstructionModel> objects) {
        super(context, resource, objects);
        recipeInstructionArrayList = objects;
        Resource = resource;
        this.context = context;
        vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        if (recipeInstructionArrayList == null) {
            return 0;
        } else {
            return recipeInstructionArrayList.size();
        }

    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewholder holder;
        if (convertView == null) {
            convertView = vi.inflate(Resource, null);
            holder = new viewholder();
            holder.textView = (TextView) convertView.findViewById(R.id.recipestep);
            convertView.setTag(holder);
        } else {
            holder = (viewholder) convertView.getTag();
        }
        holder.textView.setText(recipeInstructionArrayList.get(position).getShortDescription());

        return convertView;
    }

    static class viewholder {
        public TextView textView;
    }
}
