package com.example.mukhter.bakingrecipe.ui;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.mukhter.bakingrecipe.R;
import com.example.mukhter.bakingrecipe.model.RecipeCardModel;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.SimpleExoPlayer;

import java.util.ArrayList;

public class ExoplayerActivity extends AppCompatActivity {
    SimpleExoPlayerView playerView;
    SimpleExoPlayer player;
    TextView descriptiontext;
    private boolean playWhenReady;
    int currentWindow;
    long playbackPosition;
    private boolean autoPlay = false;
    public final String VIDEO_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd9cb_4-press-crumbs-in-pie-plate-creampie/4-press-crumbs-in-pie-plate-creampie.mp4";
    RecipeCardModel.RecipeInstructionModel recipe;
    ArrayList<RecipeCardModel.RecipeInstructionModel> recipeStepModels;
    String videoUrl;
    String shortdescription;
    private RecipeCardModel.RecipeInstructionModel mReciepeClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_exoplayer);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        FragmentManager fragmentManager = getSupportFragmentManager();
        ExoplayerFragment exoplayerFragment;
        exoplayerFragment = new ExoplayerFragment();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.frame_exo, exoplayerFragment);
        transaction.commit();


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
