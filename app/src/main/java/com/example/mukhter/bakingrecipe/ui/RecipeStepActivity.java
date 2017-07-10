package com.example.mukhter.bakingrecipe.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mukhter.bakingrecipe.R;
import com.example.mukhter.bakingrecipe.adapter.CustomAdapter;
import com.example.mukhter.bakingrecipe.adapter.RecipeAdapter;
import com.example.mukhter.bakingrecipe.model.RecipeCardModel;
import com.example.mukhter.bakingrecipe.ui.ExoplayerActivity;

import java.util.ArrayList;

public class RecipeStepActivity extends AppCompatActivity {
    RecyclerView recyclerStepView;
    RecipeAdapter recipeAdapter;
    ArrayList<RecipeCardModel.RecipeInstructionModel> recipeStepModels;
    ArrayList<RecipeCardModel> recipeInstructionModels;
    RecipeCardModel recipeCardModel;
    String ingredient;
    TextView ingred;
    ArrayList<RecipeCardModel> recipeCardArrayList = new ArrayList<>();

    private ArrayList<RecipeCardModel.RecipeStepModel> mIngredients = new ArrayList<>();
    RecipeCardModel pop = new RecipeCardModel();

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_step);
        recipeInstructionModels = new ArrayList<>();
        recipeStepModels = new ArrayList<>();
        android.support.v7.app.ActionBar actionBar =  getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        ListView listView = (ListView) findViewById(R.id.cardStepList);

        Intent intent = getIntent();
        mIngredients = intent.getParcelableArrayListExtra("quantity");
        recipeStepModels = intent.getParcelableArrayListExtra("next");
        CustomAdapter adapter = new CustomAdapter(getApplicationContext(), R.layout.recipe_step_cardlayout, recipeStepModels);
        if(adapter==null){

        }else{

            listView.setAdapter(adapter);
        }
        ingred = (TextView) findViewById(R.id.textingredients);


        StringBuilder ingredientsDetail = new StringBuilder("Ingredients: \n" + "\n");
        for (RecipeCardModel.RecipeStepModel bakingIngredient : mIngredients) {
            String quantity = bakingIngredient.getQuantity();
            String measure = bakingIngredient.getMeasure();
            String ingredient = bakingIngredient.getIngredient();
            ingredientsDetail.append(quantity + measure + " " + ingredient + "\n");
        }

        ingred.setText(ingredientsDetail.toString());

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ExoplayerActivity.class);
                RecipeCardModel.RecipeInstructionModel recipeInstructionModel = (RecipeCardModel.RecipeInstructionModel) parent.getItemAtPosition(position);

                intent.putExtra("recipe", recipeInstructionModel);

                startActivity(intent);


            }
        });

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}