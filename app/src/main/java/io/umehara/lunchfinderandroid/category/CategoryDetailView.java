package io.umehara.lunchfinderandroid.category;

import io.umehara.lunchfinderandroid.restaurant.RestaurantListView;

interface CategoryDetailView extends RestaurantListView {
    void setLabels(Category category);
    void displayMap(Category category);
}
