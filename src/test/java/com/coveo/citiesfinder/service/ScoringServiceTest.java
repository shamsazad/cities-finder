package com.coveo.citiesfinder.service;

import com.coveo.citiesfinder.models.Suggestion;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ScoringServiceTest {

    private static double LONGITUDE = 50.0;
    private static double LATITUDE = 42.8;
    private static double CITY_LONGITUDE = 50.0;
    private static double CITY_LATITUDE = 42.8;

    private ScoringService scoringService;

    @Before
    public void setUp() {

        scoringService = new ScoringService();
    }

    @Test
    public void testCalculateScore() {

        List<Suggestion> suggestions = createSuggestionList();
        suggestions = scoringService.calculateScore(suggestions);

        Assert.assertEquals(0.6,suggestions.get(0).getScore(),.1);
        Assert.assertEquals(0.5,suggestions.get(1).getScore(),.1);

    }

    @Test
    public void testCalculateScoreWithHighPopulation()  {
        List<Suggestion> suggestions = createSuggestionList();
        suggestions.get(0).setPopulation(180000);
        suggestions = scoringService.calculateScore(suggestions);

        Assert.assertEquals(0.9,suggestions.get(0).getScore(),.1);
        Assert.assertEquals(0.5,suggestions.get(1).getScore(),.1);
    }

    @Test
    public void testCalculateScoreWithLongDistance() {

        List<Suggestion> suggestions = createSuggestionList();
        suggestions.get(1).setDistance(15000);

        suggestions = scoringService.calculateScore(suggestions);

        Assert.assertEquals(0.6,suggestions.get(0).getScore(),.1);
        Assert.assertEquals(0.1,suggestions.get(1).getScore(),.1);
    }

    private List<Suggestion> createSuggestionList() {


        List<Suggestion> suggestionList = new ArrayList<>();

        Suggestion suggestion = Suggestion.builder()
                .name("testToponym,testAdminName1,testAdminName2,NA")
                .latitude(LATITUDE)
                .longitude(LONGITUDE)
                .distance(500)
                .population(69000)
                .score(0.0f)
                .build();

        Suggestion suggestion1 = Suggestion.builder()
                .name("testToponym1,testAdminName1,testAdminName2,NA")
                .latitude(CITY_LATITUDE)
                .longitude(CITY_LONGITUDE)
                .distance(900)
                .population(50000)
                .score(0.0f)
                .build();

        suggestionList.add(suggestion);
        suggestionList.add(suggestion1);

        return suggestionList;
    }
}
