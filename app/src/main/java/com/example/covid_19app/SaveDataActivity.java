package com.example.covid_19app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SaveDataActivity extends AppCompatActivity implements DatabaseService.DatabaseListener,NetworkingService.NetworkingListener, AdapterView.OnItemSelectedListener {
    DatabaseService dbService;
    NetworkingService networkingService;
    JsonService jsonService;
    RecyclerView recyclerView;
    ProvincedbAdapter   Adapter;
    ArrayList<Summary> covidData;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_data);
        dbService = ((myApp)getApplication()).getDbService();
        dbService.getDbInstance(this);
        dbService.getAllProvincesFromDB();
        dbService.listener = this;

        networkingService = ( (myApp)getApplication()).getNetworkingService();
        networkingService.listener = this;
        jsonService = ( (myApp)getApplication()).getJsonService();


        recyclerView = findViewById(R.id.SaveProvince);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
       // recyclerView.setHasFixedSize(true);
         covidData= new ArrayList<Summary>();

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public void databaseProvincesListener(List<SaveProvince> dbProvince) {

        for(int i=0;i<dbProvince.size();i++){
      networkingService.fetchCovidData("summary?loc="+dbProvince.get(i).getProvinceID());
    }
    }

    @Override
    public void APINetworkListner(String jsonString) {
         covidData.add(jsonService.parseCovidAPIData(jsonString).get(0));
       // Adapter = new RecyclerAdapter(this,covidData);
        Adapter = new ProvincedbAdapter(this,covidData);
        recyclerView.setAdapter((RecyclerView.Adapter) Adapter);      //Set Adapter for Recyacler View
        Adapter.notifyDataSetChanged();

    }
    ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.DOWN, ItemTouchHelper.LEFT |
            ItemTouchHelper.RIGHT |
            ItemTouchHelper.DOWN |
            ItemTouchHelper.UP) {

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            Toast.makeText(SaveDataActivity.this, "Item Moveing", Toast.LENGTH_SHORT).show();

            return false;
        }
        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
            //Remove swiped item from list and notify the RecyclerView
            int position = viewHolder.getAdapterPosition();
            SaveProvince saveProvince = new SaveProvince();
          //  saveProvince.getProvinceID();
        //   dbService.deleteProvince(saveProvince.setProvinceID(covidData.get(position).););
           Adapter.listRecyclerItem.remove(position);

            // Adapter.carList.remove(position);
            // we have to remove it from db as well

            Adapter.notifyDataSetChanged();

        }
    };

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}