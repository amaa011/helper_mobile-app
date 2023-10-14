package com.example.foodordring.Adaptor;

import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodordring.Domain.FoodDomain;
import com.example.foodordring.R;
import com.example.foodordring.ShowdetailActivity;

import java.util.ArrayList;

public class PopularAdaptor extends RecyclerView.Adapter<PopularAdaptor.ViewHolder> {
ArrayList<FoodDomain> popularfood;

    public PopularAdaptor(ArrayList<FoodDomain> popularfood) {
        this.popularfood = popularfood;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_populer,parent,false);
        return new ViewHolder(inflate);
    }


    @Override
    public void onBindViewHolder(@NonNull PopularAdaptor.ViewHolder holder, int position) {
holder.title.setText(popularfood.get(position).getTitle());
holder.fee.setText(String.valueOf(popularfood.get(position).getFee()));

     int drawableresource=holder.itemView.getContext().getResources().getIdentifier(popularfood.get(position).getPic(),"drawable",holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
        .load(drawableresource)
                .into(holder.pic);
        holder.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Intent intent=new Intent(holder.itemView.getContext(), ShowdetailActivity.class);
                 intent.putExtra("object",popularfood.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return popularfood.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title,fee;
        ImageView pic;
        TextView  addBtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            fee=itemView.findViewById(R.id.fee);
            pic=itemView.findViewById(R.id.pic);
            addBtn=itemView.findViewById(R.id.addBtn);

        }
    }
}
