package com.deepleaper.algorithm;

public class MatchStick {

    public static int getStickNum(int[] match, int number) {
        if (number == 0)
            return match[0];
        int result = 0;
        while (number > 0) {
            result += match[number % 10];
            number = number / 10;
        }
        return result;
    }

    public static String getMatch(int[] match, int m) {
        StringBuilder result = null;

        for (int a = 0; a <= 1111; a++) {
            for (int b = 0; b <= 1111; b++) {
                for (int c = 0; c <= 1111; c++) {
                    if (((a + b) == c) && ((getStickNum(match, a) + getStickNum(match, b) + getStickNum(match, c) + 4) == m)) {
                        String item = a + "+" + b + "=" + c;
                        if (result == null)
                            result = new StringBuilder(item);
                        else {
                            if (!result.toString().contains(item))
                                result.append(",").append(item);
                        }
                    }
                }
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        int[] match = new int[]{6, 2, 5, 5, 4, 5, 6, 3, 7, 6};
        int m = 18;

        String result = getMatch(match, m);
        System.out.println(result);
    }
}
