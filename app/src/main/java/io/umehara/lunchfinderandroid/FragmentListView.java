package io.umehara.lunchfinderandroid;

import java.util.List;

import io.umehara.lunchfinderandroid.restaurant.Restaurant;

public interface FragmentListView {
    void setRow(List<Restaurant> restaurants);
}
