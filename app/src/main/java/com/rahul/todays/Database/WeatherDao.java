package com.rahul.todays.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface WeatherDao {


    @Query("SELECT * FROM weather")
    List<Weather> getAllDetails();

    @Insert
    void insertAll(Weather... weathers);

    @Update
    void updateWeather(Weather... weathers);

}
