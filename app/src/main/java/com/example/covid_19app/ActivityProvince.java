package com.example.covid_19app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.List;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
public class ActivityProvince extends AppCompatActivity implements NetworkingService.NetworkingListener, AdapterView.OnItemSelectedListener,DatabaseService.DatabaseListener{

    Spinner spin;
   // String[] provinceList;
   // ArrayList<ProvinceClass> arraylist = new ArrayList<ProvinceClass>();
   private ArrayList<ProvinceClass> provinceList;
    private SpinnerAdapter spinnerAdapter;

    NetworkingService networkingService;
    JsonService jsonService;
    RecyclerView recyclerView;
    RecyclerAdapter   mAdapter;
    DatabaseService dbService;
    private RecyclerView.LayoutManager layoutManager;
    String provinceCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_province);

        networkingService = ((myApp) getApplication()).getNetworkingService();
        jsonService = ((myApp) getApplication()).getJsonService();
        networkingService.listener = this;

        dbService = ((myApp) getApplication()).getDbService();
        dbService.getDbInstance(this);
        dbService.listener = this;

       initList();
        spin = findViewById(R.id.spinner);
    //    spin.setOnItemSelectedListener(this);


        recyclerView = findViewById(R.id.recyclerviewProvince);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        // Generate sample data
        spinnerAdapter = new SpinnerAdapter(this, provinceList);
        spin.setAdapter(spinnerAdapter);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ProvinceClass clickedItem = (ProvinceClass) parent.getItemAtPosition(position);
               // String clickedCountryName = clickedItem.getProvinceName();
                switch (position) {
                    case 0:
                        networkingService.fetchCovidData("summary?loc=ON");
                        provinceCode = "ON";
                        break;
                    case 1:
                        networkingService.fetchCovidData("summary?loc=BC");
                        provinceCode = "BC";
                        break;
                    case 2:
                        networkingService.fetchCovidData("summary?loc=MB");
                        provinceCode = "MB";
                        break;
                    case 3:
                        networkingService.fetchCovidData("summary?loc=QC");
                        provinceCode = "QC";
                        break;
                    case 4:
                        networkingService.fetchCovidData("summary?loc=AB");
                        provinceCode = "AB";
                        break;
                    case 5:
                        networkingService.fetchCovidData("summary?loc=NB");
                        provinceCode = "NB";

                        break;
                    case 6:
                        networkingService.fetchCovidData("summary?loc=NL");
                        provinceCode = "NL";

                        break;
                    case 7:
                        networkingService.fetchCovidData("summary?loc=NT");
                        provinceCode = "NT";

                        break;
                    case 8:
                        networkingService.fetchCovidData("summary?loc=NS");
                        provinceCode = "NS";

                        break;
                    case 9:
                        networkingService.fetchCovidData("summary?loc=NU");
                        provinceCode = "NU";

                    default:
                        networkingService.fetchCovidData("summary?loc=YT");
                        provinceCode = "YT";

                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

        private void initList () {
            provinceList = new ArrayList<>();
            provinceList.add(new ProvinceClass("Ontario", R.drawable.ontario));
            provinceList.add(new ProvinceClass("British columbia", R.drawable.bc));
            provinceList.add(new ProvinceClass("Manitoba", R.drawable.manitoba));
            provinceList.add(new ProvinceClass("Quebec", R.drawable.quebec));
            provinceList.add(new ProvinceClass("Alberta",R.drawable.alberta));
            provinceList.add(new ProvinceClass("New Brunswick",R.drawable.newbrunswick));
            provinceList.add(new ProvinceClass("Newfoundland and Labrador",R.drawable.newfoundland));
            provinceList.add(new ProvinceClass("Northwest Territories",R.drawable.nor));
            provinceList.add(new ProvinceClass("Nova Scotia",R.drawable.novascotia));
            provinceList.add(new ProvinceClass("Nunavut",R.drawable.nunavut));
            provinceList.add(new ProvinceClass("Yukon",R.drawable.yukon));
        }

    @Override
    public void APINetworkListner(String jsonString) {

        ArrayList<Summary> covidData = jsonService.parseCovidAPIData(jsonString);   //call jsonService
        mAdapter = new RecyclerAdapter(this,covidData);
        recyclerView.setAdapter((RecyclerView.Adapter) mAdapter);      //Set Adapter for Recyacler View
        mAdapter.notifyDataSetChanged();

    }

    public void provinceclicked(View view) {
      //  Intent intent = new Intent(this,SaveDataActivity.class);
      //  startActivity(intent);
        SaveProvince saveProvince = new SaveProvince();
        saveProvince.setProvinceID(provinceCode);
        dbService.saveNewProvince(saveProvince);

    }

    public void ShowClicked(View view) {
        Intent intent = new Intent(this,SaveDataActivity.class);
        startActivity(intent);
    }

    @Override
    public void databaseProvincesListener(List<SaveProvince> dbProvince) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

