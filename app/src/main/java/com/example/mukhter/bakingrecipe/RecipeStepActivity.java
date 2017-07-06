package com.example.mukhter.bakingrecipe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.mukhter.bakingrecipe.model.RecipeCardModel;

import java.util.ArrayList;
import java.util.List;

public class RecipeStepActivity extends AppCompatActivity {
    RecyclerView recyclerStepView;
    RecipeAdapter recipeAdapter;
    ArrayList<RecipeCardModel>recipeStepModels;
    ArrayList<RecipeCardModel.RecipeInstructionModel>recipeInstructionModels;
    RecipeCardModel recipeCardModel;
    String ingredient;
    TextView ingred;
    private ArrayList<RecipeCardModel>recipeCardArrayList;

    private ArrayList<RecipeCardModel.RecipeStepModel> mIngredients = new ArrayList<>();
    RecipeCardModel pop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_step);
        recipeStepModels = new ArrayList<>();
        recipeInstructionModels=new ArrayList<>();
        recyclerStepView = (RecyclerView) findViewById(R.id.cardStepList);
        recyclerStepView.setHasFixedSize(true);
        LinearLayoutManager mlayoutManager = new LinearLayoutManager(RecipeStepActivity.this);
        recyclerStepView.setLayoutManager(mlayoutManager);
        recipeAdapter= new RecipeAdapter(recipeStepModels);
        recyclerStepView.setAdapter(recipeAdapter);
        ingred=(TextView)findViewById(R.id.textingredients);
        Intent intent = getIntent();
        mIngredients =intent.getParcelableArrayListExtra("quantity");

        StringBuilder ingredientsDetail = new StringBuilder("Ingredients: \n");
        for (RecipeCardModel.RecipeStepModel bakingIngredient : mIngredients) {
            String q= bakingIngredient.getIngredient();
            ingredientsDetail.append(q.toString() + "\n");
        }

        ingred.setText(ingredientsDetail.toString());



    }


}