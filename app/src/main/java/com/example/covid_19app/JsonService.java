package com.example.covid_19app;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonService {
    public ArrayList<Summary> parseCovidAPIData(String jsonCovidString,String provinceCode) {

        ArrayList<Summary> covidDataList = new ArrayList<>(0);
        try {

           JSONObject jsonObject = new JSONObject(jsonCovidString);// root
            JSONArray covidArray = jsonObject.getJSONArray("summary");
            covidDataList = new ArrayList<Summary>();
            for(int i=0;i<=covidArray.length();i++) {
                JSONObject covidObject = covidArray.getJSONObject(i);
                int active_cases = covidObject.getInt("active_cases");
                int recovered_cases = covidObject.getInt("recovered");
                int repoarted_cases = covidObject.getInt("cumulative_cases");
                int death_cases = covidObject.getInt("deaths");
                String date = covidObject.getString("date");
                String provincename = covidObject.getString("province");
                Summary covidData = new Summary();        //covidData is an object for Summary class
                covidData.setActive_cases(active_cases);
                covidData.setCumulative_cases(repoarted_cases);
                covidData.setRecovered(recovered_cases);
                covidData.setDeaths(death_cases);
                covidData.setDate(date);
                covidData.setProvince(provincename);
                covidData.setProvinceCode(provinceCode);
                covidDataList.add(covidData);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return covidDataList;
    }

        }

