package com.ramirez.proyecto.RoomArchitecture.DAO;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.ramirez.proyecto.RoomArchitecture.Entities.PolloEntity;

import java.util.List;

@Dao
public interface PollosDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(PolloEntity polloEntity);

    @Query("SELECT * FROM Pollos_table ORDER BY name DESC")
    LiveData<List<PolloEntity>> getAllPollos();

}
