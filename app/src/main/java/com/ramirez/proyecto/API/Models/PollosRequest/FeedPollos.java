package com.ramirez.proyecto.API.Models.PollosRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FeedPollos {

    @SerializedName("pollos")
    @Expose
    private ArrayList<PollosModel> pollos;

    public ArrayList<PollosModel> getPollos() {
        return pollos;
    }

    public void setPollos(ArrayList<PollosModel> pollos) {
        this.pollos = pollos;
    }
}
