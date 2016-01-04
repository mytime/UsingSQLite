package cn.uhei.usingsqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

/**
 * 读取数据库
 */
public class ReadDatabase extends AndroidTestCase {
    public void test() {
        Db db = new Db(getContext());
        SQLiteDatabase dbRead = db.getReadableDatabase();

        //查询数据库
//        dbRead.query(表名，列名，查询条件,条件值,分组，子句,排序)
//        dbRead.query("user",new String[]{"name"},"name = ?",new String[]{"小张"},null,null)
        Cursor c = dbRead.query("user", null, null, null, null, null, null);
        //循环查询
        while (c.moveToNext()){
            String name = c.getString(c.getColumnIndex("name"));
            String sex = c.getString(c.getColumnIndex("sex"));
            System.out.println(String.format("name = %s,sex=%s",name,sex));
        }


    }
}
