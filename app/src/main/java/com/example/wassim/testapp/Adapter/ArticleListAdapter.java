package com.example.wassim.testapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.wassim.testapp.Models.Article;
import com.example.wassim.testapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public  class ArticleListAdapter extends RecyclerView.Adapter<ArticleListAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Article> articleArrayList;

    public ArticleListAdapter(Context context, ArrayList<Article> articleArrayList) {
        this.context = context;
        this.articleArrayList = articleArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Picasso.get().load(articleArrayList.get(position).getThumbnail()).into(holder.article_img);
        holder.article_name.setText(articleArrayList.get(position).getName());
        holder.article_subtitle.setText(articleArrayList.get(position).getSubtitle());


    }

    @Override
    public int getItemCount() {
        return articleArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView article_img;
        TextView article_name,article_subtitle;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            article_img = (ImageView) itemView.findViewById(R.id.article_image);
            article_name =(TextView) itemView.findViewById(R.id.article_name);
            article_subtitle =(TextView) itemView.findViewById(R.id.article_subtitle);

        }
    }

}
