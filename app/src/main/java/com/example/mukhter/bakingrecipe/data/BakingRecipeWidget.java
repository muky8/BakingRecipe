package com.example.mukhter.bakingrecipe.data;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;

import com.example.mukhter.bakingrecipe.R;
import com.example.mukhter.bakingrecipe.model.RecipeCardModel;
import com.example.mukhter.bakingrecipe.ui.RecipeStepActivity;

import java.util.ArrayList;

/**
 * Implementation of App Widget functionality.
 */
public class BakingRecipeWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,RecipeCardModel recipe,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);


        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.baking_recipe_widget);
        Intent intent;
        if (recipe != null) {
            String recipeName = recipe.getTitle();
            views.setTextViewText(R.id.appwidget_text, recipeName);

            ArrayList<RecipeCardModel.RecipeInstructionModel> ingredients = recipe.getmInstructions();
            if (ingredients != null) {
                StringBuilder ingredientResult = new StringBuilder();
                for (RecipeCardModel.RecipeInstructionModel ing : ingredients) {
                    String description =ing.getShortDescription();
                    ingredientResult.append(description + "\n");
                }
                views.setTextViewText(R.id.appwidget_text, ingredientResult.toString());
            }
            Bundle bundle = new Bundle();
            bundle.putParcelable("Extra", recipe);

            intent = new Intent(context, RecipeStepActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtras(bundle);

        } else {
            views.setTextViewText(R.id.appwidget_text, context.getString(Integer.parseInt("")));
            intent = new Intent(context, RecipeStepActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }

        PendingIntent pendingIntent = PendingIntent.getActivity(context, appWidgetId, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Instruct the widget manager to update the widget

        appWidgetManager.updateAppWidget(appWidgetId, views);
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

