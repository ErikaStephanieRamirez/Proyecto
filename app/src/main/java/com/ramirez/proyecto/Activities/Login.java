package com.ramirez.proyecto.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ramirez.proyecto.API.Models.UsersRequest.UserModel;
import com.ramirez.proyecto.R;
import com.ramirez.proyecto.RoomArchitecture.Repository.LoginRepository;

import java.net.SocketTimeoutException;

public class Login extends AppCompatActivity {

    private EditText name, pass;
    private Button btn;
    public String names, passw;
    private UserModel userModel;

    private SharedPreferences preferencies;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        name = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        btn = findViewById(R.id.email_sign_in_button);

        preferencies = getSharedPreferences("Preferencias", Context.MODE_PRIVATE);

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(name.getText().toString().isEmpty() || pass.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Complete los campos", Toast.LENGTH_SHORT).show();
                }else{
                    names=name.getText().toString();
                    passw = pass.getText().toString();


                    //Cambio de validacion de token REVISAR REPOSITORIO LOGINREPOSITORY
                    userModel = new UserModel(names,passw);
                    LoginRepository.loginval(userModel,getApplication());
                    //Fin de validacion de usuario y token guardado en shared preferences


                    //SaveSharedPrefs(names,passw);
                    Intent intent = new Intent(getApplicationContext(),AdminMainActivity.class);
                    startActivity(intent);
                    finish();

                }
            }
        });
    }

    /*
    public void SaveSharedPrefs(String usuariosp,String passwrd){
        SharedPreferences.Editor editor= preferencies.edit();
        editor.putString("usuario", usuariosp);
        editor.putString("password", passwrd);
        editor.apply();
    }*/
}
