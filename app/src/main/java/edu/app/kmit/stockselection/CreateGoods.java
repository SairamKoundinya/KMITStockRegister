package edu.app.kmit.stockselection;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CreateGoods extends AppCompatActivity {

    private TextView shopn,title;
    private String id,id2;
   private List<Goods> list;
    private String pn,rate,war,in,qu,br;
    private AutoCompleteTextView pn1;
    private Spinner sp;
    private TextInputEditText war1,qu1,br1,in1;
    private RatingBar rate1;
    private AppDatabase db;
    private List<Integer> myDataset;
    private  List<String> myDataset2;
    private  List<String> myDataset4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_goods);
        shopn=findViewById(R.id.goodshopname);
        sp=findViewById(R.id.spinner);
        title=findViewById(R.id.title);
       // Intent it=getIntent();
       // id=it.getStringExtra("id");
       // name=it.getStringExtra("name");
       // st=getString(R.string.ind)+name;
       // shopn.setText(st);
         // Toast.makeText(this,name,Toast.LENGTH_SHORT).show();
        pn1=findViewById(R.id.productname);
        rate1=findViewById(R.id.rate);
       // rate1.setRating(5);
        war1=findViewById(R.id.warranty);
        qu1=findViewById(R.id.quantity);
        br1=findViewById(R.id.brand);
        in1=findViewById(R.id.extrainfo);


        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "stock").allowMainThreadQueries().build();
        myDataset=db.shopDao().getUid();
        myDataset2=db.shopDao().getName();
        myDataset4=db.goodsDao().getName();


        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,myDataset2);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);
        ArrayAdapter<String> adapter2=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,myDataset4);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pn1.setAdapter(adapter2);
        Intent it=getIntent();
        id=it.getStringExtra("id");
        if(id!=null){
            list= db.goodsDao().getGoods(Integer.parseInt(id));
            rate1.setRating(Float.parseFloat(list.get(0).getRate()));
            pn1.setText(list.get(0).getProductname());
            war1.setText(list.get(0).getWarranty());
            qu1.setText(list.get(0).getQuantity());
            br1.setText(list.get(0).getBrand());
            in1.setText(list.get(0).getInfo());
            title.setText(getString(R.string.egood));
            String sid=list.get(0).getSid();
            for(int i=0;i<myDataset.size();i++){
                if(Integer.parseInt(sid)==myDataset.get(i)){
                    sp.setSelection(i);
                    break;
                }
            }
        }


    }
    public void clear(View v){
                in1.getText().clear();

    }
    public void save(View v){
        pn=pn1.getText().toString();
        rate=String.valueOf(rate1.getRating());
        qu=qu1.getText().toString();
        war=war1.getText().toString();
        br=br1.getText().toString();
        in=in1.getText().toString();
        if(TextUtils.isEmpty(pn)) pn="Not Available";
        if(TextUtils.isEmpty(qu)) qu="Not Available";
        if(TextUtils.isEmpty(war)) war="Not Available";
        if(TextUtils.isEmpty(br)) br="Not Available";
        if(TextUtils.isEmpty(in)) in="Not Available";
        id2=String.valueOf(myDataset.get(sp.getSelectedItemPosition()));

        String vl=db.goodsDao().check(pn,id2);
        if(vl==null) {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            String datestr = sdf.format(date);

            sdf = new SimpleDateFormat("hh:mm:ss a");
            String timestr = sdf.format(date);


            if (id == null) {
                Goods gd = new Goods(0, pn, rate, qu, war, br, in, datestr, timestr, id2);
                db.goodsDao().insertAll(gd);
                Toast.makeText(this, "New Product created Successfully", Toast.LENGTH_SHORT).show();

            } else {
                Goods gd = new Goods(list.get(0).getUid(), pn, rate, qu, war, br, in, datestr, timestr, id2);
                db.goodsDao().update(gd);
                Toast.makeText(this, "Product details modified Successfully", Toast.LENGTH_SHORT).show();
            }
            move();
        }
        else{

            Toast.makeText(this,"Two Similar Products for One Shop can't be write",Toast.LENGTH_LONG).show();
        }
    }
    public void back(View v){
        move();
    }

    public void move(){

        startActivity(new Intent(CreateGoods.this,MainActivity.class));
        finish();
    }

}
