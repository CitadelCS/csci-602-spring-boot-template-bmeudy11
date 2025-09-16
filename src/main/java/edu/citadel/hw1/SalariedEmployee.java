package edu.citadel.hw1;

import java.time.LocalDate;
import java.util.Objects;

//represents employee who is salaried
public class SalariedEmployee extends Employee {
    private double annualSalary;

    //const for number of months an employee is paid
    public static final int PAY_MONTHS = 12;


    /**
     * Public constructor that initializes all three fields (one
     * declared in this class and two inherited from class `Employee`)
     *
     * @param name
     * @param hireDate
     * @param annualSalary
     */
    public SalariedEmployee(String name, LocalDate hireDate, double annualSalary) {
        //call the Employee constructor to initialize name and hireDate
        super(name, hireDate);

        //initialize SalariedEmployee
        this.annualSalary = annualSalary;
    }

    //getter for wageRate
    public double getAnnualSalary() {
        return annualSalary;
    }

    /**
     * Public method `getMonthlyPay()` that overrides the method from the superclass
     * For this class monthly pay is computed as `wageRate` times hoursWorked
     * @return total monthly pay as double
     */
    @Override
    public double getMonthlyPay() {
        return annualSalary / PAY_MONTHS;
    }

    /**
     * Public method `toString()` that returns a string of the form
     * overrides the default toString() to return custom string
     * @return string `"SalariedEmployee[name=Curly Howard, hireDate=2009-05-21, annualSalary=105000.0]"`
     */
    @Override
    public String toString() {
        return "SalariedEmployee[name=" + getName() + ", hireDate=" + getHireDate() +
                ", annualSalary=" + annualSalary + "]";
    }

    /**
     * Public methods `hashCode()`, overrides the hashCode method
     * @return has code integer based on all fields
     */
    @Override
    public int hashCode() {
        return Objects.hash(getName(), getHireDate(), annualSalary);
    }

    /**
     * Public methods `equals()`, overrides the equals method and
     * determines if two SalariedEmployee objects are the same
     * @param obj object to compare
     * @return if objects are equal = true, else false
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) { return false;}

        if (!super.equals(obj)) { return false;}

        //try to cast obj as an SalariedEmployee, then compare
        //each field to see if obj values are the same
        try {
            SalariedEmployee other = (SalariedEmployee) obj;

            return Double.compare(other.annualSalary, annualSalary) == 0 &&
                    Objects.equals(other.getName(), getName()) &&
                    Objects.equals(other.getHireDate(), getHireDate());
        } catch (Exception e) {
            return false;
        }
    }
}
