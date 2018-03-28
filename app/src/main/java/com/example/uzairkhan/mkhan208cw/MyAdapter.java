package com.example.uzairkhan.mkhan208cw;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Uzairkhan on 20/03/2018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    ArrayList<FeedItem>feedItems;
    Context context;
    public MyAdapter(Context context,ArrayList<FeedItem>feedItems){
        this.feedItems=feedItems;
        this.context= context;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view= LayoutInflater.from(context).inflate(R.layout.currentincidents_rss,parent,false);
        View view1= LayoutInflater.from(context).inflate(R.layout.activity_planned_roadwork,parent,false);
        MyViewHolder holder= new MyViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position){

        final FeedItem current= feedItems.get(position);
        holder.Title.setText(current.getTitle());
        holder.Date.setText(current.getPubDate());
        holder.Description.setText(current.getDescription());
        holder.Title.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent= new Intent(context,MapsActivity.class);
                intent.putExtra("coordinate", current.getCoordinate());
                context.startActivity(intent);
            }
        });

    }
    @Override
    public int getItemCount (){
        return feedItems.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView Title,Description,Date;
        public MyViewHolder(View itemView){
            super(itemView);
            Title= (TextView) itemView.findViewById(R.id.textTitle);
            Date= (TextView) itemView.findViewById(R.id.textDate);
            Description = (TextView) itemView.findViewById(R.id.textDesc);
        }
    }
}
