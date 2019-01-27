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

import edu.app.kmit.stockselection.R;

/**
 * Created by Admin on 12/26/2018.
 */

public class Fragment2 extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private  AppDatabase db;
    private RecyclerView.LayoutManager mLayoutManager;

    private  List<String> myDataset2;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment2, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);

        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        db = Room.databaseBuilder(getContext(),
                AppDatabase.class, "stock").allowMainThreadQueries().build();



      myDataset2=db.goodsDao().getName();
        mAdapter = new MyAdapter3(myDataset2);
        mRecyclerView.setAdapter(mAdapter);
         return rootView;
    }

}
