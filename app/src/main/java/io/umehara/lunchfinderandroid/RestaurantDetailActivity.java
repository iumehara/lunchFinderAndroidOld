package io.umehara.lunchfinderandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

import java.util.concurrent.CompletableFuture;

public class RestaurantDetailActivity extends AppCompatActivity implements OnMapReadyCallback {
    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_restaurant_detail);

        RetrofitRestaurantRepo restaurantRepo = new RetrofitRestaurantRepo();

        Intent intent = getIntent();
        long restaurantId = intent.getIntExtra("restaurantId", -1);
        CompletableFuture<Restaurant> restaurantCompletableFuture = restaurantRepo.get(restaurantId);

        restaurantCompletableFuture
                .thenAccept(restaurant -> setLabel(restaurant));

        displayMap();
    }

    private void setLabel(Restaurant restaurant) {
        View restaurantDetailView = findViewById(R.id.restaurant_detail);
        final TextView nameLabel = restaurantDetailView.findViewById(R.id.restaurant_detail_name);
        nameLabel.setText(restaurant.getName());
    }

    private void displayMap() {
        mapView = findViewById(R.id.mapView);
        mapView.onCreate(null);
        mapView.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.setMinZoomPreference(12);
        LatLng ny = new LatLng(40.7143528, -74.0059731);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(ny));
    }
}
