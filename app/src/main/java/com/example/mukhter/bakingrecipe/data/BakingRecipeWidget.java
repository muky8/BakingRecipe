package com.example.mukhter.bakingrecipe.data;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;

import com.example.mukhter.bakingrecipe.R;
import com.example.mukhter.bakingrecipe.model.RecipeCardModel;
import com.example.mukhter.bakingrecipe.ui.MainActivity;
import com.example.mukhter.bakingrecipe.ui.RecipeStepActivity;

import java.util.ArrayList;

/**
 * Implementation of App Widget functionality.
 */
public class BakingRecipeWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId, RecipeCardModel.RecipeStepModel recipe) {

        BakingRecipeDbHelper dbhelper = new BakingRecipeDbHelper(context);
        recipe = dbhelper.getLastViewedRecipe(0);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.baking_recipe_widget);
        Intent intent;
        if (recipe != null) {
            String recipeName = recipe.getIngredient();
            views.setTextViewText(R.id.appwidget_text, recipeName);

        } else {
            views.setTextViewText(R.id.appwidget_text, context.getString(R.string.app_name));
            intent = new Intent(context, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }


        intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("quantity", recipe);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, appWidgetId, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setOnClickPendingIntent(R.id.widget_layout, pendingIntent);
        // Instruct the widget manager to update the widget

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId, null);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }


}

