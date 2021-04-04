package com.forest;

// Method Reference
// Three types
// Object::Instance Method
// Class::Static Method
// Class::Instance Method

import com.forest.models.Employee;
import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.*;

public class TestMothodRef {

    @Test
    public void test1() {
        Consumer<String> con = x -> System.out.println(x);

        PrintStream ps = System.out;
        Consumer<String> con1 = ps::println;

        // Lambda 体中调用方法的参数列表与返回值类型，要与函数式接口中抽象方法的函数列表和返回值类型一致
        Consumer<String> con2 = System.out::println;
        con2.accept("agdgfe");
    }

    @Test
    public void test2() {
        Employee employee = new Employee();
        Supplier<String> supplier = () -> employee.getName();
        String str = supplier.get();
        System.out.println(str);

        Supplier<Integer> sup2 = employee::getAge;
        Integer num = sup2.get();
        System.out.println(num);
    }

    @Test
    public void test3() {
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);

        Comparator<Integer> com1 = Integer::compare;
    }

    @Test
    public void test4() {
        BiPredicate<String, String> bp = (x, y) -> x.equals(y);

        // 若Lambda参数列表中的第一个参数是实例方法的调用者，而第二个参数是实例方法的参数时，可以使用ClassName::Method
        BiPredicate<String, String> bp2 = String::equals;
    }

    @Test
    public void test5() {
        Supplier<Employee> sup = () -> new Employee();

        // Constructor Reference
        Supplier<Employee> supplier = Employee::new;
    }

    @Test
    public void test6() {
        Function<String, Employee> fun = (x) -> new Employee(x);

        Function<String, Employee> fun2 = Employee::new;
        Employee e = fun2.apply("Lisi");
        System.out.println(e);

        BiFunction<String, Integer, Employee> bf = Employee::new;
        Employee e2 = bf.apply("Wangwu", 36);
        System.out.println(e2);

    }

    @Test
    public void test7() {
        Function<Integer, String[]> fun = (x) -> new String[x];
        String[] strs = fun.apply(10);
        System.out.println(strs.length);

        Function<Integer, String[]> fun2 = String[]::new;
        String[] strs2 = fun2.apply(20);
        System.out.println(strs2.length);
    }
}
