package com.rod.jesus.earthquakeviewer;

import com.google.gson.annotations.Expose;
import com.rod.jesus.earthquakeviewer.ValueObject.Earthquake;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jesus on 9/3/2016.
 */
public class EarthquakeList {
    @Expose
    private List<Earthquake> earthquakes = new ArrayList<>();

    /**
     * @return The earthquakes
     */
    public List<Earthquake> getEarthquakes() {
        return earthquakes;
    }
}
