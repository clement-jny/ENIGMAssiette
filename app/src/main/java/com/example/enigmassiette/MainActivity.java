package com.example.enigmassiette;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.enigmassiette.data.RestaurantContract;
import com.example.enigmassiette.data.RestaurantContract.RestaurantEntry;
import com.example.enigmassiette.data.RestaurantDbHelper;

import java.util.Calendar;
import java.util.Locale;
import java.text.DateFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et_restaurant_name;
    private Button bt_date, bt_time;
    private RatingBar rb_decoration, rb_food, rb_service;
    private EditText et_restaurant_review;
    private Button bt_add;

    private SQLiteDatabase db_access;

    private String completeDate, completeTime;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        referenceComponent();
        setListener();

        RestaurantDbHelper dbHelper = new RestaurantDbHelper(this);
        db_access = dbHelper.getWritableDatabase();
    }


    // Do all 'findViewById()'
    private void referenceComponent() {
        et_restaurant_name = (EditText) findViewById(R.id.et_restaurant_name);

        bt_date = (Button) findViewById(R.id.bt_date);
        bt_time = (Button) findViewById(R.id.bt_time);

        rb_decoration = (RatingBar) findViewById(R.id.rb_decoration);
        rb_food = (RatingBar) findViewById(R.id.rb_food);
        rb_service = (RatingBar) findViewById(R.id.rb_food);

        et_restaurant_review = (EditText) findViewById(R.id.et_restaurant_review);

        bt_add = (Button) findViewById(R.id.bt_add);
    }

    // Set the 'onClickListener'
    private void setListener() {
        bt_date.setOnClickListener(this);
        bt_time.setOnClickListener(this);
        bt_add.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.bt_date) {
            openDateDialog(view);
        } else if (view.getId() == R.id.bt_time) {
            openTimeDialog(view);
        } else if (view.getId() == R.id.bt_add){
            addDataToDatabase();
        }
    }

    public void openDateDialog(View view) {
        final Calendar calendar = Calendar.getInstance();
        int cYear = calendar.get(Calendar.YEAR);
        int cMonth = calendar.get(Calendar.MONTH);
        int cDay = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                completeDate = year + "-" + month + "-" + day;
                bt_date.setText(DateFormat.getDateInstance(DateFormat.MEDIUM).format(calendar.getTime()));
            }
        }, cYear, cMonth, cDay);
        datePickerDialog.show();
    }

    public void openTimeDialog(View view) {
        final Calendar calendar = Calendar.getInstance();
        int cHour = calendar.get(Calendar.HOUR);
        int cMinute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                completeTime = hourOfDay + ":" + minute;
                bt_time.setText(hourOfDay + ":" + minute);
            }
        }, cHour, cMinute, false);
        timePickerDialog.show();
    }

    public void addDataToDatabase() {
        if (et_restaurant_name.getText().length() == 0 || et_restaurant_review.getText().length() == 0) {
            return;
        }

        String name = et_restaurant_name.getText().toString();
        String review = et_restaurant_review.getText().toString();

        float decoration = rb_decoration.getRating();
        float food = rb_food.getRating();
        float service = rb_service.getRating();

        ContentValues cv = new ContentValues();
        cv.put(RestaurantEntry.COLUMN_RESTAURANT_NAME, name);
        cv.put(RestaurantEntry.COLUMN_RESTAURANT_REVIEW, review);

        cv.put(RestaurantEntry.COLUMN_RATING_DECORATION, decoration);
        cv.put(RestaurantEntry.COLUMN_RATING_FOOD, food);
        cv.put(RestaurantEntry.COLUMN_RATING_SERVICE, service);

        cv.put(RestaurantEntry.COLUMN_DATE, completeDate);
        cv.put(RestaurantEntry.COLUMN_TIME, completeTime);

        db_access.insert(RestaurantEntry.TABLE_NAME, null, cv);

        Intent startChildActivity = new Intent(MainActivity.this, RestaurantListActivity.class);
        startActivity(startChildActivity);
    }
}
