package com.example.foodordring;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.foodordring.Adaptor.CategoryAdaptor;
import com.example.foodordring.Adaptor.PopularAdaptor;
import com.example.foodordring.Domain.CatigoryDomain;
import com.example.foodordring.Domain.FoodDomain;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter, adapter2;
    private RecyclerView recyclerViewCategoryList,recyclerViewpopulerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerViewCategory();
        recyclerviewpopuler();
        bottmnavigation();
    }
    private void bottmnavigation(){

        FloatingActionButton floatingActionButton= findViewById(R.id.cartbtn);
        LinearLayout homeBtn=findViewById(R.id.homebtn);
        LinearLayout Profilebtn=findViewById(R.id.Profilebtn);
        LinearLayout Payment=findViewById(R.id.Supportbtn);
        LinearLayout Contact_us=findViewById(R.id.Settingbtn);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( MainActivity.this, AddToCart.class));

            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( MainActivity.this, MainActivity.class));

            }
        });
        Profilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( MainActivity.this, edit_profile.class));

            }


        });
        Payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( MainActivity.this, payment.class));

            }

        });
        Contact_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( MainActivity.this, payment.class));

            }

        });










    }

    private void recyclerViewCategory() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewCategoryList=findViewById(R.id.recyclerView);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<CatigoryDomain> category=new ArrayList<>();
        category.add(new CatigoryDomain("Pizza","cat_1"));
        category.add(new CatigoryDomain("Burger","cat_2"));
        category.add(new CatigoryDomain("HOTDOG","cat_3"));
        category.add(new CatigoryDomain("Drink ","cat_4"));
        category.add(new CatigoryDomain("Dount","cat_5"));

        adapter=new CategoryAdaptor(category);
        recyclerViewCategoryList.setAdapter(adapter);


    }
    private void recyclerviewpopuler(){
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewpopulerList=findViewById(R.id.recyclerView2);
        recyclerViewpopulerList.setLayoutManager(linearLayoutManager);

        ArrayList<FoodDomain> foodList=new ArrayList<>();
        foodList.add(new FoodDomain("pepperoni Pizza","pizza","slices Pizza,Mozrila cheese,grand black peper",9.7));
        foodList.add(new FoodDomain("Cheese Burger","pop_2","beef,Gounda Cheese ,tomato",9.0));
        foodList.add(new FoodDomain("Vegtable pizza","pop_3","Olive oil,pitted kalamata, Vegtable oile,tomato",10.10));

        adapter2=new PopularAdaptor(foodList);
        recyclerViewpopulerList.setAdapter(adapter2);



    }




}

