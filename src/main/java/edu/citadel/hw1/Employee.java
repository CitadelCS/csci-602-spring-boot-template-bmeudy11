package edu.citadel.hw1;

import java.time.LocalDate;

/**
 * An abstract class representing an employee, it implements
 * Comparable<Employee></Employee>.
 */
public abstract class Employee implements Comparable<Employee> {
    private String name;
    private LocalDate hireDate;

    /**
     * constructor initializing name and hireDate
     *
     * @param name
     * @param hireDate
     */
    public Employee(String name, LocalDate hireDate) {
        this.name = name;
        this.hireDate = hireDate;
    }

    //getter for name
    //use lombok here?
    public String getName() {
        return name;
    }

    //getter for hireDate
    //use lombok here?
    public LocalDate getHireDate() {
        return hireDate;
    }

    //calculate monthly pay and return as double
    public abstract double getMonthlyPay();

    /**
     * Method `compareTo()` as required by interface `Comparable`. This method should order employees based on
     * their monthly salaries. So, for example, if the monthly salary for `emp1` is less than the monthly salary for
     * `emp2`, then `emp1.compareTo(emp2)` should return a negative number.
     *
     * @param otherEmployee - employee to compare to
     * @return comparison as integer
     */
    @Override
    public int compareTo(Employee otherEmployee) {
        return Double.compare(this.getMonthlyPay(), otherEmployee.getMonthlyPay());
    }
}
