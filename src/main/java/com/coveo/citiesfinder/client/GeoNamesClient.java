package com.coveo.citiesfinder.client;

import org.geonames.*;
import org.springframework.stereotype.Service;

@Service
public class GeoNamesClient {

    private  static String USER = "shamsazad";

    public ToponymSearchResult getToponymResults(String  name) throws Exception {

        WebService.setUserName(USER);
        ToponymSearchCriteria searchCriteria = new ToponymSearchCriteria();
        searchCriteria.setNameStartsWith(name);
        searchCriteria.setStyle(Style.FULL);
        return WebService.search(searchCriteria);
    }
}
