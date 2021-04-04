package com.forest;

import com.forest.models.Employee;
import org.junit.Test;

import java.net.CookieHandler;
import java.sql.Statement;
import java.util.*;
import java.util.stream.Collectors;

public class TestStreamAPI3 {

    List<Employee> employees = Arrays.asList(
            new Employee("zhangsan", 18, 9999.99, Employee.Status.BUSY),
            new Employee("lisi", 38, 5555.33, Employee.Status.FREE),
            new Employee("wangwu", 45, 666.66, Employee.Status.VOCATION),
            new Employee("zhaoliu", 50, 7777.99, Employee.Status.FREE),
            new Employee("tianqi", 10, 8888.99, Employee.Status.VOCATION),
            new Employee("tianqi", 10, 8888.99, Employee.Status.BUSY)
    );

    // allMatch
    // anyMatch
    // noneMatch
    // findFirst
    // findAny
    // count
    // max
    // min
    @Test
    public void test1() {
        boolean b1 = employees.stream().allMatch(e -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b1);

        b1 = employees.stream().anyMatch(e -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b1);

        b1 = employees.stream().noneMatch(e -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b1);

        Optional<Employee> op = employees.stream()
                .sorted((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()))
                .findFirst();

        System.out.println(op.get());

        Optional<Employee> op1 = employees.stream()
                .sorted((e1, e2) -> -Double.compare(e1.getSalary(), e2.getSalary()))
                .findFirst();

        System.out.println(op1.get());

        Optional<Employee> op2 = employees.stream().filter(e -> e.getStatus().equals(Employee.Status.BUSY))
                .findAny();

        System.out.println(op2.get());

    }

    @Test
    public void test2() {
        Long count = employees.stream().count();

        System.out.println(count);

        Optional<Employee> op1 = employees.stream().max((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));

        System.out.println(op1.get());

        Optional<Double> op2 = employees.stream().map(Employee::getSalary).min(Double::compare);
        System.out.println(op2.get());
    }

    //reduce
    @Test
    public void test3() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        Integer sum = list.stream().reduce(0, (x, y) -> x + y);
        System.out.println(sum);

        System.out.println("---------------------------------");

        Optional<Double> op = employees.stream()
                .map(Employee::getSalary)
                .reduce(Double::sum);

        System.out.println(op.get());
    }

    // collect
    @Test
    public void test4() {
        List<String> list = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());

        list.forEach(System.out::println);

        System.out.println("--------------------------------");

        Set<String> set = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toSet());

        set.forEach(System.out::println);

        System.out.println("--------------------------------");

        HashSet<String> hs = employees.stream().map(Employee::getName)
                .collect(Collectors.toCollection(HashSet::new));

        hs.forEach(System.out::println);
    }


    @Test
    public void test5() {
        Long count = employees.stream().collect(Collectors.counting());
        System.out.println(count);

        System.out.println("---------------------------------");

        Double average = employees.stream().collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(average);

        Double sum = employees.stream().collect(Collectors.summingDouble(Employee::getSalary));
        System.out.println(sum);

        Optional<Employee> max = employees.stream().collect(Collectors.maxBy((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())));
        System.out.println(max.get());

        Optional<Double> min = employees.stream().map(Employee::getSalary).collect(Collectors.minBy(Double::compare));
        System.out.println(min.get());

    }

    // Group
    @Test
    public void test6() {
        Map<Employee.Status,  List<Employee>> map = employees.stream().collect(Collectors.groupingBy(Employee::getStatus));
        System.out.println(map);
    }

    // Multiple level group
    @Test
    public void test7() {
        Map<Employee.Status,  Map<String, List<Employee>>> map = employees.stream()
                .collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy(e -> {
                    if (e.getAge() <= 35) {
                        return "Youth";
                    }
                    else if (e.getAge() <= 50) {
                        return "Middle";
                    }
                    else {
                        return "Old";
                    }
                })));

        System.out.println(map);
    }

    // Partition
    @Test
    public void test8() {
        Map<Boolean, List<Employee>> map = employees.stream()
                .collect(Collectors.partitioningBy(e -> e.getSalary() > 8000));

        System.out.println(map);
    }

    @Test
    public void test9() {
        DoubleSummaryStatistics ds = employees.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));

        System.out.println(ds.getMax());
        System.out.println(ds.getAverage());
        System.out.println(ds.getSum());
    }

    @Test
    public void test10() {
        String str = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.joining(","));

        System.out.println(str);
    }
}
