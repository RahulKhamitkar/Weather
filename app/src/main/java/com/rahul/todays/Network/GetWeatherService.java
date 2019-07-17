package com.rahul.todays.Network;

import com.rahul.todays.Pojo.MyPojo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetWeatherService {

    @GET("2.5/weather")
    Call<MyPojo> getDetails(@Query("q") String cityName,
                                 @Query("appid") String appid);
}
