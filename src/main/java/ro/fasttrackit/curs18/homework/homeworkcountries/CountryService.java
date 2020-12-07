package ro.fasttrackit.curs18.homework.homeworkcountries;

import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/countries")
public class CountryService {
    CountryReader countryReader = new CountryReader();
    private final List<Country> countryList = countryReader.getCountry();

    public CountryService() throws Exception {
    }

    @GetMapping
    public List<Country> listAllCountries() throws Exception {
        return countryList;
    }

    @GetMapping("/names")
    public List<String> getAllCountries() throws Exception {
        return countryList.stream()
                .map(Country::getName)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}/capital")
    public String getCapitalOfACountry(@PathVariable int id) {
        return countryList.stream()
                .filter(country -> country.getId() == id)
                .map(Country::getCapital)
                .toString();
    }

    @GetMapping(value = "/{id}/population")
    public Long getPopulationOfACountry(@PathVariable int id) {
        return countryList.stream()
                .filter(country -> country.getId() == id)
                .mapToLong(Country::getPopulation)
                .sum();
    }

    @GetMapping(value = "/{continentName}/countries")
    public List<Country> getCountriesInContinent(@PathVariable String continentName) {
        return countryList.stream()
                .filter(country -> country.getContinent().toUpperCase()
                        .equals(continentName.toUpperCase()))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}/neighbours")
    public List<String> getCountryNeighbours(@PathVariable int id) {
        return Arrays.stream(countryList.stream()
                .filter(country -> country.getId() == id)
                .map(Country::getNeighbour)
                .collect(Collectors.toList())
                .toString().split("~"))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{continentName}/countries", params = "minPopulation")
    public List<Country> getCountryByContinentAndMinimumPopulation(@PathVariable String continentName,
                                                                   @RequestParam("minPopulation") int min) {
        return countryList.stream()
                .filter(country -> country.getContinent().toUpperCase()
                        .equals(continentName.toUpperCase()))
                .filter(country -> country.getPopulation() > min)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/population")
    public Map<String, Long> countryToPopulation() {
        return countryList.stream()
                .collect(Collectors.toMap(Country::getName, Country::getPopulation));
    }

    @GetMapping(value = "/continents/countries")
    public Map<String, List<Country>> continentToListOfCountries() {
        return countryList.stream()
                .map(Country::getContinent)
                .collect(Collectors.toMap(Country::getContinent.toString(), Collectors.toList()));
    }

    @GetMapping(params = ("includeNeighbour"))
    public List<Country> includedNeighbour(@RequestParam("includeNeighbour") String neighbour1,
                                           @RequestParam("excludeNeighbour") String neighbour2){
        return countryList.stream()
                .filter(country -> country.getNeighbour()
                        .matches(neighbour1.toUpperCase()) && !country.getNeighbour()
                        .matches(neighbour2.toUpperCase()))
                .collect(Collectors.toList());
    }
}
