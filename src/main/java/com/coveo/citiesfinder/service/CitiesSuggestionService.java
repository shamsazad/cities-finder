package com.coveo.citiesfinder.service;

import com.coveo.citiesfinder.client.GeoNamesClient;
import com.coveo.citiesfinder.models.Suggestion;
import com.coveo.citiesfinder.models.response.Suggestions;
import org.geonames.Toponym;
import org.geonames.ToponymSearchResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *  This class process the response from GeoName client and prepare the response for client.
 */

@Service
public class CitiesSuggestionService {
    
    private GeoNamesClient geoNamesClient;
    private DistanceCalculatorService distanceCalculatorService;
    private ScoringService scoringService;

    private static int POPULATION = 10000;

    public CitiesSuggestionService(GeoNamesClient geoNamesClient,
                                   DistanceCalculatorService distanceCalculatorService,
                                   ScoringService scoringService) {
        this.geoNamesClient = geoNamesClient;
        this.distanceCalculatorService = distanceCalculatorService;
        this.scoringService = scoringService;
    }

    /**
     *
     * @param name
     * @param longitude
     * @param latitude
     * @return A list of suggested cities based on score.
     * @throws Exception
     */
    public Suggestions suggestCities(String name, Double longitude, Double latitude) throws Exception {

        List<Suggestion> suggestionList = new ArrayList<>();

        boolean isLongLatPresent = isLongLatPresent(longitude, latitude);
        double longitudeValue  = getLongitude(longitude);
        double latitudeValue = getLatitude(latitude);
        double dist = 0;

        ToponymSearchResult searchResult = geoNamesClient.getToponymResults(name);

        if(searchResult.getToponyms().size() == 0)
            return Suggestions.builder().suggestions(suggestionList).build();

        for (Toponym toponym : searchResult.getToponyms()) {

            if(toponym.getPopulation() != null && toponym.getPopulation() > POPULATION) {

                if(isLongLatPresent)
                    dist = distanceCalculatorService.distanceCalculator(longitudeValue, latitudeValue,
                            toponym.getLongitude(), toponym.getLatitude());

                Suggestion suggestion = Suggestion.builder()
                        .name(toponym.getName()+","+toponym.getAdminName1() +","+
                                toponym.getAdminName2()+","+toponym.getCountryName())
                        .latitude(toponym.getLatitude())
                        .longitude(toponym.getLongitude())
                        .distance(dist)
                        .population(toponym.getPopulation())
                        .build();

                suggestionList.add(suggestion);
            }
        }
        return getScoredSuggestions(suggestionList);
    }

    private Suggestions getScoredSuggestions(List<Suggestion> suggestionList) {

        if(suggestionList.size() ==  1) {
            suggestionList.get(0).setScore(1.0f);
        } else  {
                suggestionList = scoringService.calculateScore(suggestionList);
        }

        return Suggestions.builder().suggestions(suggestionList).build();
    }

    private double getLatitude(Double latitude) {

        return (latitude == null) ? 0 : latitude.doubleValue();
    }

    private double getLongitude(Double longitude) {

        return (longitude == null) ? 0 : longitude.doubleValue();
    }

    private boolean isLongLatPresent(Double longitude, Double latitude) {

        return longitude != null || latitude != null;
    }
}
