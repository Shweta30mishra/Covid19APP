package com.example.covid_19app;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface  provinceDAO {
    @Insert
    void addNewProvince(SaveProvince c);
    @Delete
    void deleteProvince(SaveProvince c);


    @Query("SELECT * FROM SaveProvince")
    List<SaveProvince> getAllProvinces();


}
