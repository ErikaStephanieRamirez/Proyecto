package com.ramirez.proyecto.RoomArchitecture;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.ramirez.proyecto.RoomArchitecture.DAO.BebidasDAO;
import com.ramirez.proyecto.RoomArchitecture.DAO.DesayunosDAO;
import com.ramirez.proyecto.RoomArchitecture.DAO.PollosDAO;
import com.ramirez.proyecto.RoomArchitecture.DAO.PupusasDAO;
import com.ramirez.proyecto.RoomArchitecture.Entities.BebidaEntity;
import com.ramirez.proyecto.RoomArchitecture.Entities.DesayunoEntity;
import com.ramirez.proyecto.RoomArchitecture.Entities.PolloEntity;
import com.ramirez.proyecto.RoomArchitecture.Entities.PupusaEntity;

@Database(entities = {BebidaEntity.class, PupusaEntity.class, PolloEntity.class, DesayunoEntity.class}, exportSchema = false, version = 1)
public abstract class RepSazonDatabase extends RoomDatabase{

    public abstract BebidasDAO bebidasDAO();
    public abstract PupusasDAO pupusasDAO();
    public abstract PollosDAO pollosDAO();
    public abstract DesayunosDAO desayunosDAO();

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
