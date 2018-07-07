package com.ramirez.proyecto.RoomArchitecture.Repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.widget.Toast;

import com.ramirez.proyecto.API.Models.PupusasRequest.FeedPupusas;
import com.ramirez.proyecto.API.Models.PupusasRequest.PupusasModel;
import com.ramirez.proyecto.API.RepSazonAPI;
import com.ramirez.proyecto.RoomArchitecture.DAO.PupusasDAO;
import com.ramirez.proyecto.RoomArchitecture.Entities.PupusaEntity;
import com.ramirez.proyecto.RoomArchitecture.RepSazonDatabase;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PupusaRepository {

    private PupusasDAO pupusasDAO;
    private Application application;

    public PupusaRepository(Application application){
        RepSazonDatabase db = RepSazonDatabase.getDatabase(application);
        pupusasDAO = db.pupusasDAO();
        this.application = application;
        fetchPupusas();
    }

    public LiveData<List<PupusaEntity>> getAllPupusas(){
        return pupusasDAO.getAllPupusas();
    }

    public void insert(PupusaEntity pupusaEntity) {
        new PupusaRepository.insertAsyncTask(pupusasDAO).execute(pupusaEntity);
    }

    public void fetchPupusas() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(RepSazonAPI.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        RepSazonAPI repSazonAPI = retrofit.create(RepSazonAPI.class);
        Call<FeedPupusas> call = repSazonAPI.getPupusas();
        call.enqueue(new Callback<FeedPupusas>() {
            @Override
            public void onResponse(Call<FeedPupusas> call, Response<FeedPupusas> response) {
                if(response.isSuccessful()){
                    Toast.makeText(application.getApplicationContext(),response.message(),Toast.LENGTH_LONG).show();
                    ArrayList<PupusasModel> pupusas = response.body().getPupusas();
                    String url = "https://rep-sazon.herokuapp.com/";
                    PupusaEntity pupusaEntity;
                    for (int i=0;i<pupusas.size();i++){
                        pupusaEntity = new PupusaEntity(pupusas.get(i).getId(),
                                pupusas.get(i).getNombre(),
                                pupusas.get(i).getPrice(),
                                url+pupusas.get(i).getProductImage().replace("uploads\\","uploads//"));
                        insert(pupusaEntity);
                    }
                }
            }
            @Override
            public void onFailure(Call<FeedPupusas> call, Throwable t) {
            }
        });
    }

    private static class insertAsyncTask extends AsyncTask<PupusaEntity, Void, Void> {

        private PupusasDAO mAsyncTaskDao;

        public insertAsyncTask(PupusasDAO dao) {
            this.mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(PupusaEntity... pupusaEntities) {
            mAsyncTaskDao.insert(pupusaEntities[0]);
            return null;
        }
    }
}
