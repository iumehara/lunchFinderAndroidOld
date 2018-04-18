package io.umehara.lunchfinderandroid.category;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import io.umehara.lunchfinderandroid.InterceptorSpy;

import static io.umehara.lunchfinderandroid.category.RetrofitCategoryStubFactory.getClient;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class CategoryDetailPresenterTest {
    private CountDownLatch lock = new CountDownLatch(1);

    @Test
    public void makesCorrectRequest() throws InterruptedException {
        InterceptorSpy interceptorSpy = new InterceptorSpy();
        CategoryDetailPresenter presenter = new CategoryDetailPresenter(getClient(interceptorSpy));
        FakeCategoryDetailView fakeView = new FakeCategoryDetailView();
        presenter.setView(fakeView);


        presenter.onCreate(5L);
        lock.await(300, TimeUnit.MILLISECONDS);


        assertThat(interceptorSpy.request.method(), equalTo("GET"));
        assertThat(interceptorSpy.request.url().toString(), equalTo("http://example.com/categories/5"));
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