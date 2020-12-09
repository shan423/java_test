package com.deepleaper;

import com.sun.xml.internal.ws.util.StringUtils;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AliUrlWrapper {
    public static void main(String[] args) throws Exception {

        Integer t = null;
        System.out.println(t==null?0:t);

        List<String> lines = Files.readAllLines(Paths.get("./file/", "新建文本文档.txt"));
        List<String> result = new ArrayList<>();

        for (String rawline : lines) {
            if (rawline == null || rawline.split("\t").length != 3)
                continue;

            String pid = rawline.split("\t")[1];
            String line = rawline.split("\t")[2];

            String[] array = pid.split("_");
            if (array.length == 4) {
                result.add("rewrite ^/landing/aitaobao_" + array[3] + ".html " + line + " redirect;");
            } else {
                System.out.println("exists invalid line");
            }
        }

        System.out.println(result.stream().collect(Collectors.joining(System.lineSeparator())));
    }
}
