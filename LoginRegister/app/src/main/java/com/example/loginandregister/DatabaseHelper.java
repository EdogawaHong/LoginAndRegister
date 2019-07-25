package com.example.loginandregister;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {
        super(context,"Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table user(name text primary key,pass text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists user");
    }
    public boolean insert(String name,String pass){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("name",name);
        contentValues.put("pass",pass);
        long ins=db.insert("user",null,contentValues);
        if(ins==-1) return false;
        else return true;
    }
    public Boolean chkname(String name){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("Select * from user where name=?",new String[]{name});
        if(cursor.getCount()>0) return false;
        else return true;
    }
    public Boolean namepass(String name,String pass){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from user where name=? and pass=?",new String[]{name,pass});
        if(cursor.getCount()>0) return true;
        else return false;
    }
}
