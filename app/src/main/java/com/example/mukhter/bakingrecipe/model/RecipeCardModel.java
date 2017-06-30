package com.example.mukhter.bakingrecipe.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by MUKHTER on 17/06/2017.
 */

public class RecipeCardModel implements Parcelable{

   public String image;
   public String title;
    public String id;
    String ingredient;
    String measure;
    String quantity;

    public ArrayList<RecipeStepModel> receipeIngredientList = new ArrayList<>();

    public RecipeCardModel() {
        super();
    }

    private RecipeCardModel(Parcel parcel) {
        image = parcel.readString();
        title = parcel.readString();
        id =parcel.readString();
        ingredient=parcel.readString();
        measure=parcel.readString();
        quantity=parcel.readString();
    }

    public static final Parcelable.Creator<RecipeCardModel> CREATOR = new Parcelable.Creator<RecipeCardModel>() {
        @Override
        public RecipeCardModel createFromParcel(Parcel in) {
            return new RecipeCardModel(in);
        }

        @Override
        public RecipeCardModel[] newArray(int size) {
            return new RecipeCardModel[size];
        }
    };


    public String getImage() {
        return image;
    }


    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(image);
        parcel.writeString(title);
        parcel.writeString(id);
        parcel.writeString(ingredient);
        parcel.writeString(measure);
        parcel.writeString(quantity);
    }
}
