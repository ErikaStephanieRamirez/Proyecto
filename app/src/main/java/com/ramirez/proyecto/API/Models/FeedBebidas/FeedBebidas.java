package com.ramirez.proyecto.API.Models.FeedBebidas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FeedBebidas {

    @SerializedName("bebidas")
    @Expose
    private ArrayList<Bebidas> bebidas;

    public ArrayList<Bebidas> getBebidas() {
        return bebidas;
    }

    public void setBebidas(ArrayList<Bebidas> bebidas) {
        this.bebidas = bebidas;
    }
}
