package io.umehara.lunchfinderandroid;

import android.support.annotation.NonNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;
import okhttp3.Response;

public class InterceptorSpy implements okhttp3.Interceptor {
    public Request request;
    public List<Request> requests = new ArrayList<>();

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        request = chain.request();
        requests.add(request);
        return chain.proceed(chain.request()).newBuilder().build();
    }
}
