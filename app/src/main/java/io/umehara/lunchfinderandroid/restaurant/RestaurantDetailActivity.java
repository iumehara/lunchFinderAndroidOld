package io.umehara.lunchfinderandroid.restaurant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;

import io.umehara.lunchfinderandroid.R;

public class RestaurantDetailActivity extends AppCompatActivity implements RestaurantDetailView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.restaurant_detail_activity);

        RestaurantDetailPresenter presenter = new RestaurantDetailPresenter();

        presenter.setView(this);

        Intent intent = getIntent();
        long restaurantId = intent.getIntExtra("restaurantId", -1);

        presenter.onCreate(restaurantId);
    }

    public void setLabel(Restaurant restaurant) {
        View restaurantDetailView = findViewById(R.id.restaurant_detail);
        TextView nameLabel = restaurantDetailView.findViewById(R.id.restaurant_detail_name);
        nameLabel.setText(restaurant.getName());

        TextView nameJpLabel = restaurantDetailView.findViewById(R.id.restaurant_detail_name_jp);
        nameJpLabel.setText(restaurant.getNameJp());
    }

    public void displayMap() {
        MapView mapView = findViewById(R.id.mapView);
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
