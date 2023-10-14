package com.example.foodordring;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.foodordring.Domain.FoodDomain;
import com.example.foodordring.Helper.MangementCart;

public class ShowdetailActivity extends AppCompatActivity {
    private TextView AddtocartBtn;
    private TextView descraptionTxt,TitleTxt,priceTxt,NumberOrderTxt;
    private ImageView plusbtn,picfood,MinusBtn;
    private FoodDomain object;
    int numberOrder=1;
    private MangementCart mangementCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showdetail);

        mangementCart=new MangementCart(this);
        iniView();
        getBundle();
    }

    private void getBundle() {
        object= (FoodDomain) getIntent().getSerializableExtra("object");

        int drawableResourceId=this.getResources().getIdentifier(object.getPic(),"drawable",this.getPackageName());
        Glide.with(this)
                .load(drawableResourceId)
                .into(picfood);
        TitleTxt.setText(object.getTitle());
        priceTxt.setText("$"+object.getFee());
        descraptionTxt.setText(object.getDescription());
        NumberOrderTxt.setText(String.valueOf(numberOrder));
        plusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberOrder=numberOrder+1;
                NumberOrderTxt.setText(String.valueOf(numberOrder));
            }
        });
        MinusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numberOrder>1){
                    numberOrder=numberOrder-1;
                }
                NumberOrderTxt.setText(String.valueOf(numberOrder));
            }
        });
        AddtocartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                object.setNumberIncart(numberOrder);
                mangementCart.insertFood(object);
            }
        });
    }

    private void iniView() {
        AddtocartBtn=findViewById(R.id.AddtocartBtn);
        TitleTxt=findViewById(R.id.TitleTxtt);
        descraptionTxt=findViewById(R.id.descraptionTxt);
        priceTxt=findViewById(R.id.priceTxt);
        NumberOrderTxt=findViewById(R.id.NumberOrderTxt);
        plusbtn=findViewById(R.id.plusbtn);
        MinusBtn=findViewById(R.id.MinusBtn);
        picfood=findViewById(R.id.picfood);
    }
}