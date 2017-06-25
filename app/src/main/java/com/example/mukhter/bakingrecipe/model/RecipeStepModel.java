package com.example.mukhter.bakingrecipe.model;

/**
 * Created by MUKHTER on 24/06/2017.
 */

public class RecipeStepModel {
    String quantity;
    String measure;
    String ingredient;


    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }
}
