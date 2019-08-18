package com.coveo.citiesfinder.client;

import org.geonames.ToponymSearchResult;
import org.geonames.WebService;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.ArgumentMatchers.any;

@RunWith(PowerMockRunner.class)
public class GeoNamesClientTest {

    private static String QUERY_PARAMETER = "lon";

    private GeoNamesClient geoNamesClient = new GeoNamesClient();

    @Test
    @Ignore
    //Issue mocking static class and method
    /* org.powermock.api.mockito.ClassNotPreparedException:
       [Ljava.lang.Object;@2f162cc0
       The class org.geonames.WebService not prepared for test.
     */
    public void testGetToponymResults() throws Exception {

        PowerMockito.mockStatic(WebService.class);
        BDDMockito.given(WebService.search(any())).willReturn(createToponymResult());

        ToponymSearchResult toponymSearchResult = geoNamesClient.getToponymResults(QUERY_PARAMETER);
        Assert.assertNotNull(toponymSearchResult);
    }

    private ToponymSearchResult createToponymResult() {
        return new ToponymSearchResult();
    }
}
