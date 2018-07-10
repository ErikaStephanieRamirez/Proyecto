package com.ramirez.proyecto.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ramirez.proyecto.Fragments.menufragment;
import com.ramirez.proyecto.R;

public class AdminMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    Button ver;
    boolean cosa = false;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2admin);
        getSupportFragmentManager().beginTransaction().replace(R.id.contentcosa, menufragment.newInstance("a","b")).commit();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Menú");
        setSupportActionBar(toolbar);
        SharedPreferences p = getSharedPreferences("Preferencias", Context.MODE_PRIVATE);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        /*fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

            if (id == R.id.verventa) {
                // Handle the camera action

            } else if (id == R.id.addregistro) {

                Intent redireccion= new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/RepublicaSazon/"));
                startActivity(redireccion);

            } else if (id == R.id.menu) {

            } else if (id == R.id.info) {

            } else if (id == R.id.logout) {
                SharedPreferences prefs;
                prefs = getSharedPreferences("Preferencias", Context.MODE_PRIVATE);
                prefs.edit().clear().apply();
                Intent intent = new Intent(this,Main2Activity.class);
                intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK | intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);


            } else if (id == R.id.facebook) {
                Intent redireccion= new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/RepublicaSazon/"));
                startActivity(redireccion);
            } else if (id == R.id.whats) {

            } else if (id == R.id.instagram) {
                Intent redireccion= new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/republicasazon/"));
                startActivity(redireccion);
            }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



}
