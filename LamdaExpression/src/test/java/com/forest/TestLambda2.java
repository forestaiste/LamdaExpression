package com.forest;

import com.forest.interfaces.MyFun;
import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

public class TestLambda2 {

    // No parameter, no return value
    @Test
    public void test1() {
        int num = 100;
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World!" + num);
            }
        };

        r.run();

        System.out.println("--------------------------");

        Runnable r1 = () -> System.out.println("Hello Lambda!" + num);

        r1.run();
    }

    // One parameter, no return value. The parentheses can be removed
    @Test
    public void test2() {
        Consumer<String> con = x -> System.out.println(x);

        con.accept("Hello World!");
    }

    // More than one parameters with multiple sentences
    @Test
    public void test3() {

        Comparator<Integer> com = (x, y) -> {
            System.out.println("function interface");
            return Integer.compare(x, y);
        };
    }

    // one sentence, return can be removed
    // parameter type can be removed because JVM can infer the type from the context.
    // lambda expression need functional interface's support that has only one method in that interface
    @Test
    public void test4() {

        Comparator<Integer> com = (Integer x, Integer y) -> Integer.compare(x, y);
        Comparator<Integer> com1 = (x, y) -> Integer.compare(x, y);
    }

    @Test
    public void test6() {
        System.out.println(operation(100, x -> x * x));

        System.out.println(operation(300, x-> x + 100));
    }

    public Integer operation(Integer num, MyFun mf) {
        return mf.getValue(num);
    }
}
