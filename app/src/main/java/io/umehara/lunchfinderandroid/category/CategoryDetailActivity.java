package io.umehara.lunchfinderandroid.category;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import io.umehara.lunchfinderandroid.R;
import io.umehara.lunchfinderandroid.restaurant.Restaurant;
import io.umehara.lunchfinderandroid.restaurant.RestaurantDetailActivity;
import io.umehara.lunchfinderandroid.restaurant.RestaurantListFragment;

public class CategoryDetailActivity extends AppCompatActivity implements CategoryDetailView {
    private RestaurantListFragment restaurantListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.category_detail_activity);

        setRestaurantListFragment();

        CategoryDetailPresenter presenter = new CategoryDetailPresenter();
        presenter.setView(this);

        long categoryId = getIntent().getIntExtra("categoryId", -1);
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

    public void setRow(List<Restaurant> restaurants) {
        restaurantListFragment.setRestaurants(restaurants, this);
    }

    @Override
    public void startRestaurantDetailActivity(Integer restaurantId) {
        Intent intent = new Intent(getApplicationContext(), RestaurantDetailActivity.class);
        intent.putExtra("restaurantId", restaurantId);

        startActivity(intent);
    }

    private void setRestaurantListFragment() {
        restaurantListFragment = new RestaurantListFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.restaurant_list_fragment, restaurantListFragment);
        fragmentTransaction.commit();
    }
}
