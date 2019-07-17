package com.rahul.todays.Database;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Weather {
    @PrimaryKey(autoGenerate = true)

    private int id;

    @ColumnInfo(name = "city_name")
    private String cityname;
    @ColumnInfo(name = "rain_percentage")
    private String rainpercentage;

    @ColumnInfo(name = "condition")
    private String condition;
    @ColumnInfo(name = "humidity")
    private String humidity;
    @ColumnInfo(name = "min_temp")
    private String mintemp;
    @ColumnInfo(name = "max_temp")
    private String maxtemp;

    public Weather(String cityname, String rainpercentage, String condition, String humidity, String mintemp, String maxtemp) {
        this.cityname = cityname;
        this.rainpercentage = rainpercentage;

        this.condition = condition;
        this.humidity = humidity;
        this.mintemp = mintemp;
        this.maxtemp = maxtemp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public String getRainpercentage() {
        return rainpercentage;
    }

    public void setRainpercentage(String rainpercentage) {
        this.rainpercentage = rainpercentage;
    }


    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getMintemp() {
        return mintemp;
    }

    public void setMintemp(String mintemp) {
        this.mintemp = mintemp;
    }

    public String getMaxtemp() {
        return maxtemp;
    }

    public void setMaxtemp(String maxtemp) {
        this.maxtemp = maxtemp;
    }
}
