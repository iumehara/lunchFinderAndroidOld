package io.umehara.lunchfinderandroid.category;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitCategoryCallerFactory {
    private static Retrofit retrofit = null;

    private RetrofitCategoryCallerFactory() {}

    public static synchronized RetrofitCategoryCaller getClient() {
        if (retrofit == null) {
            String baseURL = "http://10.0.2.2:8080/";
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }

        return retrofit.create(RetrofitCategoryCaller.class);
    }
}
