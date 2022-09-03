package com.example.mynewsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mynewsapp.Models.Article;
import com.squareup.picasso.Picasso;

public class ArticleActivity extends AppCompatActivity {

    Article article;

    TextView selected_article_title;
    TextView selected_article_author;
    TextView selected_article_publishedAt;
    TextView selected_article_description;
    TextView selected_article_content;

    ImageView selected_article_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        selected_article_title = findViewById(R.id.selected_article_title);
        selected_article_author = findViewById(R.id.selected_article_author);
        selected_article_publishedAt = findViewById(R.id.selected_article_published_at);
        selected_article_description = findViewById(R.id.selected_article_description);
        selected_article_content = findViewById(R.id.selected_article_content);

        selected_article_image = findViewById(R.id.selected_article_image);

        article = (Article) getIntent().getSerializableExtra("data");


        selected_article_title.setText(article.getTitle());
        selected_article_author.setText(article.getAuthor());
        selected_article_publishedAt.setText(article.getPublishedAt());
        selected_article_description.setText(article.getDescription());
        selected_article_content.setText(article.getContent());

        Picasso.get().load(article.getUrlToImage()).into(selected_article_image);
    }
}