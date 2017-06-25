package com.example.mukhter.bakingrecipe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.mukhter.bakingrecipe.model.RecipeCardModel;
import com.example.mukhter.bakingrecipe.model.RecipeStepModel;

import java.util.ArrayList;

public class RecipeStepActivity extends AppCompatActivity {
RecyclerView recyclerStepView;
    RecipeAdapter recipeAdapter;
    ArrayList<RecipeCardModel>recipeStepModels;
    RecipeCardModel recipeCardModel;
    String ingredient;
    TextView ingred;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_step);
        recipeStepModels = new ArrayList<>();
        recyclerStepView = (RecyclerView) findViewById(R.id.cardStepList);
        recyclerStepView.setHasFixedSize(true);
        LinearLayoutManager mlayoutManager = new LinearLayoutManager(RecipeStepActivity.this);
        recyclerStepView.setLayoutManager(mlayoutManager);
        recipeAdapter= new RecipeAdapter(recipeStepModels);
        recyclerStepView.setAdapter(recipeAdapter);
ingred=(TextView)findViewById(R.id.textingredients);
        Intent intent = getIntent();

        RecipeCardModel pop = intent.getParcelableExtra("key");
        String inggredients = pop.getIngredient();
        String quantity =pop.getQuantity();
        String measure =pop.getMeasure();
        ingred.setText(inggredients+measure);
        Log.i("",inggredients);

    }


}
