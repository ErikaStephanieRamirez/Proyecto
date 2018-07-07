package com.ramirez.proyecto.API.Models.PupusasRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FeedPupusas {

    @SerializedName("pupusas")
    @Expose
    private ArrayList<PupusasModel> pupusas;

    public ArrayList<PupusasModel> getPupusas() {
        return pupusas;
    }

    public void setPupusas(ArrayList<PupusasModel> pupusas) {
        this.pupusas = pupusas;
    }
}
