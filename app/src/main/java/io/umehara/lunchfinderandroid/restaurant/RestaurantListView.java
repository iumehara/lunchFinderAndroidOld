package io.umehara.lunchfinderandroid.restaurant;

import io.umehara.lunchfinderandroid.FragmentListView;

public interface RestaurantListView extends FragmentListView {
    void startRestaurantDetailActivity(Integer restaurantId);
}
