package io.umehara.lunchfinderandroid;

import android.util.Log;
import android.widget.ArrayAdapter;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitRestaurantRepo implements RestaurantRepo {
    private String baseURL = "http://lunch-finder-api.cfapps.io/";

    public ArrayAdapter<Restaurant> getAll(final ArrayAdapter<Restaurant> adapterRestaurants) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitRestaurantCaller service = retrofit.create(RetrofitRestaurantCaller.class);
        Call<List<Restaurant>> call = service.getAll();

        call.enqueue(new Callback<List<Restaurant>>() {
            @Override
            public void onResponse(Call<List<Restaurant>> call, retrofit2.Response<List<Restaurant>> response) {
                if (!response.isSuccessful()) {
                    Log.d("Unsuccessful response: ", response.message());
                }

                adapterRestaurants.addAll(response.body());
            }

            @Override
            public void onFailure(Call<List<Restaurant>> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });

        return adapterRestaurants;
    }

    public CompletableFuture<Restaurant> get(long id) {
        final CompletableFuture<Restaurant> futureRestaurant = new CompletableFuture<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitRestaurantCaller service = retrofit.create(RetrofitRestaurantCaller.class);
        Call<Restaurant> call = service.get(id);

        call.enqueue(new Callback<Restaurant>() {
            @Override
            public void onResponse(Call<Restaurant> call, Response<Restaurant> response) {
                if (!response.isSuccessful()) {
                    System.out.println("Unsuccessful response: " + response.message());
                    return;
                }

                futureRestaurant.complete(response.body());
            }

            @Override
            public void onFailure(Call<Restaurant> call, Throwable t) {
                System.out.println("Error" + t.getMessage());
            }
        });

        return futureRestaurant;
    }
}
