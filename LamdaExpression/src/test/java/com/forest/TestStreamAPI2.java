package com.forest;

import com.forest.models.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class TestStreamAPI2 {

    List<Employee> employees = Arrays.asList(
            new Employee("zhangsan", 18, 9999.99),
            new Employee("lisi", 38, 5555.33),
            new Employee("wangwu", 45, 666.66),
            new Employee("zhaoliu", 50, 7777.99),
            new Employee("tianqi", 10, 8888.99),
            new Employee("tianqi", 10, 8888.99)
    );

    // intermediate operation
    // filter
    @Test
    public void test1() {
        // intermediate operation
        Stream<Employee> stream = employees.stream().filter(e -> e.getAge() > 35);

        // final operation
        stream.forEach(System.out::println);
    }

    // limit
    @Test
    public void test2() {
        employees.stream().filter(e -> e.getSalary() > 5000).limit(2).forEach(System.out::println);
    }

    // skip(n)
    @Test
    public void test3() {
        employees.stream().filter(e -> e.getSalary() > 5000).skip(2).forEach(System.out::println);
    }

    // distinct
    @Test
    public void test4() {
        employees.stream().filter(e -> e.getSalary() > 5000).skip(2).distinct().forEach(System.out::println);
    }

    // map
    // flatMap
    @Test
    public void test5() {
        List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee");

        list.stream().map(str -> str.toUpperCase(Locale.ROOT)).forEach(System.out::println);

        System.out.println("--------------------");

        employees.stream().map(Employee::getName).forEach(System.out::println);

        Stream<Stream<Character>> stream = list.stream().map(TestStreamAPI2::filterCharacter);

        stream.forEach(s -> {
            s.forEach(System.out::println);
        });

        System.out.println("--------------------");

        list.stream().flatMap(TestStreamAPI2::filterCharacter).forEach(System.out::println);
    }

    public static Stream<Character> filterCharacter(String str) {
        List<Character> list = new ArrayList<>();

        for (Character c : str.toCharArray()) {
            list.add(c);
        }

        return list.stream();
    }


    // sort
    // sorted
    // sorted(Comparator com)
    @Test
    public void test6() {
        List<String> list = Arrays.asList( "eee", "ccc", "aaa", "bbb", "ddd");

        list.stream().sorted().forEach(System.out::println);

        System.out.println("----------------------------");

        employees.stream().sorted((e1, e2) -> e2.getAge() - e1.getAge()).forEach(System.out::println);
    }
}
