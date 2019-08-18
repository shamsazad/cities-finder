package com.coveo.citiesfinder.models;

import com.google.common.testing.EqualsTester;
import org.junit.Assert;
import org.junit.Test;

public class SuggestionTest {

    @Test
    public void testEqualsAndHashCode() {

        Suggestion suggestion = createSuggestion();
        suggestion.setScore(1.0f);

        Suggestion suggestion2 = createSuggestion();
        suggestion2.setScore(1.0f);

        Suggestion suggestion3 = createSuggestion();
        Suggestion suggestion4 = createSuggestion();

        new EqualsTester()
                .addEqualityGroup(suggestion, suggestion2)
                .addEqualityGroup(suggestion3, suggestion4)
                .testEquals();

        Assert.assertEquals(suggestion.hashCode(),suggestion2.hashCode());
        Assert.assertNotEquals(suggestion2.hashCode(), suggestion3.hashCode());
    }

    @Test
    public void testCompareTo() {

        Suggestion suggestion = createSuggestion();
        Suggestion  suggestion1 = createSuggestion();
        Assert.assertEquals(0,suggestion.compareTo(suggestion1));
        suggestion1.setScore(0.5f);
        Assert.assertEquals(1,suggestion.compareTo(suggestion1));
        suggestion.setScore(0.7f);
        Assert.assertEquals(-1,suggestion.compareTo(suggestion1));

    }

    private Suggestion createSuggestion(){

        return Suggestion.builder().name("test1")
                .latitude(80.0)
                .longitude(120.0)
                .score(0.0f)
                .build();
    }
}
