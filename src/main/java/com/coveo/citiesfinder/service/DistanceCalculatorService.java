package com.coveo.citiesfinder.service;

import org.springframework.stereotype.Service;

@Service
public class DistanceCalculatorService {

    public double distanceCalculator(double userLongitude, double userLatitude,
                                     double cityLongitude, double cityLatitude) {

        double theta = userLongitude - cityLongitude;
        double dist = Math.sin(deg2rad(userLatitude)) * Math.sin(deg2rad(cityLatitude)) + Math.cos(deg2rad(userLatitude)) *
                Math.cos(deg2rad(cityLatitude)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;

        return dist * 1.609344;
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }
}
