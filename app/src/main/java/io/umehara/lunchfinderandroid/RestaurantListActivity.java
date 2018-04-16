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
import java.util.concurrent.CompletableFuture;

public class RestaurantListActivity extends AppCompatActivity {
    private Context applicationContext;
    private ArrayAdapter<Restaurant> adapterRestaurants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);

        applicationContext = getApplicationContext();

        RetrofitRestaurantRepo restaurantRepo = new RetrofitRestaurantRepo();

        displayRestaurants(restaurantRepo);
    }

    private void displayRestaurants(RestaurantRepo restaurantRepo) {
        CompletableFuture<List<Restaurant>> futureRestaurants = restaurantRepo.getAll();

        futureRestaurants
                .thenAccept(this::setRow);
    }

    private void setRow(List<Restaurant> restaurants) {
        int listItemLayout = android.R.layout.simple_list_item_1;

        adapterRestaurants = new ArrayAdapter<>(
                applicationContext,
                listItemLayout,
                restaurants);

        ListView restaurantList = findViewById(R.id.restaurant_list);
        restaurantList.setAdapter(adapterRestaurants);
        restaurantList.setOnItemClickListener(clickListener());
    }

    @NonNull
    private AdapterView.OnItemClickListener clickListener() {
        return (parent, view, position, id) -> {
            Restaurant restaurant = adapterRestaurants.getItem(position);
            if (restaurant == null) return;

            Intent restaurantDetailIntent = new Intent(applicationContext, RestaurantDetailActivity.class);
            restaurantDetailIntent.putExtra("restaurantId", restaurant.getId());

            startActivity(restaurantDetailIntent);
        };
    }
}