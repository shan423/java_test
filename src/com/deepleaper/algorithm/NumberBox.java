package com.deepleaper.algorithm;

import java.util.ArrayList;
import java.util.List;

public class NumberBox {

    public static int[] mark = new int[10];
    public static int[] result = new int[10];
    public static List<Long> all = new ArrayList<>();

    public static void dfs(int max, int pos) {
        if (pos == max + 1) {
            long item = 0L;
            for (int i = 1; i <= max; i++) {
                int tmp = max - i;
                int val = 1;
                while (tmp > 0) {
                    val = val * 10;
                    tmp--;
                }
                item += ((long) result[i] * val);
            }
            all.add(item);
            return;
        }

        for (int i = 1; i <= max; i++) {
            if (mark[i] == 0) {
                result[pos] = i;
                mark[i] = 1;
                dfs(max, pos + 1);
                mark[i] = 0;
            }
        }
    }

    public static void main(String[] args) {
        // max = [1,9];
        long now = System.currentTimeMillis();
        dfs(9, 1);
        System.out.println(System.currentTimeMillis() - now);
        System.out.println(all.size());
    }
}
