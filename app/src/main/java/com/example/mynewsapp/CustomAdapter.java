package com.example.mynewsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynewsapp.Models.Article;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomView> {

    Context context;
    List<Article> articleList;
    SelectArticle selectArticle;

    public CustomAdapter(Context context, List<Article> articleList, SelectArticle selectArticle) {
        this.context = context;
        this.articleList = articleList;
        this.selectArticle = selectArticle;
    }

    @NonNull
    @Override
    public CustomView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomView(LayoutInflater.from(context).inflate(R.layout.headline_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomView holder, int position) {

        holder.title.setText(articleList.get(position).getTitle());
        holder.source.setText(articleList.get(position).getSource().getName());

        if (articleList.get(position).getUrlToImage() != null){
            Picasso.get().load(articleList.get(position).getUrlToImage()).into(holder.headline_img);
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectArticle.onClickArticle(articleList.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }
}
