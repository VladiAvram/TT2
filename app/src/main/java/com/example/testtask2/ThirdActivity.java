package com.example.testtask2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testtask2.adapters.MyAdapterRecyclerView;
import com.example.testtask2.objects.ResponseJoke;
import com.example.testtask2.service.TestApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ThirdActivity extends AppCompatActivity {
    String category;

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;

    List<String> listJokes = new ArrayList<>();

    final String baseURL = "https://api.chucknorris.io/";

    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    private final TestApiService apiService = retrofit.create(TestApiService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_third);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MyAdapterRecyclerView(listJokes, this);
        recyclerView.setAdapter(adapter);

        Intent intent = getIntent();
        category = intent.getStringExtra(FirstActivity.intentKeyCategory);
        getJokes(category);

    }

    private void getJokes(String category){
        for (int i = 0; i < 15; i++) {
            apiService.getJoke(category).enqueue(new Callback<ResponseJoke>() {
                @Override
                public void onResponse(Call<ResponseJoke> call, Response<ResponseJoke> response) {
                    ResponseJoke responseJoke = response.body();
                    addJokeToList(responseJoke.value);
                    adapter.notifyDataSetChanged();
                }
                @Override
                public void onFailure(Call<ResponseJoke> call, Throwable t) {
                    displayError(t.getMessage());
                }
            });
        }

    }

    private void addJokeToList(String joke){
        listJokes.add(joke);
    }
    public void onTvClick(View v){
        //   костыль   ((
    }

    private void displayError(String errorMessage){
        new AlertDialog.Builder(this)
                .setTitle("oooops")
                .setMessage(errorMessage)
                .setPositiveButton("try again", (dialog, which) -> {

                    getJokes(category);
                    dialog.cancel();
                })
                .create()
                .show();
    }
}

