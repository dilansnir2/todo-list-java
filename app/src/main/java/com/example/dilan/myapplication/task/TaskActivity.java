package com.example.dilan.myapplication.task;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.dilan.myapplication.MainActivity;
import com.example.dilan.myapplication.R;
import com.example.dilan.myapplication.Toolbars;

public class TaskActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        Toolbars.show(this);
        Button mbutton = (Button) findViewById(R.id.button);
        final Context self = this;
        final EditText mEdit = (EditText) findViewById(R.id.addTask);
        mbutton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TasksBDD tasksBDD = new TasksBDD(self);
                        String value = mEdit.getText().toString();
                        tasksBDD.open();
                        tasksBDD.insertTask(new Task(0, value));
                    }
                }
        );
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

        if (id == R.id.nav_list) {
            // Handle the camera action
        } else if (id == R.id.nav_task) {
            startActivity(new Intent(TaskActivity.this, TaskActivity.class));
        } else if (id == R.id.home){
            startActivity(new Intent(TaskActivity.this, MainActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
