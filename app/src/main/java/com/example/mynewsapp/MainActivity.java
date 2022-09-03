package com.example.mynewsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mynewsapp.Models.APIResponse;
import com.example.mynewsapp.Models.Article;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SelectArticle, View.OnClickListener{

    public final String defaultCategory = "general";

    RecyclerView recyclerView;
    CustomAdapter customAdapter;
    ProgressDialog progressDialog;

    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7;

    SearchView searchView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createButton();
        createSearchBar();


        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading...");
        progressDialog.show();

        Requests requests = new Requests(this);
        requests.getNewsArticles(listener, defaultCategory, null);


    }

    private void createSearchBar() {

        searchView = findViewById(R.id.search_article);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Requests requests = new Requests(MainActivity.this);
                requests.getNewsArticles(listener, defaultCategory, query);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void createButton(){

        btn1 = findViewById(R.id.btn_1);
        btn1.setOnClickListener(this);

        btn2 = findViewById(R.id.btn_2);
        btn2.setOnClickListener(this);

        btn3 = findViewById(R.id.btn_3);
        btn3.setOnClickListener(this);

        btn4 = findViewById(R.id.btn_4);
        btn4.setOnClickListener(this);

        btn5 = findViewById(R.id.btn_5);
        btn5.setOnClickListener(this);

        btn6 = findViewById(R.id.btn_6);
        btn6.setOnClickListener(this);

        btn7 = findViewById(R.id.btn_7);
        btn7.setOnClickListener(this);

    }

    private final Requests.Listener<APIResponse> listener = new Requests.Listener<APIResponse>() {
        @Override
        public void onReceivedData(List<Article> articles, String msg) {

            if (articles.isEmpty()){
                Toast.makeText(MainActivity.this, "No Articles Found", Toast.LENGTH_SHORT);
            }else{
                showArticles(articles);
                progressDialog.dismiss();
            }

        }

        @Override
        public void onError(String msg) {
            Toast.makeText(MainActivity.this, "Error",Toast.LENGTH_SHORT);

        }
    };

    private void showArticles(List<Article> articles){

        recyclerView = findViewById(R.id.recycler_main);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));

        customAdapter = new CustomAdapter(this, articles, this);

        recyclerView.setAdapter(customAdapter);
    }


    @Override
    public void onClickArticle(Article article) {

        startActivity(new Intent(MainActivity.this, ArticleActivity.class).putExtra("data", article));

    }

    @Override
    public void onClick(View view) {

        Button button = (Button) view;

        String category = button.getText().toString().toLowerCase();

        progressDialog.setTitle("Loading...");
        progressDialog.show();

        Requests requests = new Requests(this);
        requests.getNewsArticles(listener, category, null);
    }
}

