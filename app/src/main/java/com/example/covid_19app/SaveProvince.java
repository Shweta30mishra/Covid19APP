package com.example.covid_19app;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class SaveProvince {
    @PrimaryKey(autoGenerate = true)
   private int id;
   private String provinceID;
    private String provinceName;

SaveProvince(){
this.provinceID = provinceID;
this.provinceName = provinceName;
}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProvinceID() {
        return provinceID;
    }

    public void setProvinceID(String provinceID) {
        this.provinceID = provinceID;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }
}
