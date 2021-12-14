package com.example.covid_19app;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import androidx.room.Room;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DatabaseService {
    interface DatabaseListener {
        void databaseProvincesListener(List<SaveProvince> dbProvince);
    }

    public DatabaseListener listener;
    public static ProvinceDatabase dbInstance;

    ExecutorService provincesExecutor = Executors.newFixedThreadPool(4);
    Handler provincesHandler = new Handler(Looper.getMainLooper());

    private void buildDB(Context context) {
        dbInstance = Room.databaseBuilder(context, ProvinceDatabase.class, "province_database").build();

    }

    public ProvinceDatabase getDbInstance(Context context) {
        if (dbInstance == null)
            buildDB(context);
        return dbInstance;
    }

    void getAllProvincesFromDB() {

        provincesExecutor.execute(new Runnable() {
            @Override
            public void run() {
                List<SaveProvince> provinces = dbInstance.getDao().getAllProvinces();
                provincesHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.databaseProvincesListener(provinces);
                    }
                });
            }
        });
    }

    void saveNewProvince(SaveProvince c) {
        provincesExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dbInstance.getDao().addNewProvince(c);
            }
        });
    }
    public void deleteProvince(SaveProvince c){
        provincesExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dbInstance.getDao().deleteProvince(c);
            }
        });
    }
    public void deleteProvinceByProvinceCode(String provinceCode){
        provincesExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dbInstance.getDao().deleteProvincebyProvinceCode(provinceCode);
            }
        });
    }
}