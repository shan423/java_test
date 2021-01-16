package com.deepleaper.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NumberCombination {

    public static void number(List<Long> elements, String resultItem, List<Long> result) {
        for (int i = 0; i < elements.size(); i++) {
            List<Long> remain = new ArrayList<>(elements);
            remain.remove(elements.get(i));
            if (remain.size() == 0) {
                result.add(Long.parseLong(resultItem + elements.get(i)));
                return;
            } else {
                number(remain, resultItem + elements.get(i), result);
            }
        }
    }

    public static void main(String[] args) {
        List<Long> elements = Stream.of(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L).collect(Collectors.toList());
        List<Long> result = new ArrayList<>();
        long now = System.currentTimeMillis();
        number(elements, new String(), result);
        System.out.println(System.currentTimeMillis() - now);
        System.out.println(result.size());
    }
}
