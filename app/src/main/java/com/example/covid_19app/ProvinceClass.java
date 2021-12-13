package com.example.covid_19app;

public class ProvinceClass {
    private String provinceName;
    private int flagImage;

    public ProvinceClass(String provinceName,int flagImage) {
        this.provinceName = provinceName;
        this.flagImage=flagImage;
    }
    public String getProvinceName() {
        return provinceName;
    }

    public int getFlagImage() {
        return flagImage;
    }
}
