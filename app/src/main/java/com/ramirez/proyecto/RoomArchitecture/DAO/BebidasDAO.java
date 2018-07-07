package com.ramirez.proyecto.RoomArchitecture.DAO;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import com.ramirez.proyecto.RoomArchitecture.Entities.BebidaEntity;
import java.util.List;

@Dao
public interface BebidasDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(BebidaEntity bebida);

    @Query("SELECT * FROM Bebidas_table")
    LiveData<List<BebidaEntity>> getAllBebidas();

}
