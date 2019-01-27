package edu.app.kmit.stockselection;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Admin on 12/26/2018.
 */

@Entity
public class Shop {

    public Shop(int uid,String shopname,String address,String vendorname,String phone,String email,String info,String website,String timings,String date,String time ){

        this.uid=uid;
        this.shopname=shopname;
        this.address=address;
        this.vendorname=vendorname;
        this.phone=phone;
        this.email=email;
        this.info=info;
        this.website=website;
        this.timings=timings;
        this.date=date;
        this.time=time;
    }
    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "shopname")
    private String shopname;

    @ColumnInfo(name = "address")
    private String address;

    @ColumnInfo(name = "vendorname")
    private String vendorname;

    @ColumnInfo(name = "phone")
    public String phone;

    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "info")
    private String info;

    @ColumnInfo(name = "website")
    private String website;

    @ColumnInfo(name = "timings")
    private String timings;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "time")
    private String time;

    public String getDate() {
        return date;
    }

   // public void setDate(String date) {
     //   this.date = date;
    //}

    public String getTime() {
        return time;
    }

//    public void setTime(String time) {
//        this.time = time;
//    }

    public String getEmail() {
        return email;
    }

//    public void setEmail(String email) {
//        this.email = email;
//    }

    public String getInfo() {
        return info;
    }

//    public void setInfo(String info) {
//        this.info = info;
//    }

//    public void setPhone(String phone) {
//        this.phone = phone;
//    }

    public String getPhone() {
        return phone;
    }

    public String getShopname() {
        return shopname;
    }

//    public void setShopname(String shopname) {
//        this.shopname = shopname;
//    }

    public String getTimings() {
        return timings;
    }

    public String getAddress() {
        return address;
    }

//    public void setTimings(String timings) {
//        this.timings = timings;
//    }

    public String getVendorname() {
        return vendorname;
    }

//    public void setVendorname(String vendorname) {
//        this.vendorname = vendorname;
//    }

    public String getWebsite() {
        return website;
    }

//    public void setWebsite(String website) {
//        this.website = website;
//    }

//    public void setAddress(String address) {
//        this.address = address;
//    }

//    public void setUid(int uid) {
//        this.uid = uid;
//    }

    public int getUid() {
        return uid;
    }
}