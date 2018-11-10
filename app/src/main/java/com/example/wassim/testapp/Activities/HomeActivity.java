package com.example.wassim.testapp.Activities;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.wassim.testapp.Adapter.ArticleListAdapter;
import com.example.wassim.testapp.Models.Article;
import com.example.wassim.testapp.Network.RetrofitInstance;
import com.example.wassim.testapp.R;
import com.example.wassim.testapp.Services.Interfaces.ArticleService;
import com.example.wassim.testapp.Utils.Listener.CustomItemClickListener;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class HomeActivity extends AppCompatActivity {

    protected ArticleListAdapter adapter;
     protected RecyclerView recyclerView;
    private ShimmerFrameLayout mShimmerViewContainer;
    private SwipeRefreshLayout swipeContainer;
    private android.support.v7.widget.Toolbar toolbar;



    final String ARTICLE_ID ="5729fc387fdea7e267fa9761";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        mShimmerViewContainer = findViewById(R.id.shimmer_view_container);
        swipeContainer = findViewById(R.id.swipeContainer);






        /** Create handle for the RetrofitInstance interface*/
        ArticleService service = RetrofitInstance.getRetrofitInstance().create(ArticleService.class);

        /** Call the method with parameter in the interface to get the notice data*/
        Call<List<Article>> call = service.getArticles(ARTICLE_ID);
        LoadData(call);


    }

    private void LoadData(Call<List<Article>> call) {
        call.enqueue(new Callback<List<Article>>() {
            @Override
            public void onResponse(Call<List<Article>> call, final Response<List<Article>> response) {

                if (response.isSuccessful()) {
                    if (response.code() == 200) {



                        recyclerView = findViewById(R.id.list);

                        adapter = new ArticleListAdapter(HomeActivity.this, (ArrayList<Article>) response.body(), new CustomItemClickListener() {
                            @Override
                            public void onItemClick(View v, int position) {
                                if(!response.body().get(position).getExternal_link().equals("")){
                                    Intent i = new Intent(HomeActivity.this, DetailActivity.class);
                                    i.putExtra("external_url", response.body().get(position).getExternal_link());
                                    startActivity(i);
                                }else{
                                    Toast.makeText(HomeActivity.this, "No external link found", Toast.LENGTH_SHORT).show();

                                }

                            }
                        });
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(HomeActivity.this);
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setItemAnimator(new DefaultItemAnimator());

                        recyclerView.setAdapter(adapter);




                    }
                    // stop animating Shimmer and hide the layout

                    mShimmerViewContainer.stopShimmer();
                    mShimmerViewContainer.setVisibility(View.GONE);


                }


            }

            @Override
            public void onFailure(Call<List<Article>> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Something went wrong...", Toast.LENGTH_SHORT).show();
            }
        });
    }







    @Override
    public void onResume() {
        super.onResume();
        mShimmerViewContainer.startShimmer();
    }

    @Override
    public void onPause() {
        mShimmerViewContainer.stopShimmer();
        super.onPause();
    }
}
