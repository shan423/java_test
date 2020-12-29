package com.deepleaper.algorithm;

public class PalindromeString {

    public static boolean check(String a) {
        int l = a.length() / 2;
        for (int i = 0; i <= l; i++) {
            if (a.charAt(i) != a.charAt(a.length() - i - 1))
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String a = "xax";
        String b = "xaax";
        String c = "dfalsdjfljsdlkjf";
        System.out.println(check(c));
    }
}
