package io.umehara.lunchfinderandroid.category;

import java.util.List;

import io.umehara.lunchfinderandroid.restaurant.Restaurant;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitCategoryCaller {
    @GET("categories")
    Call<List<Category>> getAll();

    @GET("categories/{id}")
    Call<Category> get(@Path("id") long id);

    @GET("categories/{id}/restaurants")
    Call<List<Restaurant>> getRestaurants(@Path("id") long id);
}
