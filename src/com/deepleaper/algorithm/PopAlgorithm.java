package com.deepleaper.algorithm;

import java.util.Arrays;
import java.util.stream.Collectors;

public class PopAlgorithm {

    public static void handle(Integer[] array) {
        for (int i = 1; i < array.length; i++) {
            for (int j = 0; j < array.length - i; j++) {
                if (array[j] < array[j + 1]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
            }
        }
    }

    public static void main(String[] args) {
        Integer[] sample = SampleData.getSample();
        handle(sample);
        String s = Arrays.stream(sample).map(String::valueOf).collect(Collectors.joining(","));
        System.out.println(s);
    }
}
