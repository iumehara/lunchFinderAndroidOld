package io.umehara.lunchfinderandroid;

import android.support.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestaurantDetailPresenter {
    private RetrofitRestaurantCaller restaurantCaller;
    private RestaurantDetailView restaurantDetailView;

    RestaurantDetailPresenter() {
        this.restaurantCaller = RetrofitRestaurantCallerFactory.getClient();
    }

    RestaurantDetailPresenter(RetrofitRestaurantCaller restaurantCaller) {
        this.restaurantCaller = restaurantCaller;
    }

    public void setView(RestaurantDetailView restaurantDetailView) {
        this.restaurantDetailView = restaurantDetailView;
    }

    public void onCreate(long id) {
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

                restaurantDetailView.setLabel(response.body());
                restaurantDetailView.displayMap();
            }

            @Override
            public void onFailure(
                    @NonNull Call<Restaurant> call,
                    @NonNull Throwable t
            ) {
                System.out.println("Error" + t.getMessage());
            }
        });
    }
}

