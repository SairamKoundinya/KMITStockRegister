package edu.app.kmit.stockselection;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ExploreGoods extends AppCompatActivity {

    private TextView pn1;
    private RecyclerView mRecyclerView;
    private List<Goods> myDataset;
   // private int[] myDataset2;
    private List<String> myDataset3;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private  AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_goods);


        pn1=findViewById(R.id.goodname);

        mRecyclerView = (RecyclerView)findViewById(R.id.my_recycler_view3);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        myDataset3=new ArrayList<>();
        Intent it=getIntent();
        String id=it.getStringExtra("id");

        db = Room.databaseBuilder(ExploreGoods.this,
                AppDatabase.class, "stock").allowMainThreadQueries().build();


        myDataset=db.goodsDao().getGood(id);
//        myDataset2=new int[myDataset.size()];
//       // int j=myDataset.size()-1;
        for(int i=0;i<myDataset.size() ; i++){
//            myDataset2[i]=Integer.parseInt);
            myDataset3.add(db.shopDao().getShopName(Integer.parseInt(myDataset.get(i).getSid())));
        }

       // myDataset3=db.shopDao().getName(myDataset2);
        pn1.setText(id);
       // myDataset2=db.goodsDao().getGood(Integer.parseInt(id));
        mAdapter = new MyAdapter5(myDataset,myDataset3);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void back(View v){

        startActivity(new Intent(ExploreGoods.this,MainActivity.class));
        finish();
    }
}
