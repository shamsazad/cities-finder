package com.coveo.citiesfinder.service;

import org.junit.Assert;
import org.junit.Test;

public class DistanceCalculatorServiceTest {

    private static double LONGITUDE = 50.0;
    private static double LATITUDE = 42.8;
    private static double CITY_LONGITUDE = 70.0;
    private static double CITY_LATITUDE = 43.8;
    private static double DISTANCE_RETURNED = 1618;

    DistanceCalculatorService distanceCalculatorService = new DistanceCalculatorService();

    @Test
    public void testCalculateDistance() {
        double dist = distanceCalculatorService.distanceCalculator(LONGITUDE, LATITUDE,
                CITY_LONGITUDE, CITY_LATITUDE);
        Assert.assertEquals(DISTANCE_RETURNED, round(dist,2),2);
    }

    private static double round (double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }
}
