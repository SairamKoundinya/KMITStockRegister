package edu.app.kmit.stockselection;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;


/**
 * Created by Admin on 12/26/2018.
 */
@Dao
public interface ShopDao {

    @Query("SELECT * FROM Shop order by uid desc")
    List<Shop> getTwo();

    @Query("SELECT * FROM Shop WHERE uid = :id")
    List<Shop> getShop(int id);

    @Query("SELECT shopname FROM Shop WHERE uid = :id")
    String getShopName(int id);

    @Query("SELECT uid FROM Shop")
    List<Integer> getUid();
    @Query("SELECT shopname FROM Shop")
    List<String> getName();

    @Update
    void update(Shop s);

    @Query("SELECT shopname FROM Shop where uid IN (:li)")
    List<String> getName(int[] li);
//    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
//    List<User> loadAllByIds(int[] userIds);
//
//    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
//            "last_name LIKE :last LIMIT 1")
//    User findByName(String first, String last);

    @Insert
    void insertAll(Shop... shops);

   @Delete
    void deleteShop(Shop id);
}
