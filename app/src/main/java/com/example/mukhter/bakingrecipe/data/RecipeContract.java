package com.example.mukhter.bakingrecipe.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by MUKHTER on 10/07/2017.
 */


public class RecipeContract {
    public static final String AUTHORITY = "com.example.mukhter.bakingrecipe";
    public static final Uri BASE_URL = Uri.parse("content://" + AUTHORITY);
    public static final String PATH = "recipe";

    private RecipeContract(){

    }

    public static class RecipeEntry implements BaseColumns {
        public  final static Uri CONTENT_URI = BASE_URL.buildUpon().appendPath(PATH).build();
        public final static String TABLE_NAME = "recipes";

        public final static String _ID = "id";
        public final static String COLUMN_RECIPE_NAME = "recipe_name";
        public final static String INGREDIENTS_NAME = "ingredient";

    }
}