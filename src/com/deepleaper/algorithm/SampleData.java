package com.deepleaper.algorithm;

public class SampleData {
    private final static Integer[] SAMPLE = new Integer[]{4, 7, 5, 44, 928, 1, 98, 44, 3};
    private final static Integer[] LARGE_TO_SMALL = new Integer[]{928, 98, 44, 44, 7, 5, 4, 3, 1};

    public static Integer[] getSample() {
        return SAMPLE.clone();
    }

    public static Integer[] getLargeToSmall() {
        return LARGE_TO_SMALL.clone();
    }
}
