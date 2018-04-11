package io.umehara.lunchfinderandroid;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

class Restaurant {
    private Integer id;
    private String name;

    public Restaurant(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Restaurant(JSONObject jsonObject) throws JSONException {
        this.id = (Integer) jsonObject.get("id");
        this.name = (String) jsonObject.get("name");
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant that = (Restaurant) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
//        return "Restaurant{" +
//                "name='" + name + '\'' +
//                '}';
        return name;
    }
}
