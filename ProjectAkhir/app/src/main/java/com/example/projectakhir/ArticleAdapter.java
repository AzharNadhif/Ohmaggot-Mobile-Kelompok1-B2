package com.example.projectakhir;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private Context context;
    private List<DataClass> dataList;

    public void setSearchList(List<DataClass> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    public ArticleAdapter(Context context, List<DataClass> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.recImage.setImageResource(dataList.get(position).getDataImage());
        holder.recTitle.setText(dataList.get(position).getDataTitle());
        holder.recDesc.setText(dataList.get(position).getDataDesc());
        holder.recTgl.setText(dataList.get(position).getDataTgl());


        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;

                // Determine which activity to start based on the position
                switch (holder.getAdapterPosition()) {
                    case 0:
                        intent = new Intent(context, DetailArticle1.class);
                        break;
                    case 1:
                        intent = new Intent(context, DetailArticle2.class);
                        break;
                    case 2:
                        intent = new Intent(context, DetailArticle3.class);
                        break;
                    default:
                        // Handle the case when an unknown position is clicked
                        return;
                }

                // Pass data to the intent
                intent.putExtra("Image", dataList.get(holder.getAdapterPosition()).getDataImage());
                intent.putExtra("Title", dataList.get(holder.getAdapterPosition()).getDataTitle());
                intent.putExtra("Desc", dataList.get(holder.getAdapterPosition()).getDataDesc());
                intent.putExtra("Tanggal", dataList.get(holder.getAdapterPosition()).getDataTgl());

                // Start the selected activity
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}

class MyViewHolder extends RecyclerView.ViewHolder{
    ImageView recImage;
    TextView recTitle, recDesc, recTgl;
    CardView recCard;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        recImage = itemView.findViewById(R.id.recImage);
        recTitle = itemView.findViewById(R.id.recTitle);
        recDesc = itemView.findViewById(R.id.recDesc);
        recTgl = itemView.findViewById(R.id.recTgl);
        recCard = itemView.findViewById(R.id.recCard);
    }
}

