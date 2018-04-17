package io.umehara.lunchfinderandroid;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitRestaurantCallerFactory {
    private static Retrofit retrofit = null;

    private RetrofitRestaurantCallerFactory() {}

    public static synchronized RetrofitRestaurantCaller getClient() {
        if (retrofit == null) {
            String baseURL = "http://10.0.2.2:8080/";
            retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        }

        return retrofit.create(RetrofitRestaurantCaller.class);
    }
}
