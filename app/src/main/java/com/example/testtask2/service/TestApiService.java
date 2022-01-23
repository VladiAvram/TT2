package com.example.testtask2.service;

import com.example.testtask2.objects.ResponseJoke;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TestApiService {

        @GET("jokes/categories")
        Call<List<String>> getJokesCategories();

        @GET("jokes/random")
        Call<ResponseJoke> getJoke(
                @Query("category")
                        String category
        );

}
