package com.coveo.citiesfinder.service.util;

public final class ScoreOnPopulation {

    private static long NINETY_THOUSANDS = 90000;
    private static long EIGHTY_THOUSAND = 80000;
    private static long SEVENTY_THOUSAND = 70000;
    private static long SIXTY_THOUSAND = 60000;
    private static long FIFTY_THOUSAND = 50000;
    private static long FORTY_THOUSAND = 40000;
    private static long THIRTY_THOUSAND = 30000;
    private static long TWENTY_THOUSAND = 20000;

    /**
     *
     * @param population
     * @return a score based on population
     */
    public static float getScoreOnPopulation(long population){

        if(population < TWENTY_THOUSAND)
            return 0.1f;
        else if(population < THIRTY_THOUSAND )
            return 0.2f;
        else if(population < FORTY_THOUSAND)
            return 0.3f;
        else if(population < FIFTY_THOUSAND)
            return 0.4f;
        else if(population < SIXTY_THOUSAND)
            return 0.5f;
        else if(population < SEVENTY_THOUSAND)
            return 0.6f;
        else if(population < EIGHTY_THOUSAND)
            return 0.7f;
        else if(population < NINETY_THOUSANDS)
            return 0.8f;
        else
            return 0.9f;

    }
}
