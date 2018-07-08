package com.ramirez.proyecto.API;

import com.ramirez.proyecto.API.Models.FeedBebidas.FeedBebidas;
import com.ramirez.proyecto.API.Models.PollosRequest.FeedPollos;
import com.ramirez.proyecto.API.Models.PupusasRequest.FeedPupusas;
import com.ramirez.proyecto.API.Models.UsersRequest.TokenModel;
import com.ramirez.proyecto.API.Models.UsersRequest.UserModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RepSazonAPI {

    String BASE_URL = "https://rep-sazon.herokuapp.com";

    @GET("/bebidas")
    Call<FeedBebidas> getData();

    @GET("/pupusas")
    Call<FeedPupusas> getPupusas();

    @GET("/pollos")
    Call<FeedPollos> getPollos();

    @POST("users/login")
    Call<TokenModel> getToken(@Body UserModel userModel);
}
