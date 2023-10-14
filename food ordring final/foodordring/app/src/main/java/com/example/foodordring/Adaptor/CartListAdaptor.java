package com.example.foodordring.Adaptor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodordring.Domain.FoodDomain;
import com.example.foodordring.Helper.MangementCart;
import com.example.foodordring.R;
import com.example.foodordring.inter.ChangeNumberitemListener;

import java.util.ArrayList;


public class CartListAdaptor extends RecyclerView.Adapter<CartListAdaptor.ViewHolder> {

    private ArrayList<FoodDomain>foodDomains;
    private MangementCart mangementCart;
    private ChangeNumberitemListener changeNumberitemListener;

    public CartListAdaptor(ArrayList<FoodDomain> foodDomains, Context context, ChangeNumberitemListener changeNumberitemListener) {
        this.foodDomains = foodDomains;
        this.mangementCart = new MangementCart(context);
        this.changeNumberitemListener = changeNumberitemListener;

    }



    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart,parent,false);

        return new ViewHolder(inflate )  ;
    }


    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        // position variable is declared and initialized here
        holder.TitleTxt.setText(foodDomains.get(position).getTitle());
        holder.feeEachitem.setText(String.valueOf(foodDomains.get(position).getFee()));
        holder.totaleachitem.setText(String.valueOf(Math.round((foodDomains.get(position).getNumberIncart() * foodDomains.get(position).getFee() * 100) / 100)));
        holder.num.setText(String.valueOf(foodDomains.get(position).getNumberIncart()));

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(
                foodDomains.get(position).getPic(), "drawable", holder.itemView.getContext().getPackageName());

        if (holder.itemView != null && drawableResourceId != 0 && holder.picfood != null) {
            Glide.with(holder.itemView.getContext())
                    .load(drawableResourceId)
                    .into(holder.picfood);
        }

        holder.plusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mangementCart.plusNumberFood(foodDomains, position, new ChangeNumberitemListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberitemListener.changed();

                    }
                });

            }
        });
        holder.MinusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mangementCart.minusNumberFood(foodDomains, position, new ChangeNumberitemListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberitemListener.changed();
                    }
                });
            }
        });


    }


    @Override
    public int getItemCount() {
        return foodDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView TitleTxt,feeEachitem;
        ImageView picfood,plusbtn,MinusBtn;
        TextView totaleachitem,num;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            TitleTxt=itemView.findViewById(R.id.TitleTxtt);
            feeEachitem=itemView.findViewById(R.id.feeEachhit);
            picfood=itemView.findViewById(R.id.picfood);
            totaleachitem=itemView.findViewById(R.id.totaleachhit);
            num=itemView.findViewById(R.id.numberitemtxt);
            plusbtn=itemView.findViewById(R.id.plusbtn);
            MinusBtn=itemView.findViewById(R.id.MinusBtn);

        }
    }
}
