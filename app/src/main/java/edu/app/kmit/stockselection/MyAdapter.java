package edu.app.kmit.stockselection;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<Shop> mDataset;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public  class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        public TextView mTextView,mTextView2,datetxt,timetxt;
        private Context apc;
        public MyViewHolder(View v) {
            super(v);
            apc=v.getContext();
            mTextView = v.findViewById(R.id.shopn);
            mTextView2 = v.findViewById(R.id.addres);
            datetxt = v.findViewById(R.id.date);
            timetxt = v.findViewById(R.id.time);
            mTextView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {

            int pos=getAdapterPosition();
            String id=String.valueOf(mDataset.get(pos).getUid());
            Intent it=new Intent(apc,ExploreShop.class);
            it.putExtra("id",id);
            apc.startActivity(it);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<Shop> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_text_view, parent, false);
        return new MyViewHolder(v);

    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(mDataset.get(position).getShopname());
        holder.mTextView2.setText(mDataset.get(position).getAddress());
        holder.datetxt.setText(mDataset.get(position).getDate());
        holder.timetxt.setText(mDataset.get(position).getTime());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}