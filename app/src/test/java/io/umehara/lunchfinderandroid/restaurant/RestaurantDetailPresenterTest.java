package io.umehara.lunchfinderandroid.restaurant;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import io.umehara.lunchfinderandroid.InterceptorSpy;

import static io.umehara.lunchfinderandroid.restaurant.RetrofitRestaurantCallerStubFactory.getClient;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class RestaurantDetailPresenterTest {
    private CountDownLatch lock = new CountDownLatch(1);


    @Test
    public void makesCorrectRequest() throws InterruptedException {
        InterceptorSpy interceptorSpy = new InterceptorSpy();
        RestaurantDetailPresenter presenter = new RestaurantDetailPresenter(getClient(interceptorSpy));
        FakeRestaurantDetailView fakeView = new FakeRestaurantDetailView();
        presenter.setView(fakeView);


        presenter.onCreate(5L);
        lock.await(300, TimeUnit.MILLISECONDS);


        assertThat(interceptorSpy.request.method(), equalTo("GET"));
        assertThat(interceptorSpy.request.url().toString(), equalTo("http://example.com/restaurants/5"));
    }

    @Test
    public void displaysSuccessfulResponse() throws InterruptedException {
        SuccessfulCategoryDetailInterceptorStub SuccessfulRestaurantDetailInterceptorStub = new SuccessfulCategoryDetailInterceptorStub();
        RestaurantDetailPresenter presenter = new RestaurantDetailPresenter(getClient(SuccessfulRestaurantDetailInterceptorStub));
        FakeRestaurantDetailView fakeView = new FakeRestaurantDetailView();
        presenter.setView(fakeView);


        presenter.onCreate(5L);
        lock.await(300, TimeUnit.MILLISECONDS);


        assertTrue(fakeView.setLabelWasCalled);
        assertTrue(fakeView.displayMapWasCalled);
    }
}