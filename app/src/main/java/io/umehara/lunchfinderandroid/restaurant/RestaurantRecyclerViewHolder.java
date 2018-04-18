package io.umehara.lunchfinderandroid.restaurant;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.TextView;

import io.umehara.lunchfinderandroid.R;

public class RestaurantRecyclerViewHolder extends ViewHolder {
    public TextView nameTextView;

    public RestaurantRecyclerViewHolder(View categoryListRowLayout) {
        super(categoryListRowLayout);

        this.nameTextView = categoryListRowLayout.findViewById(R.id.name);
    }
}
