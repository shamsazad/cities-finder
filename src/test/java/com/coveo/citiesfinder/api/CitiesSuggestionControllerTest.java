package com.coveo.citiesfinder.api;

import com.coveo.citiesfinder.CitiesFinderApplicationTests;
import com.coveo.citiesfinder.models.Suggestion;
import com.coveo.citiesfinder.models.response.Suggestions;
import com.coveo.citiesfinder.service.CitiesSuggestionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CitiesFinderApplicationTests.class})
public class CitiesSuggestionControllerTest {

    @Mock
    private CitiesSuggestionService citiesSuggestionService;

    @Mock
    private CitiesSuggestionController citiesSuggestionController;

    private Suggestions expectedResponse;

    @Before
    public void setup() {
        citiesSuggestionController = new CitiesSuggestionController(citiesSuggestionService);
        expectedResponse = createResponse();
    }

    @Test
    public void testGetSuggestedCities() throws Exception {
        Mockito.when(citiesSuggestionController.getSuggestedCities(any(),any(),any())).thenReturn(expectedResponse);
        Suggestions actualResponse = citiesSuggestionController.getSuggestedCities(null,null,null);
        verify(citiesSuggestionService, times(1)).suggestCities(null,null, null);
        assertEquals(expectedResponse, actualResponse);
    }

    private Suggestions createResponse() {

        List<Suggestion> suggestionsList  = new ArrayList<>();
        Suggestion suggestion = Suggestion.builder().build();
        suggestionsList.add(suggestion);
        return Suggestions.builder().suggestions(suggestionsList).build();
    }
}
