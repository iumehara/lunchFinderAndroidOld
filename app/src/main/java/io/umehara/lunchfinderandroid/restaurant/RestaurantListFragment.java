package io.umehara.lunchfinderandroid.restaurant;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import io.umehara.lunchfinderandroid.FragmentListView;
import io.umehara.lunchfinderandroid.R;

public class RestaurantListFragment extends Fragment {
    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        return inflater.inflate(R.layout.restaurant_list_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FragmentActivity activity = getActivity();
        if (activity == null) {return;}

        recyclerView = getActivity().findViewById(R.id.restaurant_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    public void setRestaurants(List<Restaurant> restaurants, FragmentListView view) {
        RestaurantRecyclerViewAdapter adapter = new RestaurantRecyclerViewAdapter(restaurants, view);
        recyclerView.setAdapter(adapter);
    }
}
