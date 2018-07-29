package com.example.dilan.myapplication;

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
import android.widget.EditText;

import com.example.dilan.myapplication.task.TaskActivity;
import com.example.dilan.myapplication.task.TasksBDD;

import java.util.ArrayList;
import java.util.Map;

public class EditActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

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

        final EditText inputEdit = (EditText) findViewById(R.id.editTask);
        inputEdit.setText(task.get("content"));
        System.out.println(task);

        Button buttonEdit = (Button) findViewById(R.id.editTaskBtn);
        final Context self = this;
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TasksBDD bdd = new TasksBDD(self);
                bdd.open();
                System.out.println(task);
                bdd.updateTask(Integer.parseInt(task.get("id")), inputEdit.getText().toString());
                startActivity(new Intent(EditActivity.this, MainActivity.class));
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

        if (id == R.id.nav_list) {
            // Handle the camera action
        } else if (id == R.id.nav_task) {
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
