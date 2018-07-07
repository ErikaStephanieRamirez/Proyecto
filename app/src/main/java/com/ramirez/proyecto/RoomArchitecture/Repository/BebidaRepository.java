package com.ramirez.proyecto.RoomArchitecture.Repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.ramirez.proyecto.RoomArchitecture.DAO.BebidasDAO;
import com.ramirez.proyecto.RoomArchitecture.Entities.BebidaEntity;
import com.ramirez.proyecto.RoomArchitecture.RepSazonDatabase;

import java.util.ArrayList;
import java.util.List;

public class BebidaRepository {

    private BebidasDAO bebidasDAO;
    //private LiveData<ArrayList<BebidaEntity>> bebidas;

    public BebidaRepository(Application application){
        RepSazonDatabase db = RepSazonDatabase.getDatabase(application);
        bebidasDAO = db.bebidasDAO();
    }

    public LiveData<List<BebidaEntity>> getAllBebidas(){
        return bebidasDAO.getAllBebidas();
    }

    public void fetchBebidas() {

    }

}
