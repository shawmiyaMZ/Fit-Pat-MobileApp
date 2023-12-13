package com.example.fitpatapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.example.fitpatapp.databinding.ActivityRouteTracker2Binding;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.concurrent.TimeUnit;

public class RouteTrackerActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityRouteTracker2Binding binding;

    private PolylineOptions polylineOptions;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private Location previousLocation;
    private float totalDistance;
    private long startTime;

    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRouteTracker2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);






        startButton = findViewById(R.id.start_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTracking();
            }
        });


    }

    private void startTracking() {
        // Reset tracking state
        mMap.clear();
        polylineOptions = new PolylineOptions();
        polylineOptions.color(Color.RED);
        polylineOptions.width(20);
        previousLocation = null;
        totalDistance = 0;
        startTime = System.currentTimeMillis();
        updateUI();

        // Start tracking location updates
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, locationListener);
    }



    // Display the distance traveled and time duration
    private void updateUI() {
        TextView distanceTextView = findViewById(R.id.distance_text);
        distanceTextView.setText(String.format("Distance: %.2f meters", totalDistance));

        TextView timeTextView = findViewById(R.id.time_text);
        long duration = System.currentTimeMillis() - startTime;
        timeTextView.setText(String.format("Time: %d seconds", TimeUnit.MILLISECONDS.toSeconds(duration)));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.clear(); // Clear the map
        polylineOptions = new PolylineOptions();
        polylineOptions.color(Color.RED);
        polylineOptions.width(20);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            private static final float MIN_DISTANCE = 1; // minimum distance in meters to update marker

            @Override
            public void onLocationChanged(Location location) {
                float distance = 0;
                if (previousLocation != null) {
                    // Calculate the distance between the previous location and the current location
                    distance = location.distanceTo(previousLocation);
                }
                previousLocation = location;

                if (distance > MIN_DISTANCE) {
                    totalDistance += distance;
                    updateUI();

                    LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 20));
                    polylineOptions.add(latLng);
                    mMap.addPolyline(polylineOptions);
                }
            }
        };

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        // blue circle
        mMap.setMyLocationEnabled(true);

        //minimum time and distance to draw the red line
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, locationListener);
    }
}