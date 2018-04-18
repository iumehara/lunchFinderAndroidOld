package io.umehara.lunchfinderandroid.category;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import io.umehara.lunchfinderandroid.R;

public class CategoryDetailActivity extends AppCompatActivity implements CategoryDetailView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_detail_activity);

        CategoryDetailPresenter presenter = new CategoryDetailPresenter();

        presenter.setView(this);

        Intent intent = getIntent();
        long categoryId = intent.getIntExtra("categoryId", -1);
        presenter.onCreate(categoryId);
    }


    @Override
    public void setLabels(Category category) {
        View categoryDetailActivityView = findViewById(R.id.category_detail_activity);
        TextView nameView = categoryDetailActivityView.findViewById(R.id.name);
        nameView.setText(category.getName());
    }

    @Override
    public void displayMap(Category category) {

    }
}
