package com.rod.jesus.earthquakeviewer;

import android.app.Fragment;
import android.os.Bundle;

import com.rod.jesus.earthquakeviewer.ValueObject.Earthquake;

import java.util.List;

/**
 * Created by jesus on 9/4/2016.
 */
public class EarthquakeFragment extends Fragment {
    private final String TAG = EarthquakeFragment.class.getName();

    private List<Earthquake> earthquakeList;

    // this method is only called once for this fragment
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // retain this fragment
        setRetainInstance(true);
    }

    public void setData(List<Earthquake> earthquakeList) {
        this.earthquakeList = earthquakeList;
    }

    public List<Earthquake> getEarthquakes() {
        return earthquakeList;
    }
}
