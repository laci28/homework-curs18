package ro.fasttrackit.curs18.homework.homeworkcountries;

import java.util.Objects;

public class Country {
    private int id;
    private final String name;
    private final String capital;
    private final long population;
    private final int area;
    private final String continent;
    private final String neighbour;

    public Country(int id, String name, String capital, long population, int area, String continent, String neighbour) {
        this.id = id;
        this.name = name;
        this.capital = capital;
        this.population = population;
        this.area = area;
        this.continent = continent;
        this.neighbour = neighbour;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getCapital() {
        return capital;
    }

    public long getPopulation() {
        return population;
    }

    public int getArea() {
        return area;
    }

    public String getContinent() {
        return continent;
    }

    public String getNeighbour() {
        return neighbour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return id == country.id &&
                population == country.population &&
                area == country.area &&
                Objects.equals(name, country.name) &&
                Objects.equals(capital, country.capital) &&
                Objects.equals(continent, country.continent) &&
                Objects.equals(neighbour, country.neighbour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, capital, population, area, continent, neighbour);
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", capital='" + capital + '\'' +
                ", population=" + population +
                ", area=" + area +
                ", continent='" + continent + '\'' +
                ", neighbour=" + neighbour +
                '}';
    }
}

