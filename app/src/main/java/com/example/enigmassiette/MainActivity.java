package com.example.enigmassiette;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
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

import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et_restaurant_name;
    private Button bt_date, bt_time;
    private RatingBar rb_decoration, rb_food, rb_service;
    private EditText et_review;
    private Button bt_add;

    private int hour, minute;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        referenceComponent();
        setListener();
    }


    // Do all 'findViewById()'
    private void referenceComponent() {
        et_restaurant_name = (EditText) findViewById(R.id.et_restaurant_name);

        bt_date = (Button) findViewById(R.id.bt_date);
        bt_time = (Button) findViewById(R.id.bt_time);

        rb_decoration = (RatingBar) findViewById(R.id.rb_decoration);
        rb_food = (RatingBar) findViewById(R.id.rb_food);
        rb_service = (RatingBar) findViewById(R.id.rb_food);

        et_review = (EditText) findViewById(R.id.et_review);

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
            saveData();
        }
    }

    public void openDateDialog(View view) {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month += 1;
                bt_date.setText(year + " " + month + " " + day);
            }
        };

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, dateSetListener, year, month, day);
        datePickerDialog.show();
    }

    public void openTimeDialog(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                hour = selectedHour;
                minute = selectedMinute;
                bt_time.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, minute));
            }
        };

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener, hour, minute, true);

        timePickerDialog.setTitle("Select time");
        timePickerDialog.show();
    }

    public void saveData() {
        Toast.makeText(this, String.valueOf(rb_decoration.getRating()), Toast.LENGTH_LONG).show();
    }
}
