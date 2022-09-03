package com.example.mynewsapp;

import android.content.Context;
import android.widget.Toast;

import com.example.mynewsapp.Models.APIResponse;
import com.example.mynewsapp.Models.Article;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class Requests {

    public final static String API_KEY = "ca20768c885b4f1dbdbd92df6a847329";

    Context context;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    NewsService newsService = retrofit.create(NewsService.class);

    public Requests(Context context) {
        this.context = context;
    }

    public void getNewsArticles(Listener listener, String category, String q){

        Call<APIResponse> call = newsService.articles(API_KEY, "us", category, q);

        try{
            call.enqueue(new Callback<APIResponse>() {
                @Override
                public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                    if (!response.isSuccessful()){
                        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                    }

                    listener.onReceivedData(response.body().getArticles(), response.message());
                }

                @Override
                public void onFailure(Call<APIResponse> call, Throwable t) {

                    listener.onError("Failed");

                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public interface Listener<APIResponse>{

        void onReceivedData(List<Article> list, String msg);
        void onError(String msg);

    }

    public interface NewsService {
        @GET("top-headlines")
        Call<APIResponse> articles(
                @Query("apiKey") String api_key,
                @Query("country") String country,
                @Query("category") String category,
                @Query("q") String q

        );
    }
}
