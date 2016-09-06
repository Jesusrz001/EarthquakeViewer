package com.rod.jesus.earthquakeviewer;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.rod.jesus.earthquakeviewer.ValueObject.Earthquake;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private final String TAG = MainActivity.class.getName();
    private Subscription subscription;
    private EarthquakeFragment earthquakeFragment;
    private List<Earthquake> earthquakeList = new ArrayList<>();
    private LinearLayoutManager llm;
    private EarthquakeAdapter earthquakeAdapter;

    private RecyclerView recList;
    Observer<EarthquakeList> earthquakeObserver = new Observer<EarthquakeList>() {

        @Override
        public void onCompleted() {
            subscription.unsubscribe();
        }

        @Override
        public void onError(Throwable e) {
            // Called when the observable encounters an error
            Log.d(TAG, ">>>> onError gets called : " + e.getMessage());
        }

        @Override
        public void onNext(EarthquakeList earthquakes) {
            //set it to the class variable so it can be manipulated for future use

            earthquakeList = earthquakes.getEarthquakes();
            findViewAndSetAdapter(earthquakeList);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        // find the retained fragment on activity restarts
        FragmentManager fm = getFragmentManager();
        earthquakeFragment = (EarthquakeFragment) fm.findFragmentByTag("EarthquakeFragment");
        // create the fragment and data the first time
        if (earthquakeFragment == null) {
            // add the fragment
            earthquakeFragment = new EarthquakeFragment();
            fm.beginTransaction().add(earthquakeFragment, "EarthquakeFragment").commit();
        } else {
            //saves having to do a network call
            earthquakeList = earthquakeFragment.getEarthquakes();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (earthquakeList.isEmpty()) {
            fetchEarthquakesList();
        } else {
            findViewAndSetAdapter(earthquakeList);
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        // store the data in the fragment
        earthquakeFragment.setData(earthquakeList);
    }

    private void setData() {
        earthquakeFragment.setData(earthquakeList);
    }

    private void fetchEarthquakesList() {
        final GeonameInterface service = RetrofitService.createRetrofitClient();

        subscription = service.loadEarthquakes()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(earthquakeObserver);
    }

    private void findViewAndSetAdapter(final List<Earthquake> earthquakeList) {

        recList = (RecyclerView) findViewById(R.id.earthquakeList);
        recList.setHasFixedSize(true);

        llm = new LinearLayoutManager(this);

        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);

        earthquakeAdapter = new EarthquakeAdapter(earthquakeList);
        earthquakeAdapter.setOnItemClickListener(new EarthquakeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String quakeInfo = earthquakeList.get(position).toString();
                Intent intent = new Intent(MainActivity.this, MapActivity.class);
                intent.putExtra("quakeInfo", quakeInfo);
                intent.putExtra("lat", earthquakeList.get(position).getLattitude());
                intent.putExtra("long", earthquakeList.get(position).getLongitude());
                startActivity(intent);
            }
        });
        System.out.println(earthquakeList.size());
        recList.setAdapter(earthquakeAdapter);
        earthquakeAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
