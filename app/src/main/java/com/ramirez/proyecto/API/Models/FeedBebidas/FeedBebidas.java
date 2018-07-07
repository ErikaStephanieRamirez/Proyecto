package com.ramirez.proyecto.API.Models.FeedBebidas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FeedBebidas {

    @SerializedName("bebidas")
    @Expose
    private ArrayList<Bebidas> bebidas;
<<<<<<< HEAD

    public ArrayList<Bebidas> getBebidas() {
        return bebidas;
    }

    public void setBebidas(ArrayList<Bebidas> bebidas) {
        this.bebidas = bebidas;
    }
=======
>>>>>>> 306cb57cb8b8681e464d38c885d6ac48965fae56
}
