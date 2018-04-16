package io.umehara.lunchfinderandroid;

import android.support.annotation.NonNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestaurantListPresenter {
    private RetrofitRestaurantCaller restaurantCaller;
    private RestaurantListView restaurantListView;

    RestaurantListPresenter(RestaurantListView restaurantListView) {
        this.restaurantListView = restaurantListView;
        String baseURL = "http://lunch-finder-api.cfapps.io/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        restaurantCaller = retrofit.create(RetrofitRestaurantCaller.class);
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
