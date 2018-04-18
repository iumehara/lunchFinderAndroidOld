package io.umehara.lunchfinderandroid.category;

class FakeCategoryDetailView implements CategoryDetailView {
    public Category setLabelsWasCalledWith;
    @Override
    public void setLabels(Category category) {
        setLabelsWasCalledWith = category;
    }

    public Category displayMapWasCalledWith;
    @Override
    public void displayMap(Category category) {
        displayMapWasCalledWith = category;
    }
}
