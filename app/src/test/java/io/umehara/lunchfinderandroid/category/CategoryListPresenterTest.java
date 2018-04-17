package io.umehara.lunchfinderandroid.category;

import org.junit.Test;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import io.umehara.lunchfinderandroid.InterceptorSpy;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class CategoryListPresenterTest {
    private CountDownLatch lock = new CountDownLatch(1);

    @Test
    public void makesCorrectRequest() throws InterruptedException {
        InterceptorSpy interceptorSpy = new InterceptorSpy();
        RetrofitCategoryCaller caller = RetrofitCategoryStubFactory.getClient(interceptorSpy);
        CategoryListPresenter presenter = new CategoryListPresenter(caller);
        FakeCategoryListView fakeView = new FakeCategoryListView();
        presenter.setView(fakeView);


        presenter.onCreate();
        lock.await(300, TimeUnit.MILLISECONDS);


        assertThat(interceptorSpy.request.method(), equalTo("GET"));
        assertThat(interceptorSpy.request.url().toString(), equalTo("http://example.com/categories"));
    }

    @Test
    public void displaysViewwithSuccessfulResponseData() throws InterruptedException {
        SuccessfulCategoryListInterceptorStub interceptorStub = new SuccessfulCategoryListInterceptorStub();
        RetrofitCategoryCaller caller = RetrofitCategoryStubFactory.getClient(interceptorStub);
        CategoryListPresenter presenter = new CategoryListPresenter(caller);
        FakeCategoryListView fakeView = new FakeCategoryListView();
        presenter.setView(fakeView);


        presenter.onCreate();
        lock.await(300, TimeUnit.MILLISECONDS);


        List<Category> categories = asList(
                new Category(1, "Spicy"),
                new Category(2, "Sweet")
        );
        assertThat(fakeView.setOnAdapterWasCalledWith, equalTo(categories));
    }
}
