package io.umehara.lunchfinderandroid;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

interface RetrofitRestaurantCaller {
    @GET("restaurants")
    Call<List<Restaurant>> getAll();

    @GET("restaurants/{id}")
    Call<Restaurant> get(@Path("id") long id);
}