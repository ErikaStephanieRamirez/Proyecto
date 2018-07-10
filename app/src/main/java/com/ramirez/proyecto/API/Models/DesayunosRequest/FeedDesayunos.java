package com.ramirez.proyecto.API.Models.DesayunosRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FeedDesayunos {

    @SerializedName("desayunos")
    @Expose
    private ArrayList<DesayunosModel> desayunos;

    public ArrayList<DesayunosModel> getDesayunos() {
        return desayunos;
    }

    public void setDesayunos(ArrayList<DesayunosModel> desayunos) {
        this.desayunos = desayunos;
    }
}
