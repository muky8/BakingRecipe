package com.example.mukhter.bakingrecipe.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by MUKHTER on 17/06/2017.
 */

public class RecipeCardModel implements Parcelable {

    public String image;
    public String title;
    public String id;


    private ArrayList<RecipeStepModel> mIngredients;
    private ArrayList<RecipeInstructionModel> mInstructions;

    public RecipeCardModel() {
        super();
    }

    private RecipeCardModel(Parcel parcel) {
        image = parcel.readString();
        title = parcel.readString();
        id = parcel.readString();
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

    public ArrayList<RecipeStepModel> getmIngredients() {
        return mIngredients;
    }

    public void setReceipeIngredientList(ArrayList<RecipeStepModel> mIngredients) {
        this.mIngredients = mIngredients;
    }

    public ArrayList<RecipeInstructionModel> getmInstructions() {
        return mInstructions;
    }

    public void setReceipeInstruction(ArrayList<RecipeInstructionModel> mInstructions) {
        this.mInstructions = mInstructions;
    }

    public String getImage() {
        return image;
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
    }

    /**
     * Created by MUKHTER on 24/06/2017.
     */

    public static class RecipeStepModel implements Parcelable {
        String quantity;
        String measure;
        String ingredient;


        public RecipeStepModel(String quantity, String measure, String ingredient) {
            this.quantity = quantity;
            this.measure = measure;
            this.ingredient = ingredient;


        }

        private RecipeStepModel(Parcel parcel) {

            ingredient = parcel.readString();
            measure = parcel.readString();
            quantity = parcel.readString();
        }

        public static final Creator<RecipeStepModel> CREATOR = new Creator<RecipeStepModel>() {
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

    /**
     * Created by MUKHTER on 06/07/2017.
     */

    public static class RecipeInstructionModel implements Parcelable {
        String id;
        String shortDescription;
        String description;
        String videoURL;
        String thumbnailURL;

        private ArrayList<RecipeInstructionModel> mvideoUrl;

        public RecipeInstructionModel() {

        }

        protected RecipeInstructionModel(Parcel in) {
            id = in.readString();
            shortDescription = in.readString();
            description = in.readString();
            videoURL = in.readString();
            thumbnailURL = in.readString();
        }

        public ArrayList<RecipeInstructionModel> getMvideoUrl() {
            return mvideoUrl;
        }

        public void setMvideoUrl(ArrayList<RecipeInstructionModel> mvideoUrl) {
            this.mvideoUrl = mvideoUrl;
        }

        public static final Creator<RecipeInstructionModel> CREATOR = new Creator<RecipeInstructionModel>() {
            @Override
            public RecipeInstructionModel createFromParcel(Parcel in) {
                return new RecipeInstructionModel(in);
            }

            @Override
            public RecipeInstructionModel[] newArray(int size) {
                return new RecipeInstructionModel[size];
            }
        };


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getShortDescription() {
            return shortDescription;
        }

        public void setShortDescription(String shortDescription) {
            this.shortDescription = shortDescription;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getVideoURL() {
            return videoURL;
        }

        public void setVideoURL(String videoURL) {
            this.videoURL = videoURL;
        }

        public String getThumbnailURL() {
            return thumbnailURL;
        }

        public void setThumbnailURL(String thumbnailURL) {
            this.thumbnailURL = thumbnailURL;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(id);
            dest.writeString(shortDescription);
            dest.writeString(description);
            dest.writeString(videoURL);
            dest.writeString(thumbnailURL);
        }
    }
}
