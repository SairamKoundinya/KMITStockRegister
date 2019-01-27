package edu.app.kmit.stockselection;

import android.app.AlertDialog;
import android.app.Dialog;
import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ExploreShop extends AppCompatActivity {



    private TextView sn1,vn1,add1,ph1,em1,in1,ws1,tm1;
   // private String sn,vn,add,ph,em,in,ws,tm;
    private String id,temp;
    private List<Shop> myDataset;
    private List<Goods> myDataset2;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private  AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_shop);
        sn1=findViewById(R.id.goodshopname);
        sn1.setSelected(true);
        vn1=findViewById(R.id.vendorname);
        vn1.setSelected(true);
        add1=findViewById(R.id.address);
        add1.setSelected(true);
        ph1=findViewById(R.id.phone);
        em1=findViewById(R.id.email);
        em1.setSelected(true);
        in1=findViewById(R.id.info);
        in1.setSelected(true);
        ws1=findViewById(R.id.website);
        tm1=findViewById(R.id.timings);
        ws1.setSelected(true);
        mRecyclerView = (RecyclerView)findViewById(R.id.my_recycler_view2);

        mLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        Intent it=getIntent();
         id=it.getStringExtra("id");

        db = Room.databaseBuilder(ExploreShop.this,
                AppDatabase.class, "stock").allowMainThreadQueries().build();

        myDataset=db.shopDao().getShop(Integer.parseInt(id));
        temp=myDataset.get(0).getShopname();
        sn1.setText(temp);
        temp=getString(R.string.vendorname)+" : "+myDataset.get(0).getVendorname();
        vn1.setText(temp);
        temp=getString(R.string.address)+" : "+myDataset.get(0).getAddress();
        add1.setText(temp);
        temp=getString(R.string.phone)+" : "+myDataset.get(0).getPhone();
        ph1.setText(temp);
        temp=getString(R.string.email)+" : "+myDataset.get(0).getEmail();
        //Toast.makeText(this,temp,Toast.LENGTH_SHORT).show();
        em1.setText(temp);
        temp=getString(R.string.info)+" : "+myDataset.get(0).getInfo();
        in1.setText(temp);
        temp=getString(R.string.website)+" : "+myDataset.get(0).getWebsite();
        ws1.setText(temp);
        temp=getString(R.string.timings)+" : "+myDataset.get(0).getTimings();
        tm1.setText(temp);

        myDataset2=db.goodsDao().getGood(Integer.parseInt(id));
        mAdapter = new MyAdapter4(myDataset2);
        mRecyclerView.setAdapter(mAdapter);


    }

    public void back(View v){

        startActivity(new Intent(ExploreShop.this,MainActivity.class));
        finish();
    }

    public void edit(View v){

        Intent it=new Intent(ExploreShop.this,CreateShop.class);
        it.putExtra("id",id);
        startActivity(it);
    }
    public void delete(View v){

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage(R.string.delmsg);
        builder.setTitle(R.string.deltitle);
        builder.setIcon(R.drawable.delete2);
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                db.shopDao().deleteShop(new Shop(myDataset.get(0).getUid(),myDataset.get(0).getShopname(),myDataset.get(0).getAddress(),myDataset.get(0).getVendorname(),myDataset.get(0).getPhone(),myDataset.get(0).getEmail(),myDataset.get(0).getInfo(),myDataset.get(0).getWebsite(),myDataset.get(0).getTimings(),myDataset.get(0).getDate(),myDataset.get(0).getTime()));
               for(Goods gd : myDataset2) {
                   db.goodsDao().deleteGood(gd);
               }
                startActivity(new Intent(ExploreShop.this,MainActivity.class));
                finish();
                Toast.makeText(ExploreShop.this,"Removed Successfully",Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton(R.string.no,null);
       AlertDialog dialog=builder.create();
       dialog.show();
    }

}
