package com.example.calendar.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import com.example.calendar.info.Bean;
import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DBName = "test.db";
    private static final String TableName = "memorandum";
    private static final int DBVersion = 1;
    private static DBHelper helper = null;
    private SQLiteDatabase read = null;
    private SQLiteDatabase write = null;

    private DBHelper(@Nullable Context context) { super(context,DBName,null,DBVersion); }

    public static DBHelper getInstance(Context context) {
        if(helper == null) helper = new DBHelper(context);
        return helper;
    }

    public SQLiteDatabase openRead() {
        if (read == null || !read.isOpen()) read = helper.getReadableDatabase();
        return read;
    }

    public SQLiteDatabase openWrite() {
        if (write == null || !write.isOpen()) write = helper.getWritableDatabase();
        return write;
    }

    public void closeLink() {
        if(read != null && read.isOpen()) {
            read.close();
            read = null; }
        if(write != null && write.isOpen()) {
            write.close();
            write = null; }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TableName + "(" +
                "start_time VARCHAR NOT NULL," +
                "finish_time VARCHAR NOT NULL," +
                "text VARCHAR NOT NULL);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public long insert(Bean bean) {
        ContentValues values = new ContentValues();
        values.put("start_time",bean.startTime);
        values.put("finish_time",bean.finishTime);
        values.put("text",bean.text);
        return write.insert(TableName,null,values);
    }

    public long update(Bean bean) {
        ContentValues values = new ContentValues();
        values.put("start_time",bean.startTime);
        values.put("finish_time",bean.finishTime);
        values.put("text",bean.text);
        return write.delete(TableName,"text=?",new String[]{bean.text});
    }

    public ArrayList<Bean> queryAll() {
        ArrayList<Bean> list = new ArrayList<>();
        Cursor cursor = read.query(TableName,null,null,null,null,null,null);
        while (cursor.moveToNext()) {
            Bean bean = new Bean(cursor.getString(0),cursor.getString(1),cursor.getString(2));
            list.add(bean); }
        return list;
    }

    public long deleteByText(String text) {
        return write.delete(TableName,"text=?",new String[]{text});
    }


}
