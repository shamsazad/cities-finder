package com.coveo.citiesfinder.client;

import org.geonames.*;
import org.springframework.stereotype.Service;

/**
 * The service calls the geoNames client and find the places that corresponds to the given query
 */
@Service
public class GeoNamesClient {

    private  static String USER = "shamsazad";

    /**
     *
     * @param name
     * @return ToponymSearchResults which is a list of Toponym, which basically hold the details about a place.
     * @throws Exception
     */
    public ToponymSearchResult getToponymResults(String  name) throws Exception {

        WebService.setUserName(USER);
        ToponymSearchCriteria searchCriteria = new ToponymSearchCriteria();
        searchCriteria.setNameStartsWith(name);
        searchCriteria.setStyle(Style.FULL);
        return WebService.search(searchCriteria);
    }
}
