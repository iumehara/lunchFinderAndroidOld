package io.umehara.lunchfinderandroid.category;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import io.umehara.lunchfinderandroid.InterceptorSpy;
import okhttp3.Request;

import static io.umehara.lunchfinderandroid.category.RetrofitCategoryStubFactory.getClient;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class CategoryDetailPresenterTest {
    private CountDownLatch lock = new CountDownLatch(1);

    @Test
    public void makesCorrectRequests() throws InterruptedException {
        InterceptorSpy interceptorSpy = new InterceptorSpy();
        CategoryDetailPresenter presenter = new CategoryDetailPresenter(getClient(interceptorSpy));
        FakeCategoryDetailView fakeView = new FakeCategoryDetailView();
        presenter.setView(fakeView);


        presenter.onCreate(5L);
        lock.await(300, TimeUnit.MILLISECONDS);


        Request request = new Request.Builder()
                .method("GET", null)
                .url("http://example.com/categories/5")
                .build();


        assertThat(interceptorSpy.requests.size(), equalTo(2));
        System.out.println("interceptorSpy.requests = " + interceptorSpy.requests);
        System.out.println("request = " + request);
        assertThat(interceptorSpy.requests.get(0).method(), equalTo("GET"));
        assertThat(interceptorSpy.requests.get(0).url().toString(), equalTo("http://example.com/categories/5"));
        assertThat(interceptorSpy.requests.get(1).method(), equalTo("GET"));
        assertThat(interceptorSpy.requests.get(1).url().toString(), equalTo("http://example.com/categories/5/restaurants"));
    }

    @Test
    public void displaysSuccessfulResponse() throws InterruptedException {
        SuccessfulCategoryDetailInterceptorStub interceptorStub = new SuccessfulCategoryDetailInterceptorStub();
        CategoryDetailPresenter presenter = new CategoryDetailPresenter(getClient(interceptorStub));
        FakeCategoryDetailView fakeView = new FakeCategoryDetailView();
        presenter.setView(fakeView);


        presenter.onCreate(5L);
        lock.await(300, TimeUnit.MILLISECONDS);


        assertThat(fakeView.setLabelsWasCalledWith, equalTo(new Category(1, "Spicy")));
        assertThat(fakeView.displayMapWasCalledWith, equalTo(new Category(1, "Spicy")));
    }
}