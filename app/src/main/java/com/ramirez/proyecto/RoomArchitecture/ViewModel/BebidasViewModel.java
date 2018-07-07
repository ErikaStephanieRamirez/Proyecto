package com.ramirez.proyecto.RoomArchitecture.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.ramirez.proyecto.RoomArchitecture.Entities.BebidaEntity;
import com.ramirez.proyecto.RoomArchitecture.Repository.BebidaRepository;

import java.util.List;


public class BebidasViewModel extends AndroidViewModel{

    private BebidaRepository bebidaRepository;

    public BebidasViewModel(Application application){
        super(application);
        bebidaRepository = new BebidaRepository(application);
    }

    public LiveData<List<BebidaEntity>> getAllBebidas() {
        return bebidaRepository.getAllBebidas();
    }

    public void insert(BebidaEntity bebidaEntity){
        bebidaRepository.insert(bebidaEntity);
    }

}
