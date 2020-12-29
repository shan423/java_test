package com.deepleaper.algorithm;

import java.security.PublicKey;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Merge {

    public static int[] merge(int[] a, int[] b) {
        int[] c = new int[a.length + b.length];

        for (int ic = 0, ia = 0, ib = 0; ic < c.length; ic++) {
            if (ia == a.length) {
                c[ic] = b[ib];
                ib++;
                continue;
            }
            if (ib == b.length) {
                c[ic] = a[ia];
                ia++;
                continue;
            }
            if (a[ia] <= b[ib]) {
                c[ic] = a[ia];
                ia++;
            } else {
                c[ic] = b[ib];
                ib++;
            }
        }

        return c;
    }

    public static void main(String[] args) {
        int[] a = new int[]{1, 3, 5, 33, 22, 4, 7};
        int[] b = new int[]{18, 33, 22, 31, 7};
        a = Arrays.stream(a).sorted().toArray();
        b = Arrays.stream(b).sorted().toArray();
        int[] c = merge(a, b);
        System.out.println(Arrays.stream(c).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
    }
}
