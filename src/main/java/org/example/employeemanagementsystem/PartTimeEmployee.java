package org.example.employeemanagementsystem;

public class PartTimeEmployee extends Employee {
    private final double hourlyRate;
    private final double hoursWorked;

    public PartTimeEmployee(String name, double hourlyRate, double hoursWorked) {
        super(name, "Part-time");
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }

    @Override
    public double calculateSalary() {
        return hourlyRate * hoursWorked;
    }
}
