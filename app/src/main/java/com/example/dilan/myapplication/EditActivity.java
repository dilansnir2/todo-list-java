package com.example.dilan.myapplication;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.dilan.myapplication.task.TaskActivity;
import com.example.dilan.myapplication.task.TasksBDD;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

public class EditActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView mDisplayDate;
    private TextView mDisplayHour;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private String date;
    private TimePickerDialog mTimeSetListener;
    private String hours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Toolbars.show(this);
        Bundle b = getIntent().getExtras();
        int value = -1;
        if(b != null){
            value = b.getInt("id");
        }

        TasksBDD bdd = new TasksBDD(this);
        bdd.open();
        final Map<String, String> task = bdd.getTask(value);

        final EditText inputEdit = (EditText) findViewById(R.id.editTitle);
        inputEdit.setText(task.get("title"));

        final EditText editContent = (EditText) findViewById(R.id.editContent);
        editContent.setText(task.get("content"));

        mDisplayHour = (TextView) findViewById(R.id.inputHour);
        String hoursFromBdd = task.get("date").split(" ")[1];
        mDisplayHour.setText(hoursFromBdd);
        mDisplayHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int min = cal.get(Calendar.MINUTE);
                int hour = cal.get(Calendar.HOUR);
                TimePickerDialog dialog = new TimePickerDialog(EditActivity.this, new TimePickerDialog.OnTimeSetListener() {
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
        String dateFromBdd = task.get("date").split(" ")[0];
        mDisplayDate.setText(dateFromBdd);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(EditActivity.this, R.style.Theme_AppCompat_Light_Dialog, mDateSetListener, year,month,day );
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

        final Context self = this;

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TasksBDD bdd = new TasksBDD(self);
                bdd.open();
                Boolean status = false;
                if(Integer.parseInt(task.get("status")) == 1){
                    status = true;
                }
                bdd.updateTask(Integer.parseInt(task.get("id")), editContent.getText().toString(), date+" "+hours, status, inputEdit.getText().toString());
                startActivity(new Intent(EditActivity.this, MainActivity.class));
                Snackbar.make(view, "Edit task", Snackbar.LENGTH_LONG)
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
            startActivity(new Intent(EditActivity.this, TaskActivity.class));
        }
        else if (id == R.id.home){
            startActivity(new Intent(EditActivity.this, MainActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
