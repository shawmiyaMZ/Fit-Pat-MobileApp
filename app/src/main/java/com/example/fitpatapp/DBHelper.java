package com.example.fitpatapp;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;


public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "User.db";
    public DBHelper(Context context) {
        super(context, "User.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase MyDB) {

        // Create the "users" table with columns: username, password, gender
        MyDB.execSQL("create Table users(username TEXT primary key, password TEXT, gender TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {

        // Drop the "users" table if it exists during the database upgrade
        MyDB.execSQL("drop Table if exists users");
    }

    public Boolean insertData(String username, String password, String gender){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("gender", gender);
        long result = MyDB.insert("users", null, contentValues);

        // Check if the data was inserted successfully
        if(result==-1) return false;
        else
            return true;
    }

    public Boolean checkusername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[]{username});

        // Check if the username exists in the table
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?", new String[] {username,password});

        // Check if the username and password combination exists in the table
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    // update password in forget password page
    public boolean updatePassword(String username, String newPassword) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("password", newPassword);

        int rowsAffected = MyDB.update("users", contentValues, "username = ?", new String[]{username});
        return rowsAffected > 0;
    }
}