package com.example.mukhter.bakingrecipe.ui;

import android.app.FragmentManager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;

import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.androidnetworking.AndroidNetworking;
import com.example.mukhter.bakingrecipe.RecipeStepFragment;
import com.example.mukhter.bakingrecipe.adapter.Adapter;
import com.example.mukhter.bakingrecipe.R;
import com.example.mukhter.bakingrecipe.model.RecipeCardModel;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Adapter adapter;
    ArrayList<RecipeCardModel> arrayList;

    ArrayList<RecipeCardModel.RecipeInstructionModel> arrayList2 = new ArrayList<>();

    ArrayList<RecipeCardModel.RecipeStepModel> stepModelArrayList;
    String id;
    public String url = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";

    RecyclerView recipeRecyclerView;
    private boolean twoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AndroidNetworking.initialize(getApplicationContext());
        fetch();


    }

    void fetch() {
        JsonArrayRequest jsonObjReq = new JsonArrayRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONArray>() {
                    ArrayList<RecipeCardModel> arrayList = new ArrayList<>();

                    ArrayList<RecipeCardModel.RecipeInstructionModel> arrayList2 = new ArrayList<>();
                    ArrayList<RecipeCardModel.RecipeStepModel> arrayListstep = new ArrayList<>();

                    @Override
                    public void onResponse(JSONArray response) {
                        RecipeCardModel recipeCardModel;
                        RecipeCardModel.RecipeInstructionModel recipeInstructionModel;

                        try {
                            String res = response.toString();
                            Log.i("Response", response.toString());
                            JSONArray initial = new JSONArray(res);
                            for (int i = 0; i < response.length(); i++) {
                                recipeCardModel = new RecipeCardModel();
                                recipeInstructionModel = new RecipeCardModel.RecipeInstructionModel();
                                JSONObject jobj = response.getJSONObject(i);
                                String title = jobj.getString("name");

                                recipeCardModel.setTitle(title);
                                Log.i("TITLE", title);

                                JSONArray ingredient = jobj.getJSONArray("ingredients");

                                ArrayList<RecipeCardModel.RecipeStepModel> ingredientArrayList = new ArrayList<>();

                                ArrayList<RecipeCardModel.RecipeInstructionModel> instructionArrayList = new ArrayList<>();
                                RecipeCardModel.RecipeStepModel bakingIngredient = null;
                                RecipeCardModel.RecipeInstructionModel bakingInstructions = null;
                                for (int j = 0; j < ingredient.length(); j++) {

                                    JSONObject jobj2 = ingredient.getJSONObject(j);

                                    String measure = jobj2.getString("measure");
                                    String quantity = jobj2.getString("quantity");
                                    String ingredientt = jobj2.getString("ingredient");
                                    bakingIngredient = new RecipeCardModel.RecipeStepModel(quantity, measure, ingredientt);
                                    ingredientArrayList.add(bakingIngredient);


                                    Log.i("MEAS", quantity + measure + ingredientt);

                                }
                                recipeCardModel.setReceipeIngredientList(ingredientArrayList);


                                JSONArray steps = jobj.getJSONArray("steps");
                                Log.i("STEPS", steps.toString());
                                for (int g = 0; g < steps.length(); g++) {
                                    JSONObject jobj2 = steps.getJSONObject(g);

                                    bakingInstructions = new RecipeCardModel.RecipeInstructionModel();
                                    String id = jobj2.getString("id");
                                    bakingInstructions.setId(id);
                                    String shortDescription = jobj2.getString("shortDescription");
                                    bakingInstructions.setShortDescription(shortDescription);
                                    String description = jobj2.getString("description");
                                    bakingInstructions.setDescription(description);
                                    String videoURL = jobj2.getString("videoURL");
                                    bakingInstructions.setVideoURL(videoURL);
                                    String thumbnailURL = jobj2.getString("thumbnailURL");
                                    bakingInstructions.setThumbnailURL(thumbnailURL);
                                    instructionArrayList.add(bakingInstructions);
                                    Log.i("SECOND STEP", id + shortDescription + description + videoURL + thumbnailURL);
                                }
                                arrayList2.add(bakingInstructions);
                                recipeInstructionModel.setMvideoUrl(instructionArrayList);
                                recipeCardModel.setReceipeInstruction(instructionArrayList);
                                arrayList.add(recipeCardModel);

                            }


                            recipeRecyclerView = (RecyclerView) findViewById(R.id.cardList);
                            recipeRecyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));

                            adapter = new Adapter(MainActivity.this, arrayList, arrayList2);

                            recipeRecyclerView.setAdapter(adapter);

                            Log.i("Array", arrayList.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }


                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("", "Error: " + error.getMessage());
                // hide the progress dialog

            }
        });

        Volley.newRequestQueue(this).add(jsonObjReq);

    }

}