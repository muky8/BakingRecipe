package com.example.mukhter.bakingrecipe;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;

import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.RequestQueue;
import com.androidnetworking.AndroidNetworking;
import com.example.mukhter.bakingrecipe.model.RecipeCardModel;
import com.example.mukhter.bakingrecipe.model.RecipeStepModel;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Adapter adapter;
    ArrayList<RecipeCardModel> arrayList;
    ArrayList<RecipeStepModel>stepModelArrayList;
    String id;
    public String url = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";

RecyclerView recipeRecyclerView;
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
                    ArrayList<RecipeCardModel> arrayList =new ArrayList<>();
                    ArrayList<RecipeStepModel> arrayListstep =new ArrayList<>();
                    @Override
                    public void onResponse(JSONArray response) {
                        RecipeCardModel recipeCardModel;
                        RecipeStepModel recipeStepModel=new RecipeStepModel();

                        try {
                            String res = response.toString();
                            Log.i("Response", response.toString());
                            JSONArray initial = new JSONArray(res);
                            for (int i = 0; i < response.length(); i++) {
                                recipeCardModel =new RecipeCardModel();

                                JSONObject jobj = response.getJSONObject(i);
                                String title = jobj.getString("name");
                                 recipeCardModel.setTitle(title);
                                     Log.i("TITLE",title);

                                    JSONArray ingredient = jobj.getJSONArray("ingredients");

                                for(int j=0;j<ingredient.length();j++){
                                      recipeStepModel =new RecipeStepModel();
                                    JSONObject jobj2 = ingredient.getJSONObject(j);

                                    String measure = jobj2.getString("measure");
                                    recipeCardModel.setMeasure(measure);
                                    String quantity =jobj2.getString("quantity");
                                    recipeCardModel.setQuantity(quantity);

                                }
                                     Log.i("INGREDIENT",ingredient.toString());



                                JSONArray steps = jobj.getJSONArray("steps");
                                Log.i("STEPS",steps.toString());


                                arrayList.add(recipeCardModel);


                            }


                            recipeRecyclerView = (RecyclerView) findViewById(R.id.cardList);
                            recipeRecyclerView.setHasFixedSize(true);
                            LinearLayoutManager mlayoutManager = new LinearLayoutManager(MainActivity.this);
                            recipeRecyclerView.setLayoutManager(mlayoutManager);

                            adapter = new Adapter(MainActivity.this,arrayList);
                            recipeRecyclerView.setAdapter(adapter);
                            Log.i("Array",arrayList.toString());
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
