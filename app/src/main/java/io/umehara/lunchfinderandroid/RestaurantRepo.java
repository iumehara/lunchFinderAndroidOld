package io.umehara.lunchfinderandroid;

import android.widget.ArrayAdapter;

import java.util.concurrent.CompletableFuture;

public interface RestaurantRepo {
    ArrayAdapter<Restaurant> getAll(ArrayAdapter<Restaurant> layoutResource);
    CompletableFuture<Restaurant> get(long id);
}
