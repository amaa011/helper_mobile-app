package com.example.foodordring.Helper;

import android.content.Context;
import android.widget.Toast;

import com.example.foodordring.Domain.FoodDomain;
import com.example.foodordring.inter.ChangeNumberitemListener;

import java.util.ArrayList;

public class MangementCart {
    private Context context;
    private TinyDB tinyDB;


    public MangementCart(Context context){

        this.context=context;
        this.tinyDB=new TinyDB(context);

    }
    public void insertFood(FoodDomain item){
        ArrayList<FoodDomain>listFood=getListCart();
                boolean existAlready=false;
                int n=0;
                for (int i=0; i< listFood.size();i++){
                    if (listFood.get(i).getTitle().equals(item.getTitle())){
                        existAlready=true;
                        n=i;
                        break;

                    }

                }
                if (existAlready) {
                    listFood.get(n).setNumberIncart(item.getNumberIncart());
                }else {
                    listFood.add(item);

                }
                tinyDB.putListObject("cartList",listFood);
        Toast.makeText(context,"Added To cart",Toast.LENGTH_SHORT).show();

    }

    public ArrayList<FoodDomain>getListCart(){
return tinyDB.getListObject("cartList");

    }
    public void plusNumberFood(ArrayList<FoodDomain> listfood, int position, ChangeNumberitemListener changeNumberitemListener) {
        listfood.get(position).setNumberIncart(listfood.get(position).getNumberIncart() + 1);
        tinyDB.putListObject("cartList", listfood);
        changeNumberitemListener.changed();
    }

    public void minusNumberFood(ArrayList<FoodDomain> listfood, int position, ChangeNumberitemListener changeNumberitemListener){
        if (listfood.get(position).getNumberIncart()==1) {
            listfood.remove(position);
        }else{
            listfood.get(position).setNumberIncart(listfood.get(position).getNumberIncart()-1);
        }
        tinyDB.putListObject("cartList",listfood);
        changeNumberitemListener.changed();
    }



    public Double getTotalFee(){
        ArrayList <FoodDomain> listfood=getListCart();
        double fee=0;
        for(int i =0; i<listfood.size();i++){
            fee=fee+(listfood.get(i).getFee() *listfood.get(i).getNumberIncart());
        }
return fee;
    }
}
