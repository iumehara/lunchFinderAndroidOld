package io.umehara.lunchfinderandroid.restaurant;

import java.util.List;

import io.umehara.lunchfinderandroid.restaurant.Restaurant;
import io.umehara.lunchfinderandroid.restaurant.RestaurantListView;

public class FakeRestaurantListView implements RestaurantListView {
    public List<Restaurant> setRowWasCalledWith;
    @Override
    public void setRow(List<Restaurant> restaurants) {
        setRowWasCalledWith = restaurants;
    }

    @Override
    public void startRestaurantDetailActivity(Integer restaurantId) {

    }
}
