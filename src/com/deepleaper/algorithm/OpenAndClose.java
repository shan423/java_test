package com.deepleaper.algorithm;

public class OpenAndClose {
    public static String fetch(String s, String tag) {
        if (s == null || tag == null || s.isEmpty() || tag.isEmpty())
            return s;
        StringBuilder result = new StringBuilder();
        int num = 0;
        String lTag = "<" + tag + ">";
        String rTag = "</" + tag + ">";
        int lLen = lTag.length();
        int rLen = rTag.length();
        for (int i = 0; i < s.length(); ) {
            if (i + lLen <= s.length() && lTag.equals(s.substring(i, i + lLen))) {
                if (num == 0) {
                    result.append(lTag);
                }
                i += lLen;
                num++;
                continue;
            } else if (i + rLen <= s.length() && rTag.equals(s.substring(i, i + rLen))) {
                if (num == 1) {
                    result.append(rTag);
                }
                i += rLen;
                num--;
                continue;
            }
            if (num >= 0) {
                result.append(s.charAt(i));
            }
            i++;
        }
        return result.toString();
    }

    public static void main(String[] args) {
        //清楚最里面标记标签
        String a = "a<a>bc<a>haha hello</a> wo shi ~<a> html tag </a> 1</a>1";
        String b = "abc<a>haha hello</a> wo shi ~<a> html tag </a> 11 ";
        //String c ="<a>111111<a>222</a>3333</a>";
        String c ="<a>111111</a>222<a>3333</a>";
        System.out.println(fetch(c, "a"));
    }
}
