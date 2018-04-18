package io.umehara.lunchfinderandroid.category;

import java.util.List;

class FakeCategoryListView implements CategoryListView {
    public List<Category> setOnAdapterWasCalledWith;
    @Override
    public void setOnAdapter(List<Category> categories) {
        setOnAdapterWasCalledWith = categories;
    }
}
