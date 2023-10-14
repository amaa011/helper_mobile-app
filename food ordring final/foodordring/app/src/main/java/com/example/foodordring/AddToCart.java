package com.example.foodordring;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.foodordring.Adaptor.CartListAdaptor;
import com.example.foodordring.Helper.MangementCart;
import com.example.foodordring.inter.ChangeNumberitemListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddToCart extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    private MangementCart mangementCart;
    private TextView itemstotaltxt ,Totalfeetxte,deltxt,delvtxt,itemstotaltxta,Totalfeetxtea,EmptyTxt,feetxte, totaltxt,TitleTxt;
    private double tax;
    private ScrollView scrollView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);

        mangementCart=new MangementCart(this);

        initView();
        initList();
        CaluclateCart();
        bottmnavigation();
    }
    private void bottmnavigation(){

        FloatingActionButton floatingActionButton= findViewById(R.id.cartbtn);
        LinearLayout homeBtn=findViewById(R.id.homebtn);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( AddToCart.this, AddToCart.class));

            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( AddToCart.this, MainActivity.class));

            }
        });
    }

    private void initView() {

        recyclerViewList=findViewById(R.id.recyclerView);
        Totalfeetxte=findViewById(R.id.Totalfeetxte);
        delvtxt=findViewById(R.id.delvtxt);
        Totalfeetxtea=findViewById(R.id.Taxtext);
        scrollView=findViewById(R.id.scrollView3);
        EmptyTxt=findViewById(R.id.EmptyTxt);
        totaltxt=findViewById(R.id.totaltxt);
        feetxte=findViewById(R.id.feetxte);
        recyclerViewList=findViewById(R.id.cartview);

    }
    private void initList(){
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter=new CartListAdaptor(mangementCart.getListCart(),this, new ChangeNumberitemListener() {
            @Override
            public void changed() {
                CaluclateCart();

            }
        });
        recyclerViewList.setAdapter(adapter);
        if (mangementCart.getListCart().isEmpty()){
            EmptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);

        }else {
            EmptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }



    }
    private void CaluclateCart(){
        double percentTax =0.02;
        double delviery=10;


        tax = Math.round((mangementCart.getTotalFee() * percentTax)*100)/100;
        double total=Math.round((mangementCart.getTotalFee()+tax+delviery)*100)/100;
        double itemtotal=Math.round(mangementCart.getTotalFee()*100)/100;

        Totalfeetxtea.setText("$" +itemtotal);
        feetxte.setText("$"+tax);
        delvtxt.setText("$"+delviery);
        totaltxt.setText("$"+total);





    }
}