package io.umehara.lunchfinderandroid.category;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import io.umehara.lunchfinderandroid.R;

public class CategoryRecyclerViewAdapter extends RecyclerView.Adapter<CategoryRecyclerViewHolder> {
    private List<Category> categories;

    public CategoryRecyclerViewAdapter(List<Category> categories) {
        this.categories = categories;
    }

    @NonNull
    @Override
    public CategoryRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View categoryListRowLayout = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.category_list_row, parent, false);

        return new CategoryRecyclerViewHolder(categoryListRowLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryRecyclerViewHolder holder, int position) {
        holder.nameTextView.setText(categories.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
}
