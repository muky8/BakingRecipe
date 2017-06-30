package com.example.mukhter.bakingrecipe.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by MUKHTER on 24/06/2017.
 */

public class RecipeStepModel implements Parcelable{
    String quantity;
    String measure;
    String ingredient;


    public RecipeStepModel() {
        super();
    }
    private RecipeStepModel(Parcel parcel) {

        ingredient=parcel.readString();
        measure=parcel.readString();
        quantity=parcel.readString();
    }

    public static final Parcelable.Creator<RecipeStepModel> CREATOR = new Parcelable.Creator<RecipeStepModel>() {
        @Override
        public RecipeStepModel createFromParcel(Parcel in) {
            return new RecipeStepModel(in);
        }

        @Override
        public RecipeStepModel[] newArray(int size) {
            return new RecipeStepModel[size];
        }
    };

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


    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel parcel, int flags) {

        parcel.writeString(ingredient);
        parcel.writeString(measure);
        parcel.writeString(quantity);
    }




}
