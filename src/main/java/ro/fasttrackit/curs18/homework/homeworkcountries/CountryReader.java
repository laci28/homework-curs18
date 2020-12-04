package ro.fasttrackit.curs18.homework.homeworkcountries;

import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class CountryReader {
    private final List<Country> countryList = getCountry();

    public CountryReader() throws Exception {
    }

    public List<Country> getCountry() throws Exception {
        List<Country> result = new ArrayList<>();
        File file = new File("files/countries2.txt");
        Scanner scan = new Scanner(file);
        int id = 1;
        while (scan.hasNextLine()) {
            String[] str = scan.nextLine().split("\\|");
            long population = Long.parseLong(str[2]);
            int area = Integer.parseInt(str[3]);
            String neighbour = null;
            if (str.length == 6 && str[5] != null) {
                neighbour = str[5];
            }
            result.add(new Country(id, str[0], str[1], population, area, str[4], neighbour));
            id++;
        }
        scan.close();
        return result;
    }

    @Override
    public String toString() {
        return "countryList: " + countryList;
    }
}
