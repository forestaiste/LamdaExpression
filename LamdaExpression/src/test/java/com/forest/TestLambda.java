package com.forest;

import static org.junit.Assert.assertTrue;

import com.forest.interfaces.MyPredicate;
import com.forest.models.Employee;
import org.junit.Test;

import java.util.*;

/**
 * Unit test for simple App.
 */
public class TestLambda
{
    @Test
    public void test1()
    {
        Comparator<Integer> com = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };
    }

    @Test
    public void test2()
    {
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
        TreeSet<Integer> ts = new TreeSet<>(com);
    }

    List<Employee> employees = Arrays.asList(
            new Employee("zhangsan", 18, 9999.99),
            new Employee("lisi", 38, 5555.33),
            new Employee("wangwu", 45, 666.66),
            new Employee("zhaoliu", 50, 7777.99),
            new Employee("tianqi", 10, 8888.99)
    );

    public List<Employee> filterEmployees(List<Employee> list) {
        List<Employee> emps = new ArrayList<>();
        for (Employee emp : list) {
            if (emp.getAge() >= 35) {
                emps.add(emp);
            }
        }

        return emps;
    }

    @Test
    public void test3() {
        List<Employee> list = filterEmployees(employees);

        for (Employee e : list) {
            System.out.println(e);
        }
    }

    public List<Employee> filterEmployee(List<Employee> list, MyPredicate<Employee> mp) {
        List<Employee> emps = new ArrayList<>();

        for (Employee employee : list) {
            if (mp.test(employee)) {
                emps.add(employee);
            }
        }

        return emps;
    }

    @Test
    public void test4() {
        List<Employee> list = filterEmployee(employees, new FilterEmployeeByAge());

        for (Employee e : list) {
            System.out.println(e);
        }
    }

    @Test
    public void test5() {
        List<Employee> list = filterEmployee(employees, new MyPredicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getSalary() <= 5000;
            }
        });

        for (Employee employee : list) {
            System.out.println(employee);
        }
    }

    @Test
    public void test6() {
        List<Employee> list = filterEmployee(employees, e -> e.getSalary() >= 5000);
        list.forEach(System.out::println);
    }

    @Test
    public void test7() {
        employees.stream().filter(e -> e.getSalary() >= 5000).forEach(System.out::println);

        System.out.println("------------------------------");
        employees.stream().filter(e -> e.getSalary() >= 5000).map(Employee::getName).forEach(System.out::println);
    }
}
