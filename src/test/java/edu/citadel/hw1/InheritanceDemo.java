package edu.citadel.hw1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


//this class performs tests on the Employee, HourlyEmployee and SalariedEmployee classes
public class InheritanceDemo {
    public static void main(String[] args) {
        //1. Create and arrayList for Employee objects
        List<Employee> employees = new ArrayList<>();

        //2. Create two hourly employees as follows and add them to the list
        //    name=John Doe, hireDate=2009-05-21, wageRate=50.5, hourWorked=160.0
        //    name=Jane Doe, hireDate=2005-09-01, wageRate=150.5, hourWorked=80.0
        employees.add(new HourlyEmployee("John Doe", LocalDate.of(2009, 5, 21), 50.5, 160.0));
        employees.add(new HourlyEmployee("Jane Doe", LocalDate.of(2005, 9, 1), 150.5, 80.0));

        //3. Create two salaried employees as follows and add them to the list
        //    name=Moe Howard, hireDate=2004-01-01, annualSalary=75000.0
        //    name=Curly Howard, hireDate=2018-01-01, annualSalary=105000.0
        employees.add(new SalariedEmployee("Moe Howard", LocalDate.of(2004, 1, 1), 75000.0));
        employees.add(new SalariedEmployee("Curly Howard", LocalDate.of(2018, 1, 1), 105000.0));

        //4. Print “`List of Employees (before sorting)`” followed by a listing of each employee, one per employee
        //    per line. Employees should be written in the order that the employee was added to the list.
        System.out.println("List of Employees (before sorting)");
        for (Employee employee : employees) {
            System.out.println(employee);
        }

        //Print out a blank line.
        System.out.println();

        //5. Using class `Collections` from `java.util, call Collections.sort()` passing the list of employees as an
        //    argument. This will use your `compareTo()` method to sort the list based on monthly pay, so that employees
        //    with lower monthly pay will be in the list before those with higher monthly pay.
        Collections.sort(employees);

        //6. Print “`List of Employees (after sorting)`” followed by a listing of each employee, one per employee
        //    per line. Employees should now be written in the sorted order. (The order of the listing will not be obvious
        //    at this point, but it will become more meaningful after the next few steps where we print the monthly pay for
        //    each employee.)
        System.out.println("List of Employees (after sorting)");
        for (Employee employee : employees) {
            System.out.println(employee);
        }

        //Print out a blank line.
        System.out.println();

        //7. Print “`Monthly Pay`” followed by a listing of the monthly pay for each employee followed by the total
        //    monthly pay for all employees
        //    Print the monthly pay for each employee using code similar to the following:
        //    `printf("%s: $%,.2f\n", employee.getName(), employee.getMonthlyPay())`
        //    Print the total pay for all employees using code similar to the following:
        //    `printf("Total Monthly Pay: $%,.2f", totalPay)`
        System.out.println("Monthly Pay");

        //calculate total pay
        double totalPay = 0;
        for (Employee employee : employees) {
            System.out.printf("%s: $%,.2f\n", employee.getName(), employee.getMonthlyPay());
            totalPay += employee.getMonthlyPay();
        }
        System.out.printf("Total Monthly Pay: $%,.2f" , totalPay);
    }
}
