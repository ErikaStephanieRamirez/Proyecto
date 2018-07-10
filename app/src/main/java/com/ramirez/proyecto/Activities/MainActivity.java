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
import android.widget.Toast;

import com.ramirez.proyecto.API.Models.UsersRequest.UserModel;
import com.ramirez.proyecto.R;
import com.ramirez.proyecto.RoomArchitecture.Repository.LoginRepository;
import com.ramirez.proyecto.RoomArchitecture.ViewModel.BebidasViewModel;
import com.ramirez.proyecto.RoomArchitecture.ViewModel.PupusasViewModel;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences pref;
    private BebidasViewModel bebidasViewModel;
    private PupusasViewModel pupusasViewModel;
    private UserModel userModel = new UserModel("maxisun","root");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fillbaselocal();

        getSupportActionBar().hide();

        pref= getSharedPreferences("Login_Token", Context.MODE_PRIVATE);

        final Intent cliente = new Intent(this, Main2Activity.class);
        final Intent administrador = new Intent(this, AdminMainActivity.class);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(TextUtils.isEmpty(pref.getString("Token",""))){
                    cliente.setFlags(cliente.FLAG_ACTIVITY_NEW_TASK | cliente.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(cliente);
                }else {
                    administrador.setFlags(administrador.FLAG_ACTIVITY_NEW_TASK | administrador.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(administrador);
                }
            }
        }, 1000);



    }

    private void fillbaselocal(){
        bebidasViewModel = new BebidasViewModel(getApplication());
        pupusasViewModel = new PupusasViewModel(getApplication());
    }
}
