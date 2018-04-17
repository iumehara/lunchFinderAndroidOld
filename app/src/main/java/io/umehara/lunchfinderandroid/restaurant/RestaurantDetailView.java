package io.umehara.lunchfinderandroid.restaurant;

import com.google.android.gms.maps.OnMapReadyCallback;

public interface RestaurantDetailView extends OnMapReadyCallback {
    void setLabel(Restaurant restaurant);
    void displayMap();
}
