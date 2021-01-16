package com.deepleaper.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NumberCombination {

    public static void number(List<String> elements, String resultItem, List<Long> result) {
        for (int i = 0; i < elements.size(); i++) {
            List<String> remain = new ArrayList<>(elements);
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
        List<String> elements = Stream.of("1", "2", "3", "4", "5", "6", "7", "8", "9").collect(Collectors.toList());
        List<Long> result = new ArrayList<>();
        number(elements, new String(), result);
        System.out.println(result.stream().map(String::valueOf).collect(Collectors.joining(",")));
    }
}
