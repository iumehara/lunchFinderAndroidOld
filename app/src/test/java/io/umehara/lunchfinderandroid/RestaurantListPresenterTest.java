package io.umehara.lunchfinderandroid;

import org.junit.Test;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class RestaurantListPresenterTest {
    private CountDownLatch lock = new CountDownLatch(1);

    @Test
    public void makesCorrectRequest() throws InterruptedException {
        InterceptorSpy interceptorSpy = new InterceptorSpy();
        RetrofitRestaurantCaller caller = RetrofitRestaurantCallerStubFactory.getClient(interceptorSpy);
        RestaurantListPresenter presenter = new RestaurantListPresenter(caller);
        FakeRestaurantListView fakeView = new FakeRestaurantListView();
        presenter.setView(fakeView);


        presenter.onCreate();
        lock.await(300, TimeUnit.MILLISECONDS);


        assertThat(interceptorSpy.request.method(), equalTo("GET"));
        assertThat(interceptorSpy.request.url().toString(), equalTo("http://example.com/restaurants"));
    }

    @Test
    public void displaysViewWithSuccessfulResponseData() throws InterruptedException {
        SuccessfulRestaurantListInterceptorStub interceptorStub = new SuccessfulRestaurantListInterceptorStub();
        RetrofitRestaurantCaller caller = RetrofitRestaurantCallerStubFactory.getClient(interceptorStub);
        RestaurantListPresenter presenter = new RestaurantListPresenter(caller);
        FakeRestaurantListView fakeView = new FakeRestaurantListView();
        presenter.setView(fakeView);


        presenter.onCreate();
        lock.await(300, TimeUnit.MILLISECONDS);


        List<Restaurant> restaurants = asList(
                new Restaurant(1, "Pintokona", null),
                new Restaurant(2, "Momodori", null)
        );
        assertThat(fakeView.setRowWasCalledWith, equalTo(restaurants));
    }
}
