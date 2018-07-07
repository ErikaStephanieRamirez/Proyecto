package com.ramirez.proyecto.API;

import com.ramirez.proyecto.API.Models.FeedBebidas.FeedBebidas;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RepSazonAPI {

    String BASE_URL = "https://rep-sazon.herokuapp.com";

    @GET("/bebidas")
    Call<FeedBebidas> getData();
}
