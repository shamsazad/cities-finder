package com.coveo.citiesfinder.api;

import com.coveo.citiesfinder.models.response.Suggestions;
import com.coveo.citiesfinder.service.CitiesSuggestionService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.*;

@RestController
@RequestMapping("/suggestions")
@Validated
public class CitiesSuggestionController {

    private CitiesSuggestionService  citiesSuggestionService;

    public CitiesSuggestionController(CitiesSuggestionService citiesSuggestionService) {
        this.citiesSuggestionService = citiesSuggestionService;
    }

    @RequestMapping(method= RequestMethod.GET)
    public Suggestions getSuggestedCities(@RequestParam("q") String city,
                                          @Valid @RequestParam(value = "longitude", required = false) @Min(-180) @Max(180) Double longitude,
                                          @Valid @RequestParam(value = "latitude", required = false) @Min(-90) @Max(90) Double latitude) throws Exception {
        return citiesSuggestionService.suggestCities(city, longitude, latitude);
    }
}
