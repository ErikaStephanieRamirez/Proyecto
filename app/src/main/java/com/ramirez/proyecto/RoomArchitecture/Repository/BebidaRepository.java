package com.ramirez.proyecto.RoomArchitecture.Repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.widget.Toast;

import com.ramirez.proyecto.API.Models.FeedBebidas.Bebidas;
import com.ramirez.proyecto.API.Models.FeedBebidas.FeedBebidas;
import com.ramirez.proyecto.API.RepSazonAPI;
import com.ramirez.proyecto.RoomArchitecture.DAO.BebidasDAO;
import com.ramirez.proyecto.RoomArchitecture.Entities.BebidaEntity;
import com.ramirez.proyecto.RoomArchitecture.RepSazonDatabase;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BebidaRepository {

    private BebidasDAO bebidasDAO;
    private Application application;

    public BebidaRepository(Application application){
        RepSazonDatabase db = RepSazonDatabase.getDatabase(application);
        bebidasDAO = db.bebidasDAO();
        this.application =application;
        fetchBebidas();
    }

    public LiveData<List<BebidaEntity>> getAllBebidas(){
        return bebidasDAO.getAllBebidas();
    }

    public void insert(BebidaEntity bebidaEntity) {
        new insertAsyncTask(bebidasDAO).execute(bebidaEntity);
    }

    public void fetchBebidas() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(RepSazonAPI.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        RepSazonAPI repSazonAPI = retrofit.create(RepSazonAPI.class);
        Call<FeedBebidas> call = repSazonAPI.getData();
        call.enqueue(new Callback<FeedBebidas>() {
            @Override
            public void onResponse(Call<FeedBebidas> call, Response<FeedBebidas> response) {
                if(response.isSuccessful()){
                    ArrayList<Bebidas> bebidas = response.body().getBebidas();
                    String url = "https://rep-sazon.herokuapp.com/";
                    BebidaEntity bebidaEntity;
                    for (int i=0;i<bebidas.size();i++){
                        bebidaEntity = new BebidaEntity(bebidas.get(i).getId(),
                                bebidas.get(i).getNombre(),
                                bebidas.get(i).getPrice(),
                                url+bebidas.get(i).getProductImage().replace("uploads\\","uploads//"));
                        insert(bebidaEntity);
                    }
                }
            }
            @Override
            public void onFailure(Call<FeedBebidas> call, Throwable t) {
                Toast.makeText(application.getApplicationContext(),"Sin Conexion a internet",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private static class insertAsyncTask extends AsyncTask<BebidaEntity, Void, Void> {

        private BebidasDAO mAsyncTaskDao;

        public insertAsyncTask(BebidasDAO dao) {
            this.mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(BebidaEntity... bebidaEntities) {
            mAsyncTaskDao.insert(bebidaEntities[0]);
            return null;
        }
    }

}
