package com.rod.jesus.earthquakeviewer;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by jesus on 9/3/2016.
 */
public interface GeonameInterface {
    //This method used for "GET"
    // http://api.geonames.org/
    @GET("earthquakesJSON?formatted=true&north=44.1&south=-9.9&east=-22.4&west=55.2&username=replaceusername")
    Observable<EarthquakeList> loadEarthquakes();
}
