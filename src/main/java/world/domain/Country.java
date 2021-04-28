package world.domain;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Country {

    @Id
    private String code;
    private String name;
    private String continent;
    private String region;
    private long population;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "capital")
    private City capital;
    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
    private Set<City> cities;

    protected Country() {
    }

    public Country(String name, String continent, long population, String region, String code) {
        this.name = name;
        this.continent = continent;
        this.population = population;
        this.region = region;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public City getCapital() {
        return capital;
    }

    public void setCapital(City capital) {
        this.capital = capital;
    }

    public Set<City> getCities() {
        return cities;
    }

    public void setCities(Set<City> cities) {
        this.cities = cities;
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", continent='" + continent + '\'' +
                ", population=" + population;
    }
}
