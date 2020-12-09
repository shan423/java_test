
package com.deepleaper.test;

import java.util.ArrayList;
import java.util.List;

public class DanceAndSing {

    public static void main(String[] args) {
        String[] abc = new String[]{"a", "b", "c"};
        String[] xyz = new String[]{"x", "y", "z"};

        List<String> result = new ArrayList<>();

        for (int i = 1; i < 3; i++) {
            String item1 = abc[0] + xyz[i];
            for (int j = 0; j < 3; j++) {
                if (i != j) {
                    String item2 = item1 + "," + abc[1] + xyz[j];
                    for (int k = 0; k < 3; k++) {
                        if (k != i && k != j) {
                            String item3 = item2 + "," + abc[2] + xyz[k];
                            result.add(item3);
                            break;
                        }
                    }
                }
            }
        }

        System.out.println(String.join(" ", result));
    }
}
