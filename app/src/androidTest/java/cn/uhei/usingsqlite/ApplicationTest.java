package cn.uhei.usingsqlite;

import android.app.Application;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.test.ApplicationTestCase;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
        Test();
    }

    public void Test(){
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
    }
}