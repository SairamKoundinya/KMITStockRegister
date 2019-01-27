package edu.app.kmit.stockselection;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Admin on 12/27/2018.
 */
public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.MyViewHolder> {
    private List<String> mDataset2;
    private List<Integer> mDataset;
    //private View.OnClickListener onClickListener=new SelectIndustry();

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // each data item is just a string in this case
        public TextView mTextView;
        private Context apc;
        public MyViewHolder(View v) {
            super(v);
            apc=v.getContext();
          //  v.setOnClickListener(this);
            mTextView = (TextView) v;
            mTextView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
           // int pos=mRecyclerView.getChildLayoutPosition(view);
            int pos=getAdapterPosition();

            String id=String.valueOf(mDataset.get(pos));
            String name=mDataset2.get(pos);
            Intent it=new Intent(apc,CreateGoods.class);
            it.putExtra("id",id);
            it.putExtra("name",name);
            apc.startActivity(it);
        }

    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter2(List<Integer> mDataset,List<String> mDataset2) {


        this.mDataset2 = mDataset2;
       this.mDataset=mDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter2.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_text_view2, parent, false);

        return new MyViewHolder(v);

    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(mDataset2.get(position));

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset2.size();
    }
}