package io.umehara.lunchfinderandroid;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface RestaurantRepo {
    CompletableFuture<List<Restaurant>> getAll();
    CompletableFuture<Restaurant> get(long id);
}
