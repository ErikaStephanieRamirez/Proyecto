package com.ramirez.proyecto.API.Models.FeedBebidas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FeedBebidas {

    @SerializedName("bebidas")
    @Expose
    private ArrayList<Bebidas> bebidas;
}
