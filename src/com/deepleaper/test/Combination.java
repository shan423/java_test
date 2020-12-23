package com.deepleaper.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Combination {

    public static int combinationNum = 0;
    public static int permutationNum = 0;

    public static void permutation(int top, int level, List<String> elements, String prefix, List<String> result) {
        if (top <= level)
            return;
        int loop = elements.size();

        while (loop-- > 0) {
            permutationNum++;
            if (!prefix.contains(elements.get(loop))) {
                String copy = new String(prefix);

                if (copy.isEmpty())
                    copy = elements.get(loop);
                else
                    copy += ("," + elements.get(loop));

                if (copy.split(",").length == top) {
                    if (!result.contains(copy))
                        result.add(copy);
                } else {
                    List<String> remainElements = new ArrayList<>(elements);
                    remainElements.remove(elements.get(loop));
                    permutation(top, level + 1, remainElements, copy, result);
                }
            }
        }
    }

    public static void combination(int top, int level, List<String> elements, String prefix, List<String> result) {
        if (top <= level)
            return;
        int loop = elements.size();

        while (loop-- > 0) {
            combinationNum++;
            if (!prefix.contains(elements.get(loop))) {
                String copy = new String(prefix);

                if (copy.isEmpty())
                    copy = elements.get(loop);
                else
                    copy += ("," + elements.get(loop));

                String[] array = copy.split(",");
                if (array.length == top) {
                    copy = Arrays.stream(array).sorted().collect(Collectors.joining(","));
                    if (!result.contains(copy))
                        result.add(copy);
                } else {
                    List<String> remainElements = new ArrayList<>(elements);
                    remainElements.remove(elements.get(loop));
                    combination(top, level + 1, remainElements, copy, result);
                }
            }
        }
    }

    public static void main(String[] args) {
        String[] abc = new String[]{"a", "b", "c"};
        String[] xyz = new String[]{"x", "y", "z"};

        List<String> allElements = new ArrayList<>();
        for (int i = 0; i < abc.length; i++) {
            for (int j = 0; j < xyz.length; j++) {
                allElements.add(abc[i] + xyz[j]);
            }
        }

        List<String> permutationResult = new ArrayList<>();
        List<String> combinationResult = new ArrayList<>();
        permutation(3, 0, allElements, "", permutationResult);
        combination(3, 0, allElements, "", combinationResult);

        System.out.println("permutationResult: (" + permutationResult.stream().collect(Collectors.joining(")(")) + ")");
        System.out.println("combinationResult: (" + combinationResult.stream().collect(Collectors.joining(")(")) + ")");
    }
}
