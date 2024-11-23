package org.example.employeemanagementsystem;

public abstract class Employee {
    private final String name;
    private final String type;

    public Employee(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public abstract double calculateSalary();
}

