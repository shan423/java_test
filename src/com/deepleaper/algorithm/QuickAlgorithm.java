package com.deepleaper.algorithm;

import java.util.Arrays;
import java.util.stream.Collectors;

public class QuickAlgorithm {

    public static void sort(Integer[] array, Integer left, Integer right) {
        if (left >= right)
            return;

        int i = left, j = right, tmp;
        Integer base = array[left];

        while (true) {
            //i与j相遇
            if (i == j) {
                //小于base的索引位置
                if (array[i] < base) {
                    tmp = array[i];
                    array[i] = base;
                    array[left] = tmp;
                }
                break;
            }
            if (array[j] >= base) {
                //找到比base小的数
                j--;
                continue;
            }
            if (array[i] <= base) {
                //找到比base大的数
                i++;
                continue;
            }

            tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }
        //left
        sort(array, 0, i - 1);
        //right
        sort(array, i + 1, right);
    }

    public static void handle(Integer[] array) {
        sort(array, 0, array.length - 1);
    }

    public static void main(String[] args) {
        Integer[] sample = SampleData.getSample();
        handle(sample);
        String s = Arrays.stream(sample).map(String::valueOf).collect(Collectors.joining(","));
        System.out.println(s);
    }
}
