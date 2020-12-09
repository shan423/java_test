package com.deepleaper.algorithm;

import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;

import java.util.Arrays;
import java.util.stream.Collectors;

public class BucketAlgorithm {
    public static void handle(Integer[] array) {
        Integer max = Integer.MIN_VALUE;
        for (Integer item : array) {
            if (item > max)
                max = item;
        }
        int[] bucket = new int[max + 1];
        for (int i = 0; i < array.length; i++) {
            bucket[array[i]]++;
        }
        int j = array.length - 1;
        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] > 0) {
                while (bucket[i] > 0) {
                    array[j] = i;
                    j--;
                    bucket[i]--;
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
