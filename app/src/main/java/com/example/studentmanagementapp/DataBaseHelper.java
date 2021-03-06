package com.example.studentmanagementapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="STUDENT_DATABSE";
    private static final String TABLE_NAME="STUDENT_TABLE";
    private static final String COL1="NAME";
    private static final String COL2="ROLL";
    private static final String COL3="DOB";

    private SQLiteDatabase db;


    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" (NAME TEXT,ROLL INTEGER PRIMARY KEY, DOB STRING)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean addStudentDetail(String name,int roll,String dob){
        db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COL1,name);
        values.put(COL2,roll);
        values.put(COL3,dob);
        long result=db.insert(TABLE_NAME,null,values);
        if(result==-1)
            return false;
        else
            return true;
    }

    public Cursor getAllData()
   {
       db=this.getWritableDatabase();
       Cursor res=db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
       return res;
   }

   public void deleteData(int roll){
        db=this.getWritableDatabase();
        db.delete(TABLE_NAME,"ROLL=?",new String[] {String.valueOf(roll)});
   }

   public void updateData(int roll,String name,String dob){
        db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COL1,name);
        values.put(COL2,roll);
        values.put(COL3,dob);
        db.update(TABLE_NAME,values,"ROLL=?",new String[]{String.valueOf(roll)});
   }


}
