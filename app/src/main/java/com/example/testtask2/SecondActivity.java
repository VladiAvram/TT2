package com.example.testtask2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testtask2.adapters.MyAdapterRecyclerView;
import com.example.testtask2.service.TestApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SecondActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;

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

        getCategories();
    }

    private void getCategories(){
        apiService.getJokesCategories().enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                List<String> categList = response.body();
                setAdapter(categList);
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                displayError(t.getMessage());
            }
        });
    }
    private void setAdapter (List<String> list) {
        adapter = new MyAdapterRecyclerView(list, this);
        recyclerView.setAdapter(adapter);
    }

    private void displayError(String errorMessage){
        new AlertDialog.Builder(this)
                .setTitle("oooops")
                .setMessage(errorMessage)
                .setPositiveButton("try again", (dialog, which) -> {
                    getCategories();
                    dialog.cancel();
                })
                .create()
                .show();
    }

    public void onTvClick(View v) {
        String text = ((TextView) v).getText().toString();
        Intent intent = new Intent(this, ThirdActivity.class);
        intent.putExtra(FirstActivity.intentKeyCategory, text);
        startActivity(intent);
    }
}
