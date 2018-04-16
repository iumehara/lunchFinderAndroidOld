package io.umehara.lunchfinderandroid;

import android.support.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestaurantDetailPresenter {
    private RetrofitRestaurantCaller restaurantCaller;
    private RestaurantDetailView restaurantDetailView;

    RestaurantDetailPresenter(RestaurantDetailView restaurantDetailView) {
        this.restaurantDetailView = restaurantDetailView;
        String baseURL = "http://lunch-finder-api.cfapps.io/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        restaurantCaller = retrofit.create(RetrofitRestaurantCaller.class);
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

