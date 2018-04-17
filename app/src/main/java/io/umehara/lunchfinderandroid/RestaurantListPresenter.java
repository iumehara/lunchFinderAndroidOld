package io.umehara.lunchfinderandroid;

import android.support.annotation.NonNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class RestaurantListPresenter {
    private RetrofitRestaurantCaller restaurantCaller;
    private RestaurantListView restaurantListView;

    public RestaurantListPresenter() {
        this.restaurantCaller = RetrofitRestaurantCallerFactory.getClient();
    }

    RestaurantListPresenter(RetrofitRestaurantCaller restaurantCaller) {
        this.restaurantCaller = restaurantCaller;
    }

    public void setView(RestaurantListView restaurantListView) {
        this.restaurantListView = restaurantListView;
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
