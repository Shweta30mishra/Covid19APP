package com.example.covid_19app;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(version = 1,entities = {SaveProvince.class})
public abstract class ProvinceDatabase extends RoomDatabase {

abstract provinceDAO getDao();
}
