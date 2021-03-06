package com.example.dilan.myapplication.task;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.dilan.myapplication.MainActivity;
import com.example.dilan.myapplication.R;
import com.example.dilan.myapplication.Toolbars;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

public class TaskActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView mDisplayDate;
    private TextView mDisplayHour;
    private TextView mContent;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private TimePickerDialog mTimeSetListener;
    private  String date;
    private String hours;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        Toolbars.show(this);

        final Context self = this;
        final EditText mEdit = (EditText) findViewById(R.id.addTitle);
        final EditText mContent = (EditText) findViewById(R.id.addContent);

        mDisplayHour = (TextView) findViewById(R.id.inputHour);
        mDisplayHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int min = cal.get(Calendar.MINUTE);
                int hour = cal.get(Calendar.HOUR);
                TimePickerDialog dialog = new TimePickerDialog(TaskActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        mDisplayHour.setText(hourOfDay + ":" + minute);
                        hours = hourOfDay+":"+minute+":"+"00";
                    }
                }, hour, min, false );
                dialog.getWindow();
                dialog.show();
            }
        });



        mDisplayDate = (TextView) findViewById(R.id.inputDate);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                int hour = cal.get(Calendar.HOUR);

                System.out.println(hour);
                DatePickerDialog dialog = new DatePickerDialog(
                        TaskActivity.this,
                        R.style.Theme_AppCompat_Light_Dialog,
                        mDateSetListener,
                        year,month,day
                );
                dialog.getWindow();
                dialog.show();
            }
        });


        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                date = month+"/"+dayOfMonth+"/"+year;
                mDisplayDate.setText(date);
            }
        };

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TasksBDD tasksBDD = new TasksBDD(self);
                String value = mEdit.getText().toString();
                tasksBDD.open();
                tasksBDD.insertTask(new Task(0, mContent.getText().toString(), date+" "+hours, false, value));
                startActivity(new Intent(TaskActivity.this, MainActivity.class));
                Snackbar.make(view, "One task added", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_task) {
            startActivity(new Intent(TaskActivity.this, TaskActivity.class));
        } else if (id == R.id.home){
            startActivity(new Intent(TaskActivity.this, MainActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
