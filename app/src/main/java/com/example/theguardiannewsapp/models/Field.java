package com.example.theguardiannewsapp.models;


import com.google.gson.annotations.SerializedName;

public class Field  {

                @SerializedName("byline")
                private String author;

                @SerializedName("thumbnail")
                private String imgUrl;

                @SerializedName("bodyText")
                private String description;

    public Field() {}

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
