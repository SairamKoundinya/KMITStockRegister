package edu.app.kmit.stockselection;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by Admin on 12/26/2018.
 */
@Database(entities = {Shop.class,Goods.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ShopDao shopDao();
    public abstract GoodsDao goodsDao();
}
