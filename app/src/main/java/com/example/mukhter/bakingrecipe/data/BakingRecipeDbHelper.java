package com.example.mukhter.bakingrecipe.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import com.example.mukhter.bakingrecipe.model.RecipeCardModel;

/**
 * Created by MUKHTER on 10/07/2017.
 */

public class BakingRecipeDbHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "recipe.db";
    private static final int DATABASE_VERSION = 1;
    private final Context context;

    public BakingRecipeDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_TABLE = "CREATE TABLE " + RecipeContract.RecipeEntry.TABLE_NAME + " ("
                + RecipeContract.RecipeEntry._ID + " INTEGER PRIMARY KEY,"
                + RecipeContract.RecipeEntry.COLUMN_RECIPE_NAME + " TEXT,"
                + RecipeContract.RecipeEntry.QUANTITY + " TEXT,"
                + RecipeContract.RecipeEntry.MEASURE + " TEXT,"
                + RecipeContract.RecipeEntry.INGREDIENTS_NAME + " TEXT)";


        // Execute the SQL statement
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + RecipeContract.RecipeEntry.TABLE_NAME);
        onCreate(db);
    }

    public long insertvalues(RecipeCardModel recipeCardModel) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put(RecipeContract.RecipeEntry.COLUMN_RECIPE_NAME, recipeCardModel.getTitle());


        long result = db.insert(RecipeContract.RecipeEntry.TABLE_NAME, null, contentvalues);
        return result;
    }


    public RecipeCardModel.RecipeStepModel getLastViewedRecipe(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String whereClause = RecipeContract.RecipeEntry._ID + "=?";
        String[] whereArgs = {String.valueOf(id)};
        Cursor cursor = db.query(
                RecipeContract.RecipeEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );
        RecipeCardModel.RecipeStepModel recipeStepModel = new RecipeCardModel.RecipeStepModel();

        if (cursor.moveToFirst()) {
            do {
                recipeStepModel.setMeasure(cursor.getString((cursor.getColumnIndex(RecipeContract.RecipeEntry.MEASURE))));
                recipeStepModel.setQuantity(cursor.getString((cursor.getColumnIndex(RecipeContract.RecipeEntry.QUANTITY))));
                recipeStepModel.setIngredient(cursor.getString((cursor.getColumnIndex(RecipeContract.RecipeEntry.INGREDIENTS_NAME))));
            } while (cursor.moveToNext());


        }
        return recipeStepModel;
    }
}
