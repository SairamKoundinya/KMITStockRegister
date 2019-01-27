package edu.app.kmit.stockselection;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class CreateShop extends AppCompatActivity {

    private String sn,add,vn,ph,em,in,ws,tm,id;
    private TextInputEditText sn1,add1,vn1,ph1,em1,in1,ws1,tm1;
    private AppDatabase db;
    private List<Shop> list;
    private TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_shop);
        sn1=findViewById(R.id.shopname);
        add1=findViewById(R.id.address);
        vn1=findViewById(R.id.vendorname);
        ph1=findViewById(R.id.phone);
        em1=findViewById(R.id.email);
        in1=findViewById(R.id.extrainfo);
        ws1=findViewById(R.id.website);
        tm1=findViewById(R.id.timings);
        title=findViewById(R.id.title);

        title.setText(getString(R.string.menu1));
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "stock").allowMainThreadQueries().build();

        Intent it=getIntent();
        id=it.getStringExtra("id");
        if(id!=null){
           list= db.shopDao().getShop(Integer.parseInt(id));

           sn1.setText(list.get(0).getShopname());
           add1.setText(list.get(0).getAddress());
           vn1.setText(list.get(0).getVendorname());
           ph1.setText(list.get(0).getPhone());
           em1.setText(list.get(0).getEmail());
           in1.setText(list.get(0).getInfo());
           ws1.setText(list.get(0).getWebsite());
           tm1.setText(list.get(0).getTimings());
           title.setText(getString(R.string.eshop));
        }
    }

    public void clear(View v){
        int id=v.getId();
        switch (id) {
            case R.id.addressimg:
            add1.getText().clear(); break;
            case R.id.infoimg:
                in1.getText().clear(); break;
        }
    }

    public void save(View v){
        sn=sn1.getText().toString();
        add=add1.getText().toString();
        vn=vn1.getText().toString();
        ph=ph1.getText().toString();
        em=em1.getText().toString();
        in=in1.getText().toString();
        ws=ws1.getText().toString();
        tm=tm1.getText().toString();
        if(TextUtils.isEmpty(sn)) sn="Not Available";
        if(TextUtils.isEmpty(add)) add="Not Available";
        if(TextUtils.isEmpty(vn)) vn="Not Available";
        if(TextUtils.isEmpty(ph)) ph="Not Available";
        if(TextUtils.isEmpty(em)) em="Not Available";
        if(TextUtils.isEmpty(in)) in="Not Available";
        if(TextUtils.isEmpty(ws)) ws="Not Available";
        if(TextUtils.isEmpty(tm)) tm="Not Available";


        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
        String datestr=sdf.format(date);

         sdf=new SimpleDateFormat("hh:mm:ss a");
        String timestr=sdf.format(date);

        if(id==null) {
            Shop sh = new Shop(0, sn, add, vn, ph, em, in, ws, tm, datestr, timestr);
            db.shopDao().insertAll(sh);
            Toast.makeText(this,"New Shop created Successfully",Toast.LENGTH_SHORT).show();

        }
        else{
            Shop sh = new Shop(list.get(0).getUid(), sn, add, vn, ph, em, in, ws, tm, datestr, timestr);
            db.shopDao().update(sh);
            Toast.makeText(this,"Shop details modified Successfully",Toast.LENGTH_SHORT).show();
        }
        move();
    }

    public void back(View v){
        move();
    }

    public void move(){

        startActivity(new Intent(CreateShop.this,MainActivity.class));
       finish();
    }




}
