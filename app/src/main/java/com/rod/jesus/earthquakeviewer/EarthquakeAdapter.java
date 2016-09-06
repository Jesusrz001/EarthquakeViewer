package com.rod.jesus.earthquakeviewer;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.rod.jesus.earthquakeviewer.ValueObject.Earthquake;

import java.util.List;

/**
 * Created by jesus on 9/4/2016.
 */
public class EarthquakeAdapter extends RecyclerView.Adapter<EarthquakeAdapter.EarthquakeViewHolder> {
    private List<Earthquake> earthquakeList;
    private GoogleMap map;
    // Define listener member variable
    private OnItemClickListener listener;

    public EarthquakeAdapter(List<Earthquake> earthquakes) {
        this.earthquakeList = earthquakes;
    }

    // Define the method that allows the parent activity to define the listener
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int getItemCount() {

        if (earthquakeList != null) {
            return earthquakeList.size();
        } else {
            return 0;
        }
    }

    @Override
    public EarthquakeViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.earthquake_listview_layout, viewGroup, false);

        return new EarthquakeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(EarthquakeViewHolder placesViewHolder, int position) {
        Earthquake earthquake = earthquakeList.get(position);
        if (earthquake.getMagnitude() >= 8) {
            placesViewHolder.container.setBackgroundResource(R.drawable.earthquake_super);
        } else if (earthquake.getMagnitude() > 6) {
            placesViewHolder.container.setBackgroundResource(R.drawable.earthquake_major);
        } else if (earthquake.getMagnitude() > 4) {
            placesViewHolder.container.setBackgroundResource(R.drawable.earthquake_moderate);
        } else {
            placesViewHolder.container.setBackgroundResource(R.drawable.earthquake_small);
        }
        placesViewHolder.depth.setText("Depth: " + earthquake.getDepth());
        placesViewHolder.magnitude.setText(Double.toString(earthquake.getMagnitude()));

        placesViewHolder.eqid.setText("Earthquake ID: " + earthquake.getEqid());
        placesViewHolder.datetime.setText("Date: " + earthquake.getDateTime());


    }

    // Define the listener interface
    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    public class EarthquakeViewHolder extends RecyclerView.ViewHolder {

        protected TextView depth;
        protected TextView magnitude;
        protected TextView eqid;
        protected TextView datetime;
        protected RelativeLayout container;

        public EarthquakeViewHolder(View v) {
            super(v);
            container = (RelativeLayout) v.findViewById(R.id.listview_layout);
            depth = (TextView) v.findViewById(R.id.depth);
            magnitude = (TextView) v.findViewById(R.id.magnitude);
            eqid = (TextView) v.findViewById(R.id.eqid);
            datetime = (TextView) v.findViewById(R.id.datetime);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onItemClick(v, getLayoutPosition());
                    }
                }
            });
        }
    }
}
