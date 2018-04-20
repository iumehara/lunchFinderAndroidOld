package io.umehara.lunchfinderandroid.category;

import android.support.annotation.NonNull;

import java.util.List;

import io.umehara.lunchfinderandroid.restaurant.Restaurant;
import io.umehara.lunchfinderandroid.restaurant.RestaurantListView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class CategoryDetailPresenter {
    private RetrofitCategoryCaller categoryCaller;
    private CategoryDetailView view;
    private RestaurantListView restaurantListView;

    CategoryDetailPresenter() {
        this.categoryCaller = RetrofitCategoryCallerFactory.getClient();
    }

    CategoryDetailPresenter(RetrofitCategoryCaller categoryCaller) {
        this.categoryCaller = categoryCaller;
    }

    public void setView(CategoryDetailView view) {
        this.view = view;
        this.restaurantListView = view;
    }

    public void onCreate(long id) {
        fetchAndSetCategory(id);
        fetchAndSetRestaurants(id);
    }

    private void fetchAndSetRestaurants(long id) {
        Call<List<Restaurant>> restaurantsCall = categoryCaller.getRestaurants(id);
        restaurantsCall.enqueue(new Callback<List<Restaurant>>() {
            @Override
            public void onResponse(@NonNull Call<List<Restaurant>> call, @NonNull Response<List<Restaurant>> response) {
                if (!response.isSuccessful()) {
                    System.out.println("Unsuccessful response: " + response.message());
                    return;
                }
                restaurantListView.setRow(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<Restaurant>> call, @NonNull Throwable t) {
                System.out.println("Error" + t.getMessage());
            }
        });
    }

    private void fetchAndSetCategory(long id) {
        Call<Category> categoryCall = categoryCaller.get(id);
        categoryCall.enqueue(new Callback<Category>() {
            @Override
            public void onResponse(@NonNull Call<Category> call, @NonNull Response<Category> response) {
                if (!response.isSuccessful()) {
                    System.out.println("Unsuccessful response: " + response.message());
                    return;
                }
                view.setLabels(response.body());
                view.displayMap(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<Category> call, @NonNull Throwable t) {
                System.out.println("Error" + t.getMessage());
            }
        });
    }
}
