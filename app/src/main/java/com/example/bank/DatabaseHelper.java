package com.example.bank;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_table";
    private String TABLE_NAME1 = "transfers_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "User.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        db.execSQL("insert into user_table values(1290456783,'Ajay',10000,'ajay@gmail.com','012345678911','MYBK1234567')");
        db.execSQL("insert into user_table values(7014589945,'Tushar',10000,'tushar@gmail.com','123456789012','MYBK1234567')");
        db.execSQL("insert into user_table values(7016789445,'Dhyanesh',10000,'dhyanesh@gmail.com','123434345667','MYBK1234567')");
        db.execSQL("insert into user_table values(1290456734,'Varun',10000,'varun@gmail.com','123456767899','MYBK1234567')");
        db.execSQL("insert into user_table values(9099114566,'Isha',10000,'isha@gmail.com','98765645553','MYBK1234567')");
        db.execSQL("insert into user_table values(6766389569,'Pavi',10000,'pavi@gmail.com','456784536779','MYBK1234567')");
        db.execSQL("insert into user_table values(1190447683,'Nancy',10000,'nancy@gmail.com','2345645366787','MYBK1234567')");
        db.execSQL("insert into user_table values(4546783291,'Snehil',10000,'snehil@gmail.com','4365476432423','MYBK1234567')");
        db.execSQL("insert into user_table values(8945673489,'Shashwat',10000,'shashwat@gmail.com','23465765422435','MYBK1234567')");
        db.execSQL("insert into user_table values(1289402234,'Ayush',10000,'ayush@gmail.com','3875475890594','MYBK1234567')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table", null);
        return cursor;
    }

    public boolean insertTransferData(String date,String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}
