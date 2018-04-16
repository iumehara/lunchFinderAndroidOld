package io.umehara.lunchfinderandroid;

import android.support.annotation.NonNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitRestaurantRepo implements RestaurantRepo {
    private RetrofitRestaurantCaller restaurantCaller;

    RetrofitRestaurantRepo() {
        String baseURL = "http://lunch-finder-api.cfapps.io/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        restaurantCaller = retrofit.create(RetrofitRestaurantCaller.class);
    }

    public CompletableFuture<List<Restaurant>> getAll() {
        final CompletableFuture<List<Restaurant>> futureRestaurants = new CompletableFuture<>();

        Call<List<Restaurant>> call = restaurantCaller.getAll();

        call.enqueue(new Callback<List<Restaurant>>() {
            @Override
            public void onResponse(
                    @NonNull Call<List<Restaurant>> call,
                    @NonNull retrofit2.Response<List<Restaurant>> response
            ) {
                if (!response.isSuccessful()) {
                    System.out.println("Unsuccessful response: " + response.message());
                    return;
                }

                futureRestaurants.complete(response.body());
            }

            @Override
            public void onFailure(
                    @NonNull Call<List<Restaurant>> call,
                    @NonNull Throwable t
            ) {
                System.out.println("Error" + t.getMessage());
            }
        });

        return futureRestaurants;
    }

    public CompletableFuture<Restaurant> get(long id) {
        final CompletableFuture<Restaurant> futureRestaurant = new CompletableFuture<>();

        Call<Restaurant> call = restaurantCaller.get(id);

        call.enqueue(new Callback<Restaurant>() {
            @Override
            public void onResponse(
                    @NonNull Call<Restaurant> call,
                    @NonNull Response<Restaurant> response
            ) {
                if (!response.isSuccessful()) {
                    System.out.println("Unsuccessful response: " + response.message());
                    return;
                }

                futureRestaurant.complete(response.body());
            }

            @Override
            public void onFailure(
                    @NonNull Call<Restaurant> call,
                    @NonNull Throwable t
            ) {
                System.out.println("Error" + t.getMessage());
            }
        });

        return futureRestaurant;
    }
}
