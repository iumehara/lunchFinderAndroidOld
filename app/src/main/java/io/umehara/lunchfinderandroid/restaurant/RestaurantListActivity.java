package io.umehara.lunchfinderandroid.restaurant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import io.umehara.lunchfinderandroid.R;

public class RestaurantListActivity extends AppCompatActivity implements RestaurantListView {
    private RestaurantListFragment listFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.restaurant_list_activity);

        setListFragment();

        RestaurantListPresenter presenter = new RestaurantListPresenter();
        presenter.setView(this);
        presenter.onCreate();
    }

    public void setRow(List<Restaurant> restaurants) {
        listFragment.setRestaurants(restaurants, this);
    }

    public void startRestaurantDetailActivity(Integer restaurantId) {
        Intent intent = new Intent(getApplicationContext(), RestaurantDetailActivity.class);
        intent.putExtra("restaurantId", restaurantId);

        startActivity(intent);
    }

    private void setListFragment() {
        listFragment = new RestaurantListFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.restaurant_list_fragment, listFragment);
        fragmentTransaction.commit();
    }
}
