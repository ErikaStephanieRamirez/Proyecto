package com.ramirez.proyecto.RoomArchitecture.Repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.widget.Toast;

import com.ramirez.proyecto.API.Models.PollosRequest.FeedPollos;
import com.ramirez.proyecto.API.Models.PollosRequest.PollosModel;
import com.ramirez.proyecto.API.RepSazonAPI;
import com.ramirez.proyecto.RoomArchitecture.DAO.PollosDAO;
import com.ramirez.proyecto.RoomArchitecture.Entities.PolloEntity;
import com.ramirez.proyecto.RoomArchitecture.RepSazonDatabase;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PolloRepository {


    private PollosDAO pollosDAO;
    private Application application;

    public PolloRepository(Application application){
        RepSazonDatabase db = RepSazonDatabase.getDatabase(application);
        pollosDAO = db.pollosDAO();
        this.application = application;
        fetchPollos();
    }

    public LiveData<List<PolloEntity>> getAllPollos(){
        return pollosDAO.getAllPollos();
    }

    public void insert(PolloEntity polloEntity) {
        new PolloRepository.insertAsyncTask(pollosDAO).execute(polloEntity);
    }

    public void fetchPollos() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(RepSazonAPI.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        RepSazonAPI repSazonAPI = retrofit.create(RepSazonAPI.class);
        Call<FeedPollos> call = repSazonAPI.getPollos();
        call.enqueue(new Callback<FeedPollos>() {
            @Override
            public void onResponse(Call<FeedPollos> call, Response<FeedPollos> response) {
                if(response.isSuccessful()){
                    ArrayList<PollosModel> pollos = response.body().getPollos();
                    String url = "https://rep-sazon.herokuapp.com/";
                    PolloEntity polloEntity;
                    for (int i=0;i<pollos.size();i++){
                        polloEntity = new PolloEntity(pollos.get(i).getId(),
                                pollos.get(i).getNombre(),
                                pollos.get(i).getPrice(),
                                pollos.get(i).getPriceCombo(),
                                url+pollos.get(i).getProductImage().replace("uploads\\","uploads//"));
                        insert(polloEntity);
                    }
                }
            }
            @Override
            public void onFailure(Call<FeedPollos> call, Throwable t) {
            }
        });
    }

    private static class insertAsyncTask extends AsyncTask<PolloEntity, Void, Void> {

        private PollosDAO mAsyncTaskDao;

        public insertAsyncTask(PollosDAO dao) {
            this.mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(PolloEntity... polloEntities) {
            mAsyncTaskDao.insert(polloEntities[0]);
            return null;
        }
    }
}
