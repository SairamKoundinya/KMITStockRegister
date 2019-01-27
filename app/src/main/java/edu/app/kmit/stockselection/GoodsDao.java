package edu.app.kmit.stockselection;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by Admin on 1/5/2019.
 */
@Dao
public interface GoodsDao {

    @Query("SELECT uid FROM Goods")
    List<Integer> getId();

    @Update
    void update(Goods s);
    @Query("SELECT * FROM Goods WHERE sid = :id order by uid desc")
    List<Goods> getGood(int id);
    @Query("SELECT * FROM Goods WHERE uid = :id")
    List<Goods> getGoods(int id);

    @Query("SELECT * FROM Goods WHERE productname = :id order by uid desc")
    List<Goods> getGood(String id);

    @Query("SELECT * FROM Goods")
    List<Goods> getAll();

    @Query("SELECT productname FROM Goods where productname = :pn and sid =:id")
    String check(String pn,String id);

    @Query("SELECT distinct productname FROM Goods order by uid desc")
    List<String> getName();
    @Insert
    void insertAll(Goods... goods);

    @Delete
    void deleteGood(Goods id);
}
