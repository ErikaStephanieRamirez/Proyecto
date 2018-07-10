package com.ramirez.proyecto.RoomArchitecture.Repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.widget.Toast;

import com.ramirez.proyecto.API.Models.DesayunosRequest.DesayunosModel;
import com.ramirez.proyecto.API.Models.DesayunosRequest.FeedDesayunos;
import com.ramirez.proyecto.API.Models.FeedBebidas.Bebidas;
import com.ramirez.proyecto.API.Models.FeedBebidas.FeedBebidas;
import com.ramirez.proyecto.API.RepSazonAPI;
import com.ramirez.proyecto.RoomArchitecture.DAO.BebidasDAO;
import com.ramirez.proyecto.RoomArchitecture.DAO.DesayunosDAO;
import com.ramirez.proyecto.RoomArchitecture.Entities.BebidaEntity;
import com.ramirez.proyecto.RoomArchitecture.Entities.DesayunoEntity;
import com.ramirez.proyecto.RoomArchitecture.RepSazonDatabase;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DesayunoRepository {

    private DesayunosDAO desayunosDAO;
    private Application application;

    public DesayunoRepository(Application application){
        RepSazonDatabase db = RepSazonDatabase.getDatabase(application);
        desayunosDAO = db.desayunosDAO();
        this.application =application;
        fetchDesayunos();
    }

    public LiveData<List<DesayunoEntity>> getAllDesayunos(){
        return desayunosDAO.getAllDesayunos();
    }

    public void insert(DesayunoEntity desayunoEntity) {
        new DesayunoRepository.insertAsyncTask(desayunosDAO).execute(desayunoEntity);
    }

    public void fetchDesayunos() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(RepSazonAPI.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        RepSazonAPI repSazonAPI = retrofit.create(RepSazonAPI.class);
        Call<FeedDesayunos> call = repSazonAPI.getDesayunos();
        call.enqueue(new Callback<FeedDesayunos>() {
            @Override
            public void onResponse(Call<FeedDesayunos> call, Response<FeedDesayunos> response) {
                if(response.isSuccessful()){
                    ArrayList<DesayunosModel> desayunos = response.body().getDesayunos();

                    System.out.println(desayunos.get(0).getId());
                    System.out.println(desayunos.get(0).getNombre());
                    System.out.println(desayunos.get(0).getPrice());
                    System.out.println(desayunos.get(0).getProductImage());

                    String url = "https://rep-sazon.herokuapp.com/";
                    DesayunoEntity desayunoEntity;
                    for (int i=0;i<desayunos.size();i++){
                        desayunoEntity = new DesayunoEntity(desayunos.get(i).getId(),
                                desayunos.get(i).getNombre(),
                                desayunos.get(i).getPrice(),
                                url+desayunos.get(i).getProductImage().replace("uploads\\","uploads//"));
                        insert(desayunoEntity);
                    }
                }
            }
            @Override
            public void onFailure(Call<FeedDesayunos> call, Throwable t) {
                Toast.makeText(application.getApplicationContext(),"Sin Conexion a internet",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private static class insertAsyncTask extends AsyncTask<DesayunoEntity, Void, Void> {

        private DesayunosDAO mAsyncTaskDao;

        public insertAsyncTask(DesayunosDAO dao) {
            this.mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(DesayunoEntity... desayunoEntities) {
            mAsyncTaskDao.insert(desayunoEntities[0]);
            return null;
        }
    }
}
