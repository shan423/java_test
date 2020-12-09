package com.deepleaper;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class NumberBreaker {

    public static void maybe(Set<String> one, String[] array, String result, int top) {

        Arrays.stream(array).parallel().forEach(item -> {
            String base = item;
            String tmp = result.toString();
            if ("".equals(tmp)) {
                tmp = base;
            } else {
                tmp += "+" + base;
            }
            String[] tmpArray = tmp.split("\\+");

            if ((tmpArray.length) == top) {
                one.add(Arrays.stream(tmp.split("\\+")).sorted().collect(Collectors.joining("+")));
            } else {
                maybe(one, array, tmp, top);
            }
        });
    }

    public static void main(String[] args) throws Exception {
        Set<String> result = Collections.synchronizedSet(new HashSet<>());
        String[] array = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        String[] loopArray = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9"};
        String tmp = "";

        Arrays.stream(loopArray).parallel().forEach(i -> {
            maybe(result, array, tmp, Integer.parseInt(i));
        });

        List<String> finalResult = result.stream().parallel().filter(item -> Arrays.stream(item.split("\\+")).mapToInt(Integer::parseInt).sum() == 10).collect(Collectors.toList());

        String content = finalResult.stream().collect(Collectors.joining(System.lineSeparator()));
        Files.write(Paths.get("/tmp/output.txt"), content.getBytes());
    }
}
