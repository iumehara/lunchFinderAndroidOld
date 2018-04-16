package io.umehara.lunchfinderandroid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class RestaurantListActivity extends AppCompatActivity implements RestaurantListView {
    private Context applicationContext;
    private List<Restaurant> restaurants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);

        applicationContext = getApplicationContext();

        RestaurantListPresenter presenter = new RestaurantListPresenter(this);

        presenter.onCreate();
    }

    public void setRow(List<Restaurant> restaurants) {
        this.restaurants = restaurants;

        ArrayAdapter<String> adapterRestaurants = new ArrayAdapter<>(
                applicationContext,
                android.R.layout.simple_list_item_1,
                restaurants.stream().map(Restaurant::getName).collect(toList())
        );

        ListView restaurantList = findViewById(R.id.restaurant_list);
        restaurantList.setAdapter(adapterRestaurants);
        restaurantList.setOnItemClickListener(clickListener());
    }

    @NonNull
    private AdapterView.OnItemClickListener clickListener() {
        return (parent, view, position, id) -> {
            Restaurant restaurant = restaurants.get(position);
            if (restaurant == null) return;

            Intent restaurantDetailIntent = new Intent(applicationContext, RestaurantDetailActivity.class);
            restaurantDetailIntent.putExtra("restaurantId", restaurant.getId());

            startActivity(restaurantDetailIntent);
        };
    }
}
