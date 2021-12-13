package com.example.covid_19app;

import android.app.Application;

public class myApp extends Application {
    public NetworkingService getNetworkingService() {
        return networkingService;
    }
    private NetworkingService networkingService = new NetworkingService();
    private JsonService jsonService = new JsonService();
    public JsonService getJsonService() {
        return jsonService;
    }

    DatabaseService dbService = new DatabaseService();

    public DatabaseService getDbService() {
        return dbService;
    }

}
