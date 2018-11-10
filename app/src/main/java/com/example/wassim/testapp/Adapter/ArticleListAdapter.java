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
import com.example.wassim.testapp.Utils.Listener.CustomItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public  class ArticleListAdapter extends RecyclerView.Adapter<ArticleListAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Article> articleArrayList;
    CustomItemClickListener listener;

    public ArticleListAdapter(Context context, ArrayList<Article> articleArrayList,CustomItemClickListener listener) {
        this.context = context;
        this.articleArrayList = articleArrayList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        final ViewHolder mViewHolder = new ViewHolder(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, mViewHolder.getAdapterPosition());
            }
        });

        return mViewHolder;
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
            article_img =  itemView.findViewById(R.id.article_image);
            article_name = itemView.findViewById(R.id.article_name);
            article_subtitle = itemView.findViewById(R.id.article_subtitle);

        }
    }






}
