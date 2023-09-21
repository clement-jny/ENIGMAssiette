package com.example.enigmassiette.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.enigmassiette.data.RestaurantContract.RestaurantEntry;

public class RestaurantDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "restaurant.db";

    private static final int DATABASE_VERSION = 1;

    public RestaurantDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        /*final String SQL_CREATE_RESTAURANT_TABLE = "CREATE TABLE " + RestaurantEntry.TABLE_NAME + " (" +
                RestaurantEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                RestaurantEntry.COLUMN_RESTAURANT_NAME + " TEXT NOT NULL, " +
                RestaurantEntry.COLUMN_RATING_DECORATION + " FLOAT NOT NULL, " +
                RestaurantEntry.COLUMN_RATING_FOOD + " FLOAT NOT NULL, " +
                RestaurantEntry.COLUMN_RATING_SERVICE + " FLOAT NOT NULL, " +
                RestaurantEntry.COLUMN_RESTAURANT_REVIEW + " TEXT NOT NULL, " +
                RestaurantEntry.COLUMN_DATE + " DATE DEFAULT (strftime('%Y-%m-%d', 'now', 'localtime')), " +
                RestaurantEntry.COLUMN_TIME + " TIMESTAMP DEFAULT (strftime('%H:%M:%S', 'now', 'localtime'))" +
                "); ";*/

        final String SQL_CREATE_RESTAURANT_TABLE = "CREATE TABLE " + RestaurantEntry.TABLE_NAME + " (" +
                RestaurantEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                RestaurantEntry.COLUMN_RESTAURANT_NAME + " TEXT NOT NULL, " +
                RestaurantEntry.COLUMN_RATING_DECORATION + " FLOAT NOT NULL, " +
                RestaurantEntry.COLUMN_RATING_FOOD + " FLOAT NOT NULL, " +
                RestaurantEntry.COLUMN_RATING_SERVICE + " FLOAT NOT NULL, " +
                RestaurantEntry.COLUMN_RESTAURANT_REVIEW + " TEXT NOT NULL, " +
                RestaurantEntry.COLUMN_DATE + " TEXT NOT NULL, " +
                RestaurantEntry.COLUMN_TIME + " TEXT NOT NULL" + "); ";

        sqLiteDatabase.execSQL(SQL_CREATE_RESTAURANT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
