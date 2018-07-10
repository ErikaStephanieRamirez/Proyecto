package com.ramirez.proyecto.RoomArchitecture.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.ramirez.proyecto.RoomArchitecture.Entities.DesayunoEntity;
import com.ramirez.proyecto.RoomArchitecture.Repository.DesayunoRepository;

import java.util.List;

public class DesayunoViewModel extends AndroidViewModel{

    private DesayunoRepository desayunoRepository;

    public DesayunoViewModel(Application application){
        super(application);
        desayunoRepository = new DesayunoRepository(application);
    }

    public LiveData<List<DesayunoEntity>> getAllDesayunos() {
        return desayunoRepository.getAllDesayunos();
    }

    public void insert(DesayunoEntity desayunoEntity){
        desayunoRepository.insert(desayunoEntity);
    }
}
