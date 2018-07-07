package com.ramirez.proyecto.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.ramirez.proyecto.R;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        pref= getSharedPreferences("Preferencias", Context.MODE_PRIVATE);

        final Intent cliente = new Intent(this, AdminMainActivity.class);
        final Intent administrador = new Intent(this, Main2Activity.class);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(TextUtils.isEmpty(pref.getString("usuario","")) &&
                        TextUtils.isEmpty(pref.getString("password",""))
                        && TextUtils.isEmpty(pref.getString("Token",""))){
                    cliente.setFlags(cliente.FLAG_ACTIVITY_NEW_TASK | cliente.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(cliente);
                }else {
                    administrador.setFlags(administrador.FLAG_ACTIVITY_NEW_TASK | administrador.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(administrador);
                }
            }
        }, 1000);


    }
}
