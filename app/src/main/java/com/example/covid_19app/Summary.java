package com.example.covid_19app;

import java.util.List;

public class Summary{
    public int active_cases;
    public int active_cases_change;
    public double avaccine;
    public int cases;
    public double cumulative_avaccine;
    public int cumulative_cases;
    public double cumulative_cvaccine;
    public double cumulative_deaths;
    public double cumulative_dvaccine;
    public double cumulative_recovered;
    public double cumulative_testing;
    public double cvaccine;
    public String date;
    public double deaths;
    public double dvaccine;
    public String province;
    public String provinceCode;
    public double recovered;
    public double testing;
    public String testing_info;
    public Summary() {
    }

    public int getActive_cases() {
        return active_cases;
    }

    public void setActive_cases(int active_cases) {
        this.active_cases = active_cases;
    }

    public int getCumulative_cases() {
        return cumulative_cases;
    }

    public void setCumulative_cases(int cumulative_cases) {
        this.cumulative_cases = cumulative_cases;
    }

    public double getDeaths() {
        return deaths;
    }

    public void setDeaths(double deaths) {
        this.deaths = deaths;
    }

    public double getRecovered() {
        return recovered;
    }

    public void setRecovered(double recovered) {
        this.recovered = recovered;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }
}


