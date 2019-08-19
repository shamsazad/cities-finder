package com.coveo.citiesfinder.service.util;

public final class ScoreOnDistance {

    private static long SIX_HUNDRED = 600;
    private static long TWELVE_HUNDRED = 1200;
    private static long EIGHTEEN_HUNDRED = 1800;
    private static long TWENTY_FOUR_HUNDRED = 2400;
    private static long THIRTY_HUNDRED = 3000;
    private static long THIRTY_SIX_HUNDRED = 3600;
    private static long FORTY_TWO_HUNDRED = 4200;
    private static long FORTY_EIGHT_HUNDRED = 4800;
    private static long FIFTY_FOUR_HUNDRED = 5400;
    private static long SIXTY_HUNDRED = 6000;
    private static long SIXTY_SIX_HUNDRED = 6600;
    private static long SEVENTY_TWO_HUNDRED = 7200;
    private static long SEVENTY_EIGHT_HUNDRED = 7800;
    private static long EIGHTY_FOUR_HUNDRED = 8400;
    private static long NINETY_HUNDRED = 9000;
    private static long NINETY_SIX_HUNDRED = 9600;
    private static long TEN_THOUSAND_TWO_HUNDRED = 10200;

    /**
     *
     * @param distance
     * @return a score based on distance
     */
    public static float getScoreOnDistance(double distance) {

        if(distance == 0)
            return 1;
        else if(distance < SIX_HUNDRED)
            return 0.95f;
        else if(distance < TWELVE_HUNDRED)
            return 0.90f;
        else if(distance < EIGHTEEN_HUNDRED)
            return 0.85f;
        else if(distance < TWENTY_FOUR_HUNDRED)
            return 0.80f;
        else if(distance < THIRTY_HUNDRED)
            return 0.75f;
        else if(distance < THIRTY_SIX_HUNDRED)
            return 0.70f;
        else if(distance < FORTY_TWO_HUNDRED)
            return 0.65f;
        else if(distance < FORTY_EIGHT_HUNDRED)
            return 0.60f;
        else if(distance < FIFTY_FOUR_HUNDRED)
            return 0.55f;
        else if(distance < SIXTY_HUNDRED)
            return 0.50f;
        else if(distance < SIXTY_SIX_HUNDRED)
            return 0.45f;
        else if(distance < SEVENTY_TWO_HUNDRED)
            return 0.40f;
        else if(distance  < SEVENTY_EIGHT_HUNDRED)
            return 0.35f;
        else if(distance < EIGHTY_FOUR_HUNDRED)
            return 0.30f;
        else if(distance < NINETY_HUNDRED)
            return 0.25f;
        else if(distance < NINETY_SIX_HUNDRED)
            return 0.20f;
        else if(distance < TEN_THOUSAND_TWO_HUNDRED)
            return 0.15f;
        else
            return 0.10f;

    }
}
