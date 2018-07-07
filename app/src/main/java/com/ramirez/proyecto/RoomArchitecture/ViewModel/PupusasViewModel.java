package com.ramirez.proyecto.RoomArchitecture.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.ramirez.proyecto.RoomArchitecture.Entities.PupusaEntity;
import com.ramirez.proyecto.RoomArchitecture.Repository.PupusaRepository;

import java.util.List;

public class PupusasViewModel extends AndroidViewModel {

    private PupusaRepository pupusaRepository;

    public PupusasViewModel(Application application){
        super(application);
        pupusaRepository = new PupusaRepository(application);
    }

    public LiveData<List<PupusaEntity>> getAllPupusas(){
        return pupusaRepository.getAllPupusas();
    }

    public void insert(PupusaEntity pupusaEntity){
        pupusaRepository.insert(pupusaEntity);
    }
}
