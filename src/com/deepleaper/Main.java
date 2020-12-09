package com.deepleaper;

import javafx.application.Application;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;

public class Main {

    public static Map<String, String> analysis(String url) {
        Map<String, String> paramMap = new HashMap<String, String>();
        if (!"".equals(url)) {// 如果URL不是空字符串
            url = url.substring(url.indexOf('?') + 1);
            String paramaters[] = url.split("&");
            for (String param : paramaters) {
                String values[] = param.split("=");
                if (values.length == 2)
                    paramMap.put(values[0], values[1]);
            }
        }
        return paramMap;
    }

    static class Person {
        private int age;
        private String name;

        public Person(int age, String name) {
            this.age = age;
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) throws Exception {
        //singleTask();
        //mulitipleTask();

        Map<Integer, List<Person>> vv = Arrays.asList(new Person(11, "test"), new Person(12, "test2")).stream().collect(groupingBy(Person::getAge));

    }

    public static int target = 1000000000;

    public static void singleTask() {
        long begin = System.currentTimeMillis();

        NumberKeeper keeper = new NumberKeeper(1);

        while (keeper.getNumber() <= target) {
            keeper.increase();
        }

        System.out.println("single task all done,exhausted " + (System.currentTimeMillis() - begin) + " result" + keeper.getNumber());
    }

    public static void mulitipleTask() throws Exception {
        long begin = System.currentTimeMillis();

        NumberKeeper keeper = new NumberKeeper(1);

        for (int i = 0; i < 4; i++) {
            PoolThreadBase thread = new PoolThreadBase(keeper);
            thread.start();
        }

        while (!keeper.isEnd()) {
            continue;
        }

        System.out.println("mulitipe task all done,exhausted " + (System.currentTimeMillis() - begin) + " result " + keeper.getNumber());
        System.exit(1);
    }
}
