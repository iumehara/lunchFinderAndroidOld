package io.umehara.lunchfinderandroid.category;

import java.util.List;

import io.umehara.lunchfinderandroid.restaurant.Restaurant;

class FakeCategoryDetailView implements CategoryDetailView {
    public Category setLabelsWasCalledWith;
    @Override
    public void setLabels(Category category) {
        setLabelsWasCalledWith = category;
    }

    public Category displayMapWasCalledWith;
    @Override
    public void displayMap(Category category) {
        displayMapWasCalledWith = category;
    }

    @Override
    public void setRow(List<Restaurant> restaurants) {

    }

    @Override
    public void startRestaurantDetailActivity(Integer restaurantId) {

    }
}
