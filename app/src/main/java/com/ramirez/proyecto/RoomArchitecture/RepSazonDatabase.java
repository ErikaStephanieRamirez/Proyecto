package com.ramirez.proyecto.RoomArchitecture;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.ramirez.proyecto.RoomArchitecture.DAO.BebidasDAO;
import com.ramirez.proyecto.RoomArchitecture.Entities.BebidaEntity;

@Database(entities = {BebidaEntity.class}, exportSchema = false, version = 1)
public abstract class RepSazonDatabase extends RoomDatabase{

    public abstract BebidasDAO bebidasDAO();

    private static RepSazonDatabase INSTANCE;

    public static RepSazonDatabase getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (RepSazonDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),RepSazonDatabase.class,"Rep_Sazon_Database").build();
                }
            }
        }
        return INSTANCE;
    }
}