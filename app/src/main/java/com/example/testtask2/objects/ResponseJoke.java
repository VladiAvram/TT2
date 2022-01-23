package com.example.testtask2.objects;

import com.google.gson.annotations.SerializedName;

public class ResponseJoke {

    @SerializedName("categories")
    public String[] categories;

    @SerializedName("created_at")
    public String created_at;

    @SerializedName("icon_url")
    public String icon_url;

    @SerializedName("id")
    public String id;

    @SerializedName("updated_at")
    public String updated_at;

    @SerializedName("url")
    public String url;

    @SerializedName("value")
    public String value;

}
