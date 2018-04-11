package io.umehara.lunchfinderandroid;

import android.content.Context;
import android.widget.Adapter;
import android.widget.ArrayAdapter;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RestaurantRepo {
    private Context context;
    private String baseURL = "http://lunch-finder-api.cfapps.io/";

    public RestaurantRepo(Context context) {
        this.context = context;
    }

    public ArrayAdapter<Restaurant> getAll(int layoutResource) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = baseURL + "restaurants";
        final ArrayAdapter<Restaurant> adapterRestaurants = new ArrayAdapter<>(
                context,
                layoutResource,
                new ArrayList<Restaurant>()
        );

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                url,
                jsonArrayResponseListener(adapterRestaurants),
                errorResponseListener()
        );

        requestQueue.add(jsonArrayRequest);

        return adapterRestaurants;
    }

    private Response.Listener<JSONArray> jsonArrayResponseListener(final ArrayAdapter<Restaurant> adapterRestaurants) {
        return new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                List<Restaurant> restaurants = new ArrayList<>();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = (JSONObject) response.get(i);
                        Restaurant restaurant = new Restaurant(jsonObject);
                        restaurants.add(restaurant);
                    } catch (JSONException e) {
                        System.out.println("e = " + e);
                    }
                }


                adapterRestaurants.addAll(restaurants);
            }
        };
    }

    private Response.ErrorListener errorResponseListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("error = " + error);
            }
        };
    }
}
