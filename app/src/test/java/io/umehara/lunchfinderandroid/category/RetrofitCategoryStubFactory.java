package io.umehara.lunchfinderandroid.category;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitCategoryStubFactory {
    public static RetrofitCategoryCaller getClient(Interceptor interceptor) {
        final OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        String baseURL = "http://example.com/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(RetrofitCategoryCaller.class);
    }
}

class SuccessfulCategoryDetailInterceptorStub implements okhttp3.Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        //language=json
        String responseString = "{\n" +
                "  \"id\": 1,\n" +
                "  \"name\": \"Spicy\"\n" +
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

class SuccessfulCategoryListInterceptorStub implements okhttp3.Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        //language=json
        String responseString = "[\n" +
                "  {\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"Spicy\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 2,\n" +
                "    \"name\": \"Sweet\"\n" +
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