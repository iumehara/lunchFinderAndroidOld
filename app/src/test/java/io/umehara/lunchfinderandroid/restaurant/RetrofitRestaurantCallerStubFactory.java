package io.umehara.lunchfinderandroid.restaurant;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitRestaurantCallerStubFactory {
    public static RetrofitRestaurantCaller getClient(Interceptor interceptor) {
        final OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        String baseURL = "http://example.com/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(RetrofitRestaurantCaller.class);
    }
}

class SuccessfulRestaurantDetailInterceptorStub implements okhttp3.Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        //language=json
        String responseString = "{\n" +
                "  \"id\": 1,\n" +
                "  \"name\": \"Pintokona\"\n" +
                "}";

        return chain.proceed(chain.request()).newBuilder()
                .code(200)
                .message(responseString)
                .request(chain.request())
                .protocol(Protocol.HTTP_1_0)
                .body(ResponseBody.create(MediaType.parse("application/json"), responseString.getBytes()))
                .addHeader("content-type", "application/json")
                .build();
    }
}

class SuccessfulRestaurantListInterceptorStub implements okhttp3.Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        //language=json
        String responseString = "[\n" +
                "  {\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"Pintokona\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 2,\n" +
                "    \"name\": \"Momodori\"\n" +
                "  }\n" +
                "]";

        return chain.proceed(chain.request()).newBuilder()
                .code(200)
                .message(responseString)
                .request(chain.request())
                .protocol(Protocol.HTTP_1_0)
                .body(ResponseBody.create(MediaType.parse("application/json"), responseString.getBytes()))
                .addHeader("content-type", "application/json")
                .build();
    }
}