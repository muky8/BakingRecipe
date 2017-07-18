package com.example.mukhter.bakingrecipe;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mukhter.bakingrecipe.adapter.CustomAdapter;
import com.example.mukhter.bakingrecipe.adapter.RecipeAdapter;
import com.example.mukhter.bakingrecipe.model.RecipeCardModel;
import com.example.mukhter.bakingrecipe.ui.ExoplayerActivity;
import com.example.mukhter.bakingrecipe.ui.ExoplayerFragment;
import com.example.mukhter.bakingrecipe.ui.MainActivity;
import com.example.mukhter.bakingrecipe.ui.RecipeStepActivity;

import java.util.ArrayList;


public class RecipeStepFragment extends Fragment implements AdapterView.OnItemClickListener {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    RecyclerView recyclerStepView;
    RecipeAdapter recipeAdapter;
    ArrayList<RecipeCardModel.RecipeInstructionModel> recipeStepModels;
    ArrayList<RecipeCardModel> recipeInstructionModels;
    RecipeCardModel recipeCardModel;
    String ingredient;
    TextView ingred;
    ArrayList<RecipeCardModel> recipeCardArrayList = new ArrayList<>();
    public static final String ARG_ITEM_ID = "item_id";
    private ArrayList<RecipeCardModel.RecipeStepModel> mIngredients = new ArrayList<>();
    RecipeCardModel pop = new RecipeCardModel();
    boolean mTwoPane;
    Context context;
    ClickListener clickListener;

    public RecipeStepFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_recipe_step, container, false);

        recipeInstructionModels = new ArrayList<>();
        recipeStepModels = new ArrayList<>();

        ListView listView = (ListView) view.findViewById(R.id.cardStepList);
        Intent intent = getActivity().getIntent();
        mIngredients = intent.getParcelableArrayListExtra("quantity");
        recipeStepModels = intent.getParcelableArrayListExtra("next");

        CustomAdapter adapter = new CustomAdapter(getActivity().getApplicationContext(),
                R.layout.recipe_step_cardlayout, recipeStepModels);


        listView.setAdapter(adapter);

        ingred = (TextView) view.findViewById(R.id.textingredients);

        if (mIngredients == null) {

        } else {

            StringBuilder ingredientsDetail = new StringBuilder("Ingredients: \n" + "\n");
            for (RecipeCardModel.RecipeStepModel bakingIngredient : mIngredients) {
                String quantity = bakingIngredient.getQuantity();
                String measure = bakingIngredient.getMeasure();
                String ingredient = bakingIngredient.getIngredient();
                ingredientsDetail.append(quantity + measure + " " + ingredient + "\n");
            }

            ((TextView) view.findViewById(R.id.textingredients)).setText(ingredientsDetail.toString());
        }

        listView.setOnItemClickListener(this);


        return view;
    }


    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);

        try {
            clickListener = (ClickListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        clickListener.onCustomClick(position,parent);



    }

    public interface ClickListener {
        public void onCustomClick(int position,AdapterView<?> parent);
        // pass view as argument or whatever you want.
    }
}
