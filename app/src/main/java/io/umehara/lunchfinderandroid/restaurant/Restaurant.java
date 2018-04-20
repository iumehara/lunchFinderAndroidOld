package io.umehara.lunchfinderandroid.restaurant;

import java.util.Objects;

public class Restaurant {
    private Integer id;
    private String name;
    private String nameJp;

    public Restaurant(Integer id, String name, String nameJp) {
        this.id = id;
        this.name = name;
        this.nameJp = nameJp;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNameJp() {
        return nameJp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant that = (Restaurant) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(nameJp, that.nameJp);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, nameJp);
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nameJp='" + nameJp + '\'' +
                '}';
    }
}
