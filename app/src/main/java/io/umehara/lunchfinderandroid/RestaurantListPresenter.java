package io.umehara.lunchfinderandroid;

import android.support.annotation.NonNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class RestaurantListPresenter {
    private RetrofitRestaurantCaller restaurantCaller;
    private RestaurantListView restaurantListView;

    RestaurantListPresenter(RestaurantListView restaurantListView) {
        this.restaurantListView = restaurantListView;
        this.restaurantCaller = RetrofitRestaurantCallerFactory.getClient();
    }

    public void onCreate() {
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
                restaurantListView.setRow(response.body());
            }

            @Override
            public void onFailure(
                    @NonNull Call<List<Restaurant>> call,
                    @NonNull Throwable t
            ) {
                System.out.println("Error" + t.getMessage());
            }
        });
    }
}
