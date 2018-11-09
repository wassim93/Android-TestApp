package com.example.wassim.testapp.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.wassim.testapp.Adapter.ArticleListAdapter;
import com.example.wassim.testapp.Models.Article;
import com.example.wassim.testapp.Network.RetrofitInstance;
import com.example.wassim.testapp.R;
import com.example.wassim.testapp.Services.Interfaces.ArticleService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    private ArticleListAdapter adapter;
     private RecyclerView recyclerView;
     final String ARTICLE_ID ="5729fc387fdea7e267fa9761";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        /** Create handle for the RetrofitInstance interface*/
        ArticleService service = RetrofitInstance.getRetrofitInstance().create(ArticleService.class);

        /** Call the method with parameter in the interface to get the notice data*/
        Call<List<Article>> call = service.getArticles(ARTICLE_ID);
        call.enqueue(new Callback<List<Article>>() {
            @Override
            public void onResponse(Call<List<Article>> call, Response<List<Article>> response) {

                recyclerView = findViewById(R.id.list);
                adapter = new ArticleListAdapter(HomeActivity.this, (ArrayList<Article>) response.body());
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(HomeActivity.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Article>> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Something went wrong...", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
