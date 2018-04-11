package io.umehara.lunchfinderandroid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

public class RestaurantDetailActivity extends AppCompatActivity implements OnMapReadyCallback {
    private Context applicationContext;
    private RestaurantRepo restaurantRepo;
    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        constructor();

        setContentView(R.layout.activity_restaurant_detail);

        Intent intent = getIntent();
        long restaurantId = intent.getIntExtra("restaurantId", -1);
        System.out.println("restaurantId = " + restaurantId);


        mapView = findViewById(R.id.mapView);
        mapView.onCreate(null);
        mapView.getMapAsync(this);
    }

    private void constructor() {
        applicationContext = getApplicationContext();
        restaurantRepo = new RestaurantRepo(applicationContext);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.setMinZoomPreference(12);
        LatLng ny = new LatLng(40.7143528, -74.0059731);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(ny));
    }
}
