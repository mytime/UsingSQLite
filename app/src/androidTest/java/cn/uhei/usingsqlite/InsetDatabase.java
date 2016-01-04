package cn.uhei.usingsqlite;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

/**
 * 插入数据
 */
public class InsetDatabase extends AndroidTestCase {
    public void test(){
        //定义数据库对象并实例化数据库
        Db db = new Db(getContext());
        //获取一个可写的数据
        SQLiteDatabase dbWrite = db.getWritableDatabase();

        //执行插入操作
        //values:是一个ContentValues对象
        ContentValues values = new ContentValues();
        //生成两条数据
        values.put("name", "小张");
        values.put("sex", "男");
        long i = dbWrite.insert("user", null, values);

        values = null;
        values = new ContentValues();
        //生成两条数据
        values.put("name", "小李");
        values.put("sex", "女");
        i = dbWrite.insert("user", null, values);
        System.out.println("成功插入" + i + "条数据");

        //关闭数据库
        dbWrite.close();
    }

}
