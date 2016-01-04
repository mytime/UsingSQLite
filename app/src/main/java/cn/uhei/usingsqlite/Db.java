package cn.uhei.usingsqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 *创建数据库
 * */
public class Db extends SQLiteOpenHelper {
    //
    public Db(Context context) {
        //db:数据库名称
        //null:数据工厂
        //1：版本号
        super(context, "db", null, 1);
    }

    //创建数据库结构
    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建表
        db.execSQL("create table user(_id integer primary key autoincrement," +
                "name text default none," +
                "sex text default none)");
    }

    //升级数据库
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
