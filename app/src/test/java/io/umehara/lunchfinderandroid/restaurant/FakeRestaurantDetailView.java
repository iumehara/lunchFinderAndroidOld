package io.umehara.lunchfinderandroid.restaurant;

import com.google.android.gms.maps.GoogleMap;

import io.umehara.lunchfinderandroid.restaurant.Restaurant;
import io.umehara.lunchfinderandroid.restaurant.RestaurantDetailView;

public class FakeRestaurantDetailView implements RestaurantDetailView {

    public boolean setLabelWasCalled = false;
    @Override
    public void setLabel(Restaurant restaurant) {
        setLabelWasCalled = true;
    }

    public boolean displayMapWasCalled = false;
    @Override
    public void displayMap() {
        displayMapWasCalled = true;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}
