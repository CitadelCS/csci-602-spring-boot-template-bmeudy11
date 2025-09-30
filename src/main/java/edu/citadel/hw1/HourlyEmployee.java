package edu.citadel.hw1;

import java.time.LocalDate;
import java.util.Objects;


//represents employee who is paid hourly
public class HourlyEmployee extends Employee {
    private double wageRate;
    private double hoursWorked;

    /**
     * Public constructor that initializes all four fields (two
     * declared in this class and two inherited from class `Employee`)
     *
     * @param name
     * @param hireDate
     * @param wageRate
     * @param hoursWorked
     */
    public HourlyEmployee(String name, LocalDate hireDate, double wageRate, double hoursWorked)
    {
        //call the Employee constructor to initialize name and hireDate
        super(name, hireDate);

        //initialize HourlyEmployee
        this.wageRate = wageRate;
        this.hoursWorked = hoursWorked;
    }

    //getter for wageRate
    public double getWageRate() {
        return wageRate;
    }

    //getter for hoursWorked
    public double getHoursWorked() {
        return hoursWorked;
    }

    /**
     * Public method `getMonthlyPay()` that overrides the method from the superclass
     * For this class monthly pay is computed as `wageRate` times hoursWorked
     * @return total monthly pay as double
     */
    @Override
    public double getMonthlyPay() {
        return hoursWorked * wageRate;
    }

    /**
     * Public method `toString()` that returns a string of the form
     * overrides the default toString() to return custom string
     * @return string `"HourlyEmployee[name=John Doe, hireDate=2009-05-21, wageRate=50.5, hoursWorked=160.0]"`
     */
    @Override
    public String toString() {
        return "HourlyEmployee[name=" + getName() + ", hireDate=" + getHireDate() +
                ", wageRate=" + wageRate + ", hoursWorked=" + hoursWorked + "]";
    }

    /**
     * Public methods `hashCode()`, overrides the hashCode method
     * @return has code integer based on all fields
     */
    @Override
    public int hashCode() {
        return Objects.hash(getName(), getHireDate(), wageRate, hoursWorked);
    }

    /**
     * Public methods `equals()`, overrides the equals method and
     * determines if two HourlyEmployee objects are the same
     * @param obj object to compare
     * @return if objects are equal = true, else false
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) { return false;}

        if (!super.equals(obj)) { return false;}

        //try to cast obj as an HourlyEmployee, then compare
        //each field to see if obj values are the same
        try {
            HourlyEmployee other = (HourlyEmployee) obj;

            return Double.compare(other.wageRate, wageRate) == 0 &&
                    Double.compare(other.hoursWorked, hoursWorked) == 0 &&
                    Objects.equals(other.getName(), getName()) &&
                    Objects.equals(other.getHireDate(), getHireDate());
        } catch (Exception e) {
            return false;
        }
    }
}
