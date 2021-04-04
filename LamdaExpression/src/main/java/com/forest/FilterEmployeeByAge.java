package com.forest;

import com.forest.interfaces.MyPredicate;
import com.forest.models.Employee;

public class FilterEmployeeByAge implements MyPredicate<Employee> {

    @Override
    public boolean test(Employee t) {
        return t.getAge() >= 35;
    }
}
