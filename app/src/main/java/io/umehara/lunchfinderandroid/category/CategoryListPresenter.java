package io.umehara.lunchfinderandroid.category;

import android.support.annotation.NonNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class CategoryListPresenter {
    private final RetrofitCategoryCaller categoryCaller;
    private CategoryListView categoryListView;

    public CategoryListPresenter() {
        categoryCaller = RetrofitCategoryCallerFactory.getClient();
    }

    public CategoryListPresenter(RetrofitCategoryCaller caller) {
        categoryCaller = caller;
    }

    public void setView(CategoryListView categoryListView) {
        this.categoryListView = categoryListView;
    }

    public void onCreate() {
        Call<List<Category>> call = categoryCaller.getAll();

        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(@NonNull Call<List<Category>> call, @NonNull Response<List<Category>> response) {
                if (!response.isSuccessful()) {
                    System.out.println("Unsuccessful response: " + response.message());
                    return;
                }
                categoryListView.setOnAdapter(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<Category>> call, @NonNull Throwable t) {
                System.out.println("Error" + t.getMessage());
            }
        });
    }
}
