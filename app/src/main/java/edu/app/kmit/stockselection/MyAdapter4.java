package edu.app.kmit.stockselection;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Admin on 1/10/2019.
 */

class MyAdapter4 extends  RecyclerView.Adapter<MyAdapter4.MyViewHolder> {
    private List<Goods> mDataset;
     private Context apc;
    private String temp;
    public  class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        public TextView product,rate,warranty,quantity,brand,date;
        public MyViewHolder(View v) {
            super(v);
           apc=v.getContext();
            product = v.findViewById(R.id.productname);
            rate = v.findViewById(R.id.rate);
            warranty = v.findViewById(R.id.warranty);
            quantity = v.findViewById(R.id.quantity);
            brand = v.findViewById(R.id.brand);
             product.setOnClickListener(this);
            date = v.findViewById(R.id.datetime);

        }
        public void onClick(View view) {
            int pos=getAdapterPosition();
            String id=mDataset.get(pos).getProductname();
            Intent it=new Intent(apc,ExploreGoods.class);
            it.putExtra("id",id);
            apc.startActivity(it);
        }

    }
    public MyAdapter4(List<Goods> myDataset2) {
        mDataset=myDataset2;

    }
    @Override
    public MyAdapter4.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview2, parent, false);
        return new MyAdapter4.MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        temp=mDataset.get(position).getProductname();
        holder.product.setText(temp);
        temp=apc.getString(R.string.rate)+" : "+mDataset.get(position).getRate();
        holder.rate.setText(temp);
        temp=apc.getString(R.string.warranty)+" : "+mDataset.get(position).getWarranty();
        holder.warranty.setText(temp);
        temp=apc.getString(R.string.quantity)+" : "+mDataset.get(position).getQuantity();
        holder.quantity.setText(temp);
        temp=apc.getString(R.string.brand)+" : "+mDataset.get(position).getBrand();
        holder.brand.setText(temp);

       temp=apc.getString(R.string.date)+" : "+mDataset.get(position).getDate()+" "+mDataset.get(position).getTime();
        holder.date.setText(temp);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }


}
