package com.example.mynewsapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class CustomView extends RecyclerView.ViewHolder {

    TextView title, source;
    ImageView headline_img;

    CardView cardView;



    public CustomView(@NonNull View itemView) {
        super(itemView);

        title = itemView.findViewById(R.id.title);
        source = itemView.findViewById(R.id.source);
        headline_img = itemView.findViewById(R.id.headline_img);
        cardView = itemView.findViewById(R.id.container);
    }
}
