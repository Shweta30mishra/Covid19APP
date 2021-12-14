package com.example.covid_19app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.TextView;
//import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;
import androidx.appcompat.widget.SearchView;
public class MainActivity extends AppCompatActivity implements NetworkingService.NetworkingListener  {
    TextView textTitle;
    RecyclerView recyclerView;
    RecyclerAdapter   mAdapter;

    private RecyclerView.LayoutManager layoutManager;

    NetworkingService networkingService;
    JsonService jsonService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        networkingService = ( (myApp)getApplication()).getNetworkingService();
        jsonService = ( (myApp)getApplication()).getJsonService();
        networkingService.listener = this;
        networkingService.fetchCovidData("");          //call fetchdata from networkingService class

        textTitle = (TextView) findViewById(R.id.textView);
        textTitle.setText("Covid 19 Canada Cases");

        recyclerView = findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

    }


    @Override
    public void APINetworkListner(String jsonString,String provinceCode) {

       ArrayList<Summary> covidData = jsonService.parseCovidAPIData(jsonString, provinceCode);   //call jsonService
        mAdapter = new RecyclerAdapter(this,covidData);

        recyclerView.setAdapter((RecyclerView.Adapter) mAdapter);      //Set Adapter for Recyacler View
       mAdapter.notifyDataSetChanged();


    }

    public void provinceClicked(View view) {
        Intent intent = new Intent(this,ActivityProvince.class);
        startActivity(intent);

    }

    public void ShowPressed(View view) {
        Intent intent = new Intent(this,SaveDataActivity.class);
        startActivity(intent);
    }
}