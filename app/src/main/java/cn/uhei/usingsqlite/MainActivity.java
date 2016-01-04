package cn.uhei.usingsqlite;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;

//把数据呈现到listview中，
//1 继承ListActivity
//2 listView需要借助adapter来显示数据
public class MainActivity extends ListActivity {

    //定义adapter对象,自动呈现数据
    private SimpleCursorAdapter adapter;

    //定义布局对象
    private EditText etName, etSex;
    private Button btnAdd;
    private Db db;
    private SQLiteDatabase dbWrite, dbRead;

    //长按对象方法
    private AdapterView.OnItemLongClickListener listViewItemLongClickListener = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

            new AlertDialog
                    .Builder(MainActivity.this)
                    .setTitle("提醒")
                    .setMessage("您确定要删除嘛？")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //获取cursor
                            Cursor c = adapter.getCursor();
                            //移动到点击的位置,position需要转成常量
                            c.moveToPosition(position);
                            //获得项目关联的数据库id
                            int itemid = c.getInt(c.getColumnIndex("_id"));
                            //执行删除
                            dbWrite.delete("user", "_id=?", new String[]{itemid + ""});

                            //刷新列表
                            refreshList();
                        }
                    })
                    .setNegativeButton("取消", null)
                    .show();



            //true 是否开启长按事件
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //实例化布局对象
        etName = (EditText) findViewById(R.id.etName);
        etSex = (EditText) findViewById(R.id.etSex);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建新值
                ContentValues values = new ContentValues();
                values.put("name", etName.getText().toString());
                values.put("sex", etSex.getText().toString());

                //执行插入
                dbWrite.insert("user", null, values);

                //动态向adapter传入cursor对象，刷新列表
                refreshList();

            }
        });

        //定义数据库对象并实例化
        db = new Db(this);
        //取得数据的读写操作
        dbRead = db.getReadableDatabase();
        dbWrite = db.getWritableDatabase();

        //实例化adapter
        //arg1,Context,
        //arg2,显示样式
        //arg3,cursor对象，用动态方式传入（刷新方法）
        //arg4,取数据的哪些列的值
        //arg5,从数据库取得的值放到哪里显示
        adapter = new SimpleCursorAdapter(this, R.layout.user_list_cell, null, new String[]{"name", "sex"}, new int[]{R.id.tvName, R.id.tvSex});
        setListAdapter(adapter);

        //显示数据,动态传入cursor对象
        refreshList();

        //长按删除
        //1获取列表项的长按事件
        getListView().setOnItemLongClickListener(listViewItemLongClickListener);


    }

    //执行查询和刷新方法
    private void refreshList() {
        //执行查询
        Cursor c = dbRead.query("user", null, null, null, null, null, null);
        //改变数据cursor对象
        adapter.changeCursor(c);
    }

}
