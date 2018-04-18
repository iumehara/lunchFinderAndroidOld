package io.umehara.lunchfinderandroid.category;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class CategoryDetailPresenter {
    private RetrofitCategoryCaller categoryCaller;
    private CategoryDetailView view;

    CategoryDetailPresenter() {
        this.categoryCaller = RetrofitCategoryCallerFactory.getClient();
    }

    CategoryDetailPresenter(RetrofitCategoryCaller categoryCaller) {
        this.categoryCaller = categoryCaller;
    }

    public void setView(CategoryDetailView view) {
        this.view = view;
    }

    public void onCreate(long id) {
        Call<Category> call = categoryCaller.get(id);

        call.enqueue(new Callback<Category>() {
            @Override
            public void onResponse(Call<Category> call, Response<Category> response) {
                if (!response.isSuccessful()) {
                    System.out.println("Unsuccessful response: " + response.message());
                    return;
                }
                view.setLabels(response.body());
                view.displayMap(response.body());
            }

            @Override
            public void onFailure(Call<Category> call, Throwable t) {
                System.out.println("Error" + t.getMessage());
            }
        });
    }
}
