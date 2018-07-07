package com.ramirez.proyecto.RoomArchitecture.DAO;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.ramirez.proyecto.RoomArchitecture.Entities.BebidaEntity;
import com.ramirez.proyecto.RoomArchitecture.Entities.PupusaEntity;

import java.util.List;

@Dao
public interface PupusasDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(PupusaEntity pupusaEntity);

    @Query("SELECT * FROM Bebidas_table ORDER BY name DESC")
    LiveData<List<PupusaEntity>> getAllPupusas();
}
