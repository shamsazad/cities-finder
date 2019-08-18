package com.coveo.citiesfinder.service;

import com.coveo.citiesfinder.models.Suggestion;
import com.coveo.citiesfinder.service.util.ScoreOnDistance;
import com.coveo.citiesfinder.service.util.ScoreOnPopulation;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ScoringService {

    public List<Suggestion> calculateScore(List<Suggestion> suggestionList) {

        float score;
        int precision = 1;

        for(Suggestion suggestion : suggestionList) {

             float populationScore = ScoreOnPopulation.getScoreOnPopulation(suggestion.getPopulation()) ;
             float distanceScore = ScoreOnDistance.getScoreOnDistance(suggestion.getDistance());
             score = populationScore * distanceScore;
             score = round(score, precision);
             if(score == 0)
                 score =  0.1f;
             suggestion.setScore(score);
        }

        Collections.sort(suggestionList);
        return suggestionList;
    }

    private static float round (float value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (float) Math.round(value * scale) / scale;
    }
}
