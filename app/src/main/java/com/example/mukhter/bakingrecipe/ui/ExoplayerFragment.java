package com.example.mukhter.bakingrecipe.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mukhter.bakingrecipe.R;
import com.example.mukhter.bakingrecipe.model.RecipeCardModel;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;


public class ExoplayerFragment extends Fragment {

    private String mParam1;
    private String mParam2;
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
RecipeCardModel.RecipeInstructionModel mReciepeClass = new RecipeCardModel.RecipeInstructionModel();
   String thumbnailUrl;

    public ExoplayerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.activity_exoplayer, container, false);

        playerView = (SimpleExoPlayerView) view.findViewById(R.id.video_view);
        descriptiontext = (TextView) view.findViewById(R.id.description);
        Intent intent = getActivity().getIntent();
        RecipeCardModel.RecipeInstructionModel instructionModel = intent.getParcelableExtra("recipe");

        if (instructionModel == null) {

        } else {
            videoUrl = instructionModel.getVideoURL();
            shortdescription = instructionModel.getDescription();
            thumbnailUrl=instructionModel.getThumbnailURL();

        }



        ((TextView) view.findViewById(R.id.description)).setText(shortdescription);

if(videoUrl !=null) {
    initializePlayer(videoUrl);
}else{
    if(thumbnailUrl!=null&&thumbnailUrl.contains(".mp4")){
        initializePlayer(thumbnailUrl);
    }
}

        return view;
    }


    void initializePlayer(String videoUrl) {
        // create a new instance of SimpleExoPlayer
        player = ExoPlayerFactory.newSimpleInstance(
                new DefaultRenderersFactory(getActivity().getApplicationContext()),
                new DefaultTrackSelector(),
                new DefaultLoadControl());

        // attach the just created player to the view responsible for displaying the media (i.e. media controls, visual feedback)
        playerView.setPlayer(player);
        player.setPlayWhenReady(autoPlay);

        // resume playback position
        player.seekTo(currentWindow, playbackPosition);
        if (videoUrl != null) {
            Uri uri = Uri.parse(videoUrl);
            MediaSource mediaSource = buildMediaSource(uri);

            // now we are ready to start playing our media files
            player.prepare(mediaSource);

        }

    }

    public static MediaSource buildMediaSource(Uri uri) {

        DefaultExtractorsFactory extractorSourceFactory = new DefaultExtractorsFactory();
        DefaultHttpDataSourceFactory dataSourceFactory = new DefaultHttpDataSourceFactory("ua");

//        ExtractorMediaSource audioSource = new ExtractorMediaSource(uri, dataSourceFactory, extractorSourceFactory, null, null);

        // this return a single mediaSource object. i.e. no next, previous buttons to play next/prev media file
//        return new ExtractorMediaSource(uri, dataSourceFactory, extractorSourceFactory, null, null);

        /*
         * Uncomment the line below to play multiple meidiaSources in sequence aka playlist (and totally without buffering!)
         * NOTE: you have to comment the return statement just above this comment
         */
//         ExtractorMediaSource videoSource1 = new ExtractorMediaSource(Uri.parse(VIDEO_1), dataSourceFactory, extractorSourceFactory, null, null);


        return new ExtractorMediaSource(uri, dataSourceFactory, extractorSourceFactory, null, null);
        // returns a mediaSource collection
//          ConcatenatingMediaSource(videoSource1, audioSource, videoSource2);


    }

    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT > 23) {
            initializePlayer(videoUrl);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        if ((Util.SDK_INT <= 23 || player == null)) {
            initializePlayer(videoUrl);
        }
    }

    @SuppressLint("InlinedApi")
    private void hideSystemUi() {
        playerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }


    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT <= 23) {
            releasePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT > 23) {
            releasePlayer();
        }
    }

    private void releasePlayer() {
        if (player != null) {
            playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            playWhenReady = player.getPlayWhenReady();
            player.release();
            player = null;
        }
    }


    public void setRecipeClassObject(RecipeCardModel.RecipeInstructionModel reciepeClass) {
        mReciepeClass =
                reciepeClass;
    }
}
