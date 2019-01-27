package edu.app.kmit.stockselection;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Admin on 12/26/2018.
 */
public  class Fragment1 extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private  AppDatabase db;
    private List<Shop> myDataset;
    private RecyclerView.LayoutManager mLayoutManager;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);

        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        db = Room.databaseBuilder(getContext(),
                AppDatabase.class, "stock").allowMainThreadQueries().build();


        myDataset=db.shopDao().getTwo();
        mAdapter = new MyAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);
        return rootView;
    }

//    @Override
//    public void onClick(View view) {
//        int pos=mRecyclerView.getChildLayoutPosition(view);
//        String id=String.valueOf(myDataset.get(pos).getUid());
//        Intent it=new Intent(getContext(),ExploreShop.class);
//        it.putExtra("id",id);
//        startActivity(it);
//        getActivity().finish();
//    }
}