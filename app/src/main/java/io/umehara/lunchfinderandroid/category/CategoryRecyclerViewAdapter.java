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
    private CategoryListActivity activity;

    CategoryRecyclerViewAdapter(List<Category> categories, CategoryListActivity activity) {
        this.categories = categories;
        this.activity = activity;
    }

    @NonNull
    @Override
    public CategoryRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View categoryListRowLayout = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.category_list_row, parent, false);

        categoryListRowLayout.setOnClickListener(onClickListener(parent));

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

    private View.OnClickListener onClickListener(ViewGroup viewGroup) {
        return view -> {
            RecyclerView recyclerView = (RecyclerView) viewGroup;
            int viewPosition = recyclerView.getChildLayoutPosition(view);
            Category category = categories.get(viewPosition);
            activity.startCategoryDetailActivity(category.getId());
        };
    }
}
