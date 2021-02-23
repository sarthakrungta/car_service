package com.example.car_service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.TransitionRes;

import java.util.ArrayList;
import java.util.List;

public class databaseHelper extends SQLiteOpenHelper {


    public static final String COLUMN_CUSTOMER_NAME = "CUSTOMER_NAME";
    public static final String COLUMN_CAR_DETAIL = "CAR_DETAIL";
    public static final String COLUMN_DATE = "DATE";
    public static final String COLUMN_ID = "ID";
    public static final String SERVICES_TABLE = "SERVICES_TABLE";

    public databaseHelper(@Nullable Context context) {
        super(context, "services.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + SERVICES_TABLE + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_CUSTOMER_NAME + " TEXT, " + COLUMN_CAR_DETAIL + " TEXT, " + COLUMN_DATE + " TEXT)";
        db.execSQL(createTableStatement);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(data_model data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_CUSTOMER_NAME, data.getName());
        cv.put(COLUMN_CAR_DETAIL, data.getCarDetail());
        cv.put(COLUMN_DATE, data.getDate());

        long insert = db.insert(SERVICES_TABLE, null, cv);

        if (insert == -1){
            return false;
        }
        else {
            return true;
        }

    };

    public boolean deleteOne(data_model data){
        SQLiteDatabase db = this.getWritableDatabase();
        long delete = db.delete(SERVICES_TABLE, "id=?", new String[]{COLUMN_ID});

        if (delete == -1){
            return false;
        }
        else{
            return true;
        }
    };

    public void deleteNote(long id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(SERVICES_TABLE,COLUMN_ID+"=?",new String[]{String.valueOf(id)});
        db.close();
    }

    public List<data_model> getlist(){
        List<data_model> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + SERVICES_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()){ //LOOP THROUGH RESULTS
            do{
                int custID = cursor.getInt(0);
                String custName = cursor.getString(1);
                String carDetails = cursor.getString(2);
                String date = cursor.getString(3);

                data_model data = new data_model(custID, custName, carDetails, date);
                returnList.add(data);
            }while (cursor.moveToNext());

        }
        else{ //ERROR
            returnList.add(new data_model(-1, "null", "error", "error"));
        }
        cursor.close();
        db.close();
        return returnList;

    };
}
