package imta.utils;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Utility class that helps to retrieve country list
 */
public class CountryList {

    /**
     * This class can't be instanced
     */
    private CountryList() {}

    /**
     * The country list
     */
    private static ArrayList<String> countryList;

    /**
     * Getter on the country list sorted in alphabetical order
     * @return The country list (format: ["France (FR)","Gabon (GA)",...,"Country name (Country code)"]
     */
    public static ArrayList<String> getCountryList() {
        //If the list in not initializes, fill the list
        if(countryList == null) {
            countryList = new ArrayList<>();
            String[] locales = Locale.getISOCountries();
            for (String countryCode : locales) {
                Locale obj = new Locale("", countryCode);
                String country = obj.getDisplayCountry() + " " + "(" + obj.getCountry() + ")";
                countryList.add(country);
            }
            java.util.Collections.sort(countryList);
        }
        return countryList;
    }

}
