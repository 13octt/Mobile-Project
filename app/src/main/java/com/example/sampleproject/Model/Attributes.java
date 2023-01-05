
package com.example.sampleproject.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attributes {

    @SerializedName("rainfall")
    @Expose
    private Rainfall rainfall;
    @SerializedName("weatherData")
    @Expose
    private WeatherData weatherData;
    @SerializedName("location")
    @Expose
    private Location1 location;

    public Rainfall getRainfall() {
        return rainfall;
    }

    public void setRainfall(Rainfall rainfall) {
        this.rainfall = rainfall;
    }

    public WeatherData getWeatherData() {
        return weatherData;
    }

    public void setWeatherData(WeatherData weatherData) {
        this.weatherData = weatherData;
    }

    public Location1 getLocation() {
        return location;
    }

    public void setLocation(Location1 location) {
        this.location = location;
    }

}
