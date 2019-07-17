package com.rahul.todays.Pojo;

import java.util.ArrayList;

public class MyPojo {

    private String dt;

    private Coord coord;

    private String visibility;

    private ArrayList<Weather> weather;

    private String name;

    private String cod;

    private Main main;

    private Clouds clouds;

    private String id;

    private Sys sys;

    private String base;

    private Wind wind;

    public String getDt ()
    {
        return dt;
    }

    public void setDt (String dt)
    {
        this.dt = dt;
    }

    public Coord getCoord ()
    {
        return coord;
    }

    public void setCoord (Coord coord)
    {
        this.coord = coord;
    }

    public String getVisibility ()
    {
        return visibility;
    }

    public void setVisibility (String visibility)
    {
        this.visibility = visibility;
    }

    public ArrayList<Weather> getWeather ()
    {
        return weather;
    }

    public void setWeather (ArrayList<Weather> weather)
    {
        this.weather = weather;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getCod ()
    {
        return cod;
    }

    public void setCod (String cod)
    {
        this.cod = cod;
    }

    public Main getMain ()
    {
        return main;
    }

    public void setMain (Main main)
    {
        this.main = main;
    }

    public Clouds getClouds ()
    {
        return clouds;
    }

    public void setClouds (Clouds clouds)
    {
        this.clouds = clouds;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public Sys getSys ()
    {
        return sys;
    }

    public void setSys (Sys sys)
    {
        this.sys = sys;
    }

    public String getBase ()
    {
        return base;
    }

    public void setBase (String base)
    {
        this.base = base;
    }

    public Wind getWind ()
    {
        return wind;
    }

    public void setWind (Wind wind)
    {
        this.wind = wind;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [dt = "+dt+", coord = "+coord+", visibility = "+visibility+", weather = "+weather+", name = "+name+", cod = "+cod+", main = "+main+", clouds = "+clouds+", id = "+id+", sys = "+sys+", base = "+base+", wind = "+wind+"]";
    }

}
