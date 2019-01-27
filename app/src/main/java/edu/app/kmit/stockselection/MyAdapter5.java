package edu.app.kmit.stockselection;

import android.app.AlertDialog;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Admin on 1/10/2019.
 */

class MyAdapter5 extends  RecyclerView.Adapter<MyAdapter5.MyViewHolder> {
    private List<Goods> mDataset;
    private List<String> mDataset1;
    private Context apc;
    private String temp;
    private AppDatabase db;
    public  class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {
        // each data item is just a string in this case
        public TextView shop,rate,warranty,quantity,brand,info,date;
        private ImageView edit,del;

        public MyViewHolder(View v) {
            super(v);
            apc=v.getContext();
            shop = v.findViewById(R.id.shopname);
            shop.setOnClickListener(this);
            del=v.findViewById(R.id.delete);
            del.setOnClickListener(this);
            edit=v.findViewById(R.id.edit);
            edit.setOnClickListener(this);
            rate = v.findViewById(R.id.rate);
            warranty = v.findViewById(R.id.warranty);
            quantity = v.findViewById(R.id.quantity);
            brand = v.findViewById(R.id.brand);
            info = v.findViewById(R.id.info);
            date = v.findViewById(R.id.datetime);
            db = Room.databaseBuilder(apc,
                    AppDatabase.class, "stock").allowMainThreadQueries().build();


        }

        @Override
        public void onClick(View view) {
           final int pos = getAdapterPosition();
            switch (view.getId()) {
                case R.id.shopname :

                String id = mDataset.get(pos).getSid();
                Intent it = new Intent(apc, ExploreShop.class);
                it.putExtra("id", id);
                apc.startActivity(it);
                break;
                case R.id.edit:
                    String id1 = String.valueOf(mDataset.get(pos).getUid());
                  //  Toast.makeText(apc,String.valueOf(id1),Toast.LENGTH_SHORT).show();
                     it = new Intent(apc, CreateGoods.class);
                    it.putExtra("id", id1);
                    apc.startActivity(it);
                    break;
                case R.id.delete:

                    AlertDialog.Builder builder=new AlertDialog.Builder(apc);
                    builder.setMessage(R.string.delmsg);
                    builder.setTitle(R.string.deltitle2);
                    builder.setIcon(R.drawable.delete2);
                    builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            db.goodsDao().deleteGood(new Goods(mDataset.get(pos).getUid(),mDataset.get(pos).getProductname(),mDataset.get(pos).getRate(),mDataset.get(pos).getQuantity(),mDataset.get(pos).getWarranty(),mDataset.get(pos).getBrand(),mDataset.get(pos).getInfo(),mDataset.get(pos).getDate(),mDataset.get(pos).getTime(),mDataset.get(pos).getSid()));
                            apc.startActivity(new Intent(apc,MainActivity.class));
                            Toast.makeText(apc,"Removed Successfully",Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.setNegativeButton(R.string.no,null);
                    AlertDialog dialog=builder.create();
                    dialog.show();
                    break;

            }
        }
    }
    public MyAdapter5(List<Goods> myDataset2,List<String> myDataset1) {
        mDataset=myDataset2;
        mDataset1=myDataset1;

    }
    @Override
    public MyAdapter5.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        // create a new view
        View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview3, parent, false);
        return new MyAdapter5.MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        temp=mDataset1.get(position);
        holder.shop.setText(temp);
        temp=apc.getString(R.string.rate)+" : "+mDataset.get(position).getRate();
        holder.rate.setText(temp);
        temp=apc.getString(R.string.warranty)+" : "+mDataset.get(position).getWarranty();
        holder.warranty.setText(temp);
        temp=apc.getString(R.string.quantity)+" : "+mDataset.get(position).getQuantity();
        holder.quantity.setText(temp);
        temp=apc.getString(R.string.brand)+" : "+mDataset.get(position).getBrand();
        holder.brand.setText(temp);
        temp=apc.getString(R.string.info)+" : "+mDataset.get(position).getInfo();
        holder.info.setText(temp);
        temp=apc.getString(R.string.date)+" : "+mDataset.get(position).getDate()+" "+mDataset.get(position).getTime();
        holder.date.setText(temp);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }


}
