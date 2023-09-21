package com.example.enigmassiette;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.enigmassiette.data.RestaurantContract;
import com.example.enigmassiette.data.RestaurantDbHelper;

public class RestaurantListActivity extends AppCompatActivity {
    private RestaurantListAdapter mAdapter;
    private SQLiteDatabase mDb;
    private RecyclerView restaurantRecyclerView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);

        restaurantRecyclerView = (RecyclerView) this.findViewById(R.id.rv_list);

        restaurantRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        RestaurantDbHelper dbHelper = new RestaurantDbHelper(this);

        mDb = dbHelper.getWritableDatabase();
        Cursor cursor = getAllReviews();

        mAdapter = new RestaurantListAdapter(this, cursor);

        restaurantRecyclerView.setAdapter(mAdapter);
    }

    public Cursor getAllReviews() {
        return mDb.query(RestaurantContract.RestaurantEntry.TABLE_NAME, null, null, null, null, null, null);
    }
}
