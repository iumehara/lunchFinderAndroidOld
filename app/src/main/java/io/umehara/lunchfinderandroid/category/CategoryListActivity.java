package io.umehara.lunchfinderandroid.category;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import io.umehara.lunchfinderandroid.R;

public class CategoryListActivity extends AppCompatActivity implements CategoryListView {
    private RecyclerView categoryRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);

        CategoryListPresenter presenter = new CategoryListPresenter();

        categoryRecyclerView = findViewById(R.id.category_list);
        categoryRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        presenter.setView(this);
        presenter.onCreate();
    }

    public void setOnAdapter(List<Category> categories) {
        CategoryRecyclerViewAdapter adapter = new CategoryRecyclerViewAdapter(categories);

        categoryRecyclerView.setAdapter(adapter);
    }
}
