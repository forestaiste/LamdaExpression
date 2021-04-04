package com.forest;

import com.forest.models.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class TestStreamAPI4 {
    List<Employee> employees = Arrays.asList(
            new Employee("zhangsan", 18, 9999.99, Employee.Status.BUSY),
            new Employee("lisi", 38, 5555.33, Employee.Status.FREE),
            new Employee("wangwu", 45, 666.66, Employee.Status.VOCATION),
            new Employee("zhaoliu", 50, 7777.99, Employee.Status.FREE),
            new Employee("tianqi", 10, 8888.99, Employee.Status.VOCATION)
    );


    @Test
    public void test1() {
        Integer[] nums = new Integer[]{1, 2, 3, 4, 5};

        Arrays.stream(nums).map(n -> n * n).forEach(System.out::println);
    }

    @Test
    public void test2() {
        Optional<Integer> count = employees.stream().map(e -> 1).reduce(Integer::sum);
        System.out.println(count.get());
    }
}
