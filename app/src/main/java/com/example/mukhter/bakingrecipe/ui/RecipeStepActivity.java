package com.example.mukhter.bakingrecipe.ui;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mukhter.bakingrecipe.R;
import com.example.mukhter.bakingrecipe.RecipeStepFragment;
import com.example.mukhter.bakingrecipe.adapter.CustomAdapter;
import com.example.mukhter.bakingrecipe.adapter.RecipeAdapter;
import com.example.mukhter.bakingrecipe.model.RecipeCardModel;
import com.example.mukhter.bakingrecipe.ui.ExoplayerActivity;

import java.util.ArrayList;

public class RecipeStepActivity extends AppCompatActivity implements RecipeStepFragment.ClickListener{
    boolean tabletSize=true;

    private ArrayList<RecipeCardModel.RecipeInstructionModel> mSteps = new ArrayList<>();
    private Bundle args;

    private ArrayList<RecipeCardModel.RecipeStepModel> mIngredients = new ArrayList<>();
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_step);

        android.support.v7.app.ActionBar actionBar =  getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
if(savedInstanceState==null){
    android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
    RecipeStepFragment recipeDetailsFragment ;
    recipeDetailsFragment = new RecipeStepFragment();
    FragmentTransaction transaction = fragmentManager.beginTransaction();

    transaction.replace(R.id.item_detail_container, recipeDetailsFragment);
    transaction.commit();
}

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

    @Override
    public void onCustomClick(int position,AdapterView<?> parent) {
if (tabletSize){

    FragmentManager fragmentManager = getSupportFragmentManager();
    ExoplayerFragment exoplayerFragment;
    exoplayerFragment = new ExoplayerFragment();
    FragmentTransaction transaction = fragmentManager.beginTransaction();
    transaction.add(R.id.frame_exo, exoplayerFragment);
    transaction.commit();
} else {


    Intent intent = new Intent(this, ExoplayerActivity.class);
    RecipeCardModel.RecipeInstructionModel recipeInstructionModel =
            (RecipeCardModel.RecipeInstructionModel) parent.getItemAtPosition(position);

    intent.putExtra("recipe", recipeInstructionModel);

    startActivity(intent);

}


    }
}