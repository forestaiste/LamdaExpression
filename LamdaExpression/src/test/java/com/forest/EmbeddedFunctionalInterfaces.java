package com.forest;

import com.forest.models.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;


// Four types of functional interfaces in Java 8
// Consumer<T>: Consumer Interface
// void accept(T t);
//
// Supplier<T>: Supplier Interface
// T get()
//
// Function<T, R>: Function Interface
// R apply(T t);
//
// Predicate<T>: Assert Interface
// boolean test(T t);
//
public class EmbeddedFunctionalInterfaces {

    List<Employee> employees = Arrays.asList(
            new Employee("zhangsan", 18, 9999.99),
            new Employee("lisi", 38, 5555.33),
            new Employee("wangwu", 45, 666.66),
            new Employee("zhaoliu", 50, 7777.99),
            new Employee("tianqi", 38, 8888.99)
    );

    @Test
    public void test1() {
        Collections.sort(employees, (e1, e2) -> {
            if (e1.getAge() == e2.getAge()) {
                return e1.getName().compareTo(e2.getName());
            }
            else {
                return Integer.compare(e1.getAge(), e2.getAge());
            }
        });

        employees.forEach(System.out::println);
    }


    // Consumer<T>
    @Test
    public void test2() {
        happy(10000, m -> System.out.println("Massage cost " + m + " dollars."));
    }

    public void happy(double money, Consumer<Double> con) {
        con.accept(money);
    }

    // Supplier<T>
    @Test
    public void test3() {
        List<Integer> numList = getNumList(10, () -> (int)(Math.random() * 100));
        for (Integer num : numList) {
            System.out.println(num);
        }
    }

    public List<Integer> getNumList(int num, Supplier<Integer> sup) {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < num; i++) {
            Integer n = sup.get();
            list.add(n);
        }
        return list;
    }

    // Function<T, R>
    @Test
    public void test4() {
        String str = strHandler("\t\t\t Hello Microsoft ", s -> s.trim());
        System.out.println(str);

        str = strHandler("I got the offer.", s -> s.substring(2, 7));
        System.out.println(str);
    }

    public String strHandler(String str, Function<String, String> fun) {
        return fun.apply(str);
    }

    // Predicate<T>
    @Test
    public void test5() {
        List<String> list = Arrays.asList("Hello", " Microsoft.", " I'm", " coming.");
        List<String> results = filterStr(list, s -> s.length() > 4);

        results.forEach(System.out::println);

    }
    public List<String> filterStr(List<String> list, Predicate<String> pre) {
        List<String> strings = new ArrayList<>();

        for (String s : list) {
            if (pre.test(s)) {
                strings.add(s);
            }
        }

        return strings;
    }
}
