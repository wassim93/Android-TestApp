package com.example.wassim.testapp.Services.Interfaces;

import com.example.wassim.testapp.Models.Article;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ArticleService {

    @GET("{articleId}")
    Call<List<Article>> getArticles(@Path("articleId") String id);
}
