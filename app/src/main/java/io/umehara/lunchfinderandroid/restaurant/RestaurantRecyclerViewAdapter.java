package io.umehara.lunchfinderandroid.restaurant;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import io.umehara.lunchfinderandroid.R;

public class RestaurantRecyclerViewAdapter extends RecyclerView.Adapter<RestaurantRecyclerViewHolder> {
    private List<Restaurant> restaurants;
    private RestaurantListView activity;

    RestaurantRecyclerViewAdapter(List<Restaurant> restaurants, RestaurantListView activity) {
        this.restaurants = restaurants;
        this.activity = activity;
    }

    @NonNull
    @Override
    public RestaurantRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View restaurantListRowLayout = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.restaurant_list_row, parent, false);

        restaurantListRowLayout.setOnClickListener(onClickListener(parent));

        return new RestaurantRecyclerViewHolder(restaurantListRowLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantRecyclerViewHolder holder, int position) {
        holder.nameTextView.setText(restaurants.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    private View.OnClickListener onClickListener(ViewGroup viewGroup) {
        return view -> {
            RecyclerView recyclerView = (RecyclerView) viewGroup;
            int viewPosition = recyclerView.getChildLayoutPosition(view);
            Restaurant restaurant = restaurants.get(viewPosition);

            activity.startRestaurantDetailActivity(restaurant.getId());
        };
    }
}
