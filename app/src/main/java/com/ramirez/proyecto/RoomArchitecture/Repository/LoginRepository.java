package com.ramirez.proyecto.RoomArchitecture.Repository;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.ramirez.proyecto.API.Models.UsersRequest.TokenModel;
import com.ramirez.proyecto.API.Models.UsersRequest.UserModel;
import com.ramirez.proyecto.API.RepSazonAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginRepository {

    public static void loginval(UserModel userModel, final Application application){
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(RepSazonAPI.BASE_URL).addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        RepSazonAPI repSazonAPI = retrofit.create(RepSazonAPI.class);
        Call<TokenModel> call = repSazonAPI.getToken(userModel);
        call.enqueue(new Callback<TokenModel>() {
            @Override
            public void onResponse(Call<TokenModel> call, Response<TokenModel> response) {
                if(response.isSuccessful()){
                    sharedpreferences(response.body().getToken(),application);
                }else {
                    Toast.makeText(application.getApplicationContext(),"No Autorizado",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TokenModel> call, Throwable t) {
                Toast.makeText(application.getApplicationContext(),"Sin acceso a internet",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private static void sharedpreferences(String token, Application application ){
        SharedPreferences sharedPreferences = application.getApplicationContext().getSharedPreferences("Login_Token", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Token",token);
        //asyncrono
        editor.apply();
        Toast.makeText(application.getApplicationContext(),token,Toast.LENGTH_SHORT).show();
    }
}
