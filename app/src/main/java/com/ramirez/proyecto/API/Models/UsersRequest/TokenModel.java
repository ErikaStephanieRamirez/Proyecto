package com.ramirez.proyecto.API.Models.UsersRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TokenModel {

    @SerializedName("token")
    @Expose
    private String token;

    public String getToken() {
        return token;
    }

}
