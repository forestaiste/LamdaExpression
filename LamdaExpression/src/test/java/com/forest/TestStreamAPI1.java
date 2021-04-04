package com.forest;

// Three steps
//
// 1. Create Stream
//
// 2. Intermediate operation
//
// 3. Final Operation

import com.forest.models.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class TestStreamAPI1 {
    @Test
    public void test1() {
        // 1. Create stream() through Collection
        List<String> list = new ArrayList<>();
        Stream<String> stream1 = list.stream();

        // 2. Create stream() through Arrays static methods
        Employee[] employees = new Employee[10];
        Stream<Employee> stream2 = Arrays.stream(employees);

        // 3. Create stream() through static of method in Stream
        Stream<String> stream3 = Stream.of("aa", "bb", "cc");

        // 4. Create infinite stream
        Stream<Integer> stream4 = Stream.iterate(0, x -> x + 2);
        stream4.limit(10).forEach(System.out::println);

        Stream.generate(() -> Math.random()).limit(10).forEach(System.out::println);
    }

}
