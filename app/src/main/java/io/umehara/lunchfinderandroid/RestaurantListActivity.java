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

public class RestaurantListActivity extends AppCompatActivity {
    private Context applicationContext;
    private ArrayAdapter<Restaurant> adapterRestaurants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);

        applicationContext = getApplicationContext();

        RestaurantRepo restaurantRepo = new RestaurantRepo(applicationContext);

        displayRestaurants(restaurantRepo);
    }

    private void displayRestaurants(RestaurantRepo restaurantRepo) {
        int layoutResource = android.R.layout.simple_list_item_1;

        adapterRestaurants = restaurantRepo.getAll(layoutResource);

        ListView restaurantList = findViewById(R.id.restaurant_list);
        restaurantList.setAdapter(adapterRestaurants);

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
