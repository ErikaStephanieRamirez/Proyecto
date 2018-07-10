package com.ramirez.proyecto.Activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.MenuInflater;
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
import android.widget.TextView;
import android.widget.Toast;

import com.ramirez.proyecto.Fragments.TabsFragment;
import com.ramirez.proyecto.Fragments.emptyfrag;
import com.ramirez.proyecto.Fragments.menufragment;
import com.ramirez.proyecto.Fragments.verde;
import com.ramirez.proyecto.R;

import static java.lang.Boolean.getBoolean;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    Button ver;
    boolean cosa = false;
    SharedPreferences prefs;
    static final int REQUEST_CODE_ASK_PERMISSION = 2018;
    int Read;
    int prueba;
    private boolean isFirstEntry=true;



    private void accessPermission(){
        Read = ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);

        if(Read != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE},REQUEST_CODE_ASK_PERMISSION);
            prueba=0;
        }
        else{
            prueba=1;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        accessPermission();
        if(savedInstanceState==null){
            getSupportFragmentManager().beginTransaction().replace(R.id.contentcosa, menufragment.newInstance("a","b")).commit();
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Menú");
        setSupportActionBar(toolbar);
       // FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
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
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("first", false);
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
        boolean FragmentSeleccionado = false;
        Fragment miFragment=null;

            if (id == R.id.login) {
                // Handle the camera action
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            } else if (id == R.id.menu) {
                miFragment = menufragment.newInstance("C","d");
                FragmentSeleccionado = true;
            } else if (id == R.id.info) {

            } else if (id == R.id.facebook) {
                Intent redireccion= new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/RepublicaSazon/"));
                startActivity(redireccion);
            } else if (id == R.id.whats) {
                Toast.makeText(getApplicationContext(),"Llamando a República Sazón",Toast.LENGTH_SHORT).show();
                Intent call = new Intent(Intent.ACTION_CALL);
                call.setData(Uri.parse("tel:"+"25026340"));
                startActivity(call);
            } else if (id == R.id.instagram) {
                Intent redireccion= new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/republicasazon/"));
                startActivity(redireccion);
            }
        if(FragmentSeleccionado){
            getSupportFragmentManager().beginTransaction().replace(R.id.contentcosa,miFragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



}
