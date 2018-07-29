package com.example.dilan.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.example.dilan.myapplication.recycler.MyAdapter;
import com.example.dilan.myapplication.recycler.MyObject;
import com.example.dilan.myapplication.task.Task;
import com.example.dilan.myapplication.task.TaskActivity;
import com.example.dilan.myapplication.task.TasksBDD;

import net.steamcrafted.materialiconlib.MaterialIconView;

import java.nio.file.DirectoryStream;
import java.util.ArrayList;
import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;

public class MainActivity extends AppCompatActivity

        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerView;
    private String filter;
    MaterialSpinner materialBetterSpinner ;
    MainActivity self = this;
    String[] SPINNER_DATA = {"ALL", "TODO", "DONE"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbars.show(this);

        materialBetterSpinner = (MaterialSpinner)findViewById(R.id.material_spinner1);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_dropdown_item_1line, SPINNER_DATA);
        materialBetterSpinner.setAdapter(adapter);
        materialBetterSpinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        List task;

                        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
                        recyclerView.setLayoutManager(new LinearLayoutManager(self));
                        recyclerView.setAdapter(new MyAdapter(Task.showTasks(self, SPINNER_DATA[position]), new MyAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(MyObject object) {
                                Log.d("========= ok",object.getText());
                            }
                        }, self));
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        filter = "ALL";
                    }

                }
        );
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(self));
        recyclerView.setAdapter(new MyAdapter(Task.showTasks(self, "ALL"), new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(MyObject object) {
                Log.d("========= ok",object.getText());
            }
        }, self));

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_list) {
            // Handle the camera action
        } else if (id == R.id.nav_task) {
            startActivity(new Intent(MainActivity.this, TaskActivity.class));
        }
        else if (id == R.id.home){
            startActivity(new Intent(MainActivity.this, MainActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
