package com.coveo.citiesfinder.models.response;

import com.coveo.citiesfinder.models.Suggestion;
import com.google.common.testing.EqualsTester;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SuggestionsTest {


    @Test
    public void testEqualsAndHashCode(){

        Suggestions suggestions = createSuggestions();
        Suggestions suggestions1 = createSuggestions();
        Suggestions suggestions2 = createSuggestions();

        suggestions2.getSuggestions().get(0).setScore(1.0f);

        new EqualsTester()
                .addEqualityGroup(suggestions, suggestions1)
                .addEqualityGroup(suggestions2)
                .testEquals();

        Assert.assertEquals(suggestions.hashCode(),suggestions1.hashCode());
        Assert.assertNotEquals(suggestions1.hashCode(),suggestions2.hashCode());
    }


    private Suggestions createSuggestions(){

        List<Suggestion> suggestionList = new ArrayList<>();

        Suggestion suggestion = Suggestion.builder().name("test1")
                .latitude(80.0)
                .longitude(120.0)
                .score(0.0f)
                .build();

        Suggestion suggestion1 = Suggestion.builder().name("test1")
                .latitude(80.0)
                .longitude(120.0)
                .score(0.0f)
                .build();

        suggestionList.add(suggestion);
        suggestionList.add(suggestion1);

        return Suggestions.builder().suggestions(suggestionList).build();
    }
}
