package edu.app.kmit.stockselection;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Admin on 1/5/2019.
 */
@Entity
public class Goods {

    public Goods(int uid,String productname,String rate,String quantity,String warranty,String brand,String info,String date,String time,String sid ){

        this.uid=uid;
        this.productname=productname;
        this.rate=rate;
        this.quantity=quantity;
        this.warranty=warranty;
        this.brand= brand;
        this.info=info;
        this.date=date;
        this.time=time;
        this.sid=sid;
    }
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "productname")
    public String productname;

    @ColumnInfo(name = "rate")
    public String rate;

    @ColumnInfo(name = "quantity")
    public String quantity;

    @ColumnInfo(name = "warranty")
    public String warranty;

    @ColumnInfo(name = "brand")
    public String brand;

    @ColumnInfo(name = "info")
    public String info;

    @ColumnInfo(name = "date")
    public String date;

    @ColumnInfo(name = "time")
    public String time;

    @ColumnInfo(name = "sid")
    public String sid;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    public String getWarranty() {
        return warranty;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }



    public String getRate() {
        return rate;
    }



    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity= quantity;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getUid() {
        return uid;
    }

}
