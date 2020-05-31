package com.example.smartgreenhouse;

import java.util.LinkedList;

public class Preset {
    private String name;
    private int temperature;
    private int humidity;

    Preset()
    {
        this.name = "name";
        this.temperature = 24;
        this.humidity = 1;
    };

    Preset(String name, int temp, int lvlOfHydration)
    {
        this.name = name;
        this.temperature = temp;
        this.humidity = lvlOfHydration;
    };
    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }
    public int getHumidity() {
        return humidity;
    }
    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }
    public int getTemperature() {
        return temperature;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
