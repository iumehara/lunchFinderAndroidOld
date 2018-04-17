package io.umehara.lunchfinderandroid;

import java.util.List;

public class FakeRestaurantListView implements RestaurantListView {
    public List<Restaurant> setRowWasCalledWith;
    @Override
    public void setRow(List<Restaurant> restaurants) {
        setRowWasCalledWith = restaurants;
    }
}
