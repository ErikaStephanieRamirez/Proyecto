package com.ramirez.proyecto.RoomArchitecture.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.ramirez.proyecto.RoomArchitecture.Entities.PolloEntity;
import com.ramirez.proyecto.RoomArchitecture.Repository.PolloRepository;

import java.util.List;

public class PollosViewModel extends AndroidViewModel{

    private PolloRepository polloRepository;

    public PollosViewModel(Application application){
        super(application);
        polloRepository = new PolloRepository(application);
    }

    public LiveData<List<PolloEntity>> getAllPollos(){
        return  polloRepository.getAllPollos();
    }

    public void insert(PolloEntity polloEntity){
        polloRepository.insert(polloEntity);
    }


}
