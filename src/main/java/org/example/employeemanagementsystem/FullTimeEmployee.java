package org.example.employeemanagementsystem;

public class FullTimeEmployee extends Employee {
    private final double annualSalary;

    public FullTimeEmployee(String name, double annualSalary) {
        super(name, "Full-time");
        this.annualSalary = annualSalary;
    }

    @Override
    public double calculateSalary() {
        return annualSalary;
    }
}

