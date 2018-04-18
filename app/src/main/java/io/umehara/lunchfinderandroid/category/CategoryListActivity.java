package io.umehara.lunchfinderandroid.category;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import io.umehara.lunchfinderandroid.R;

public class CategoryListActivity extends AppCompatActivity implements CategoryListView {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_list_activity);

        CategoryListPresenter presenter = new CategoryListPresenter();

        recyclerView = findViewById(R.id.category_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        presenter.setView(this);
        presenter.onCreate();
    }

    public void setOnAdapter(List<Category> categories) {
        CategoryRecyclerViewAdapter adapter = new CategoryRecyclerViewAdapter(categories, this);
        recyclerView.setAdapter(adapter);
    }

    public void startCategoryDetailActivity(Integer categoryId) {
        Intent intent = new Intent(getApplicationContext(), CategoryDetailActivity.class);
        intent.putExtra("categoryId", categoryId);

        startActivity(intent);
    }
}
