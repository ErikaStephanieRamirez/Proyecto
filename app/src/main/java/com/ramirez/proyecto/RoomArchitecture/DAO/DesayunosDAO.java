package com.ramirez.proyecto.RoomArchitecture.DAO;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.ramirez.proyecto.RoomArchitecture.Entities.DesayunoEntity;

import java.util.List;

@Dao
public interface DesayunosDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(DesayunoEntity desayunoEntity);

    @Query("SELECT * FROM Desayunos_table ORDER BY name DESC")
    LiveData<List<DesayunoEntity>> getAllDesayunos();
}
