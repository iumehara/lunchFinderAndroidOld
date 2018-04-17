package io.umehara.lunchfinderandroid;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.Response;

public class InterceptorSpy implements okhttp3.Interceptor {
    public Request request;

    @Override
    public Response intercept(Chain chain) throws IOException {
        request = chain.request();
        return chain.proceed(chain.request()).newBuilder().build();
    }
}
