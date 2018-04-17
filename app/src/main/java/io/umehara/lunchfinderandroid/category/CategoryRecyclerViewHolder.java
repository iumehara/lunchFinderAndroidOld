package io.umehara.lunchfinderandroid.category;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.TextView;

import io.umehara.lunchfinderandroid.R;

public class CategoryRecyclerViewHolder extends ViewHolder {
    public TextView nameTextView;

    public CategoryRecyclerViewHolder(View categoryListRowLayout) {
        super(categoryListRowLayout);

        this.nameTextView = categoryListRowLayout.findViewById(R.id.name);
    }
}
