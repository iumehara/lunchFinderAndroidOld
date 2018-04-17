package io.umehara.lunchfinderandroid.category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitCategoryCaller {
    @GET("categories")
    Call<List<Category>> getAll();
}
