package com.coveo.citiesfinder.service;

import com.coveo.citiesfinder.client.GeoNamesClient;
import com.coveo.citiesfinder.models.Suggestion;
import com.coveo.citiesfinder.models.response.Suggestions;
import org.geonames.Toponym;
import org.geonames.ToponymSearchResult;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class CitiesSuggestionServiceTest {

    @Mock
    private GeoNamesClient geoNamesClient;

    @Mock
    private DistanceCalculatorService distanceCalculatorService;

    @Mock
    private ScoringService scoringService;

    private CitiesSuggestionService citiesSuggestionService;

    private static String NAME = "test";
    private static double LONGITUDE = 50.0;
    private static double LATITUDE = 42.8;
    private static double CITY_LONGITUDE = 50.0;
    private static double CITY_LATITUDE = 42.8;
    private static double DISTANCE_RETURNED = 20.0;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        citiesSuggestionService = new CitiesSuggestionService(geoNamesClient,
                distanceCalculatorService, scoringService);
        Mockito.when(distanceCalculatorService.distanceCalculator(LONGITUDE, LATITUDE,
                CITY_LONGITUDE, CITY_LATITUDE)).thenReturn(DISTANCE_RETURNED);
    }

    @Test
    public void testSuggestCitiesWhenLongLatNotProvided() throws Exception {

        Mockito.when(geoNamesClient.getToponymResults(any())).thenReturn(createToponymSearchResult());
        Suggestions suggestions = citiesSuggestionService.suggestCities(NAME,null,null);
        Assert.assertEquals(1, suggestions.getSuggestions().size());
        Assert.assertEquals(123456, suggestions.getSuggestions().get(0).getPopulation());
        Assert.assertEquals(0,suggestions.getSuggestions().get(0).getDistance(),0.0);
    }

    @Test
    public void testSuggestCitiesWhenLongLatIsProvided() throws Exception {

        Mockito.when(geoNamesClient.getToponymResults(any())).thenReturn(createToponymSearchResult());
        Suggestions suggestions = citiesSuggestionService.suggestCities(NAME, LONGITUDE, LATITUDE);
        Assert.assertEquals(1, suggestions.getSuggestions().size());
        Assert.assertEquals(20,suggestions.getSuggestions().get(0).getDistance(),0.0);

    }

    @Test
    public void testSuggestCities() throws Exception {

        ToponymSearchResult toponymSearchResult = createToponymSearchResult();
        toponymSearchResult.getToponyms().get(0).setPopulation(Long.valueOf(20000));

        Mockito.when(geoNamesClient.getToponymResults(any())).thenReturn(toponymSearchResult);
        Mockito.when(scoringService.calculateScore(any())).thenReturn(createSuggestionList());
        Suggestions suggestions = citiesSuggestionService.suggestCities(NAME, LONGITUDE, LATITUDE);

        Assert.assertEquals(2, suggestions.getSuggestions().size());
    }

    @Test
    public void testWhenNoSuggestionIsProvided() throws Exception {
        Mockito.when(geoNamesClient.getToponymResults(any())).thenReturn(new ToponymSearchResult());
        Suggestions suggestions = citiesSuggestionService.suggestCities(NAME, LONGITUDE, LATITUDE);
        Assert.assertEquals(0, suggestions.getSuggestions().size());
    }


    private List<Suggestion> createSuggestionList() {

        List<Suggestion> suggestionList = new ArrayList<>();

        Suggestion suggestion = Suggestion.builder()
                .name("testToponym,testAdminName1,testAdminName2,NA")
                .latitude(LATITUDE)
                .longitude(LONGITUDE)
                .score(0.0f)
                .build();

        Suggestion suggestion1 = Suggestion.builder()
                .name("testToponym1,testAdminName1,testAdminName2,NA")
                .latitude(CITY_LATITUDE)
                .longitude(CITY_LONGITUDE)
                .score(0.9f)
                .build();

        suggestionList.add(suggestion);
        suggestionList.add(suggestion1);

        return suggestionList;
    }

    private ToponymSearchResult createToponymSearchResult() {

        ToponymSearchResult toponymSearchResult = new ToponymSearchResult();
        toponymSearchResult.setToponyms(createToponym());
        return toponymSearchResult;
    }

    private List<Toponym> createToponym() {

        List<Toponym> toponymList = new ArrayList<>();

        Toponym toponym = new Toponym();
        toponym.setLatitude(LATITUDE);
        toponym.setLongitude(LONGITUDE);
        toponym.setName("testToponym");
        toponym.setAdminName1("testAdminName1");
        toponym.setAdminName2("testAdminName2");
        toponym.setCountryCode("NA");
        toponym.setPopulation(Long.valueOf(1234));

        Toponym toponym1 = new Toponym();
        toponym1.setLatitude(CITY_LATITUDE);
        toponym1.setLongitude(CITY_LONGITUDE);
        toponym1.setName("testToponym1");
        toponym1.setAdminName1("testAdminName1");
        toponym1.setAdminName2("testAdminName2");
        toponym1.setCountryCode("NA");
        toponym1.setPopulation(Long.valueOf(123456));

        toponymList.add(toponym);
        toponymList.add(toponym1);

        return toponymList;
    }
}
