package io.umehara.lunchfinderandroid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

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
        int listItemLayout = android.R.layout.simple_list_item_1;

        this.adapterRestaurants = restaurantRepo.getAll(
                new ArrayAdapter<>(applicationContext, listItemLayout, new ArrayList<Restaurant>())
        );

        ListView restaurantList = findViewById(R.id.restaurant_list);
        restaurantList.setAdapter(this.adapterRestaurants);

        restaurantList.setOnItemClickListener(clickListener());
    }

    @NonNull
    private AdapterView.OnItemClickListener clickListener() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Restaurant restaurant = adapterRestaurants.getItem(position);
                if (restaurant == null) return;

                Intent restaurantDetailIntent = new Intent(applicationContext, RestaurantDetailActivity.class);
                restaurantDetailIntent.putExtra("restaurantId", restaurant.getId());

                startActivity(restaurantDetailIntent);
            }
        };
    }
}
