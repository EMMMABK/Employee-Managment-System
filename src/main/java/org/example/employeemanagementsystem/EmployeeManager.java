package org.example.employeemanagementsystem;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class EmployeeManager extends Application {
    private TableView<Employee> tableView;
    private ObservableList<Employee> employeeList;

    @Override
    public void start(Stage stage) {
        tableView = new TableView<>();
        employeeList = FXCollections.observableArrayList();

        setupTableView();

        TextField nameField = new TextField();
        nameField.setPromptText("Name");

        ComboBox<String> typeBox = new ComboBox<>();
        typeBox.getItems().addAll("Full-time", "Part-time", "Contractor");

        TextField salaryOrRateField = new TextField();
        salaryOrRateField.setPromptText("Annual Salary or Hourly Rate");

        TextField hoursField = new TextField();
        hoursField.setPromptText("Hours Worked / Max Hours");

        Button addButton = new Button("Add Employee");
        addButton.setOnAction(e -> addEmployee(nameField, typeBox, salaryOrRateField, hoursField));

        VBox vbox = new VBox(10, tableView, nameField, typeBox, salaryOrRateField, hoursField, addButton);

        Scene scene = new Scene(vbox, 600, 400);
        stage.setScene(scene);
        stage.setTitle("Employee Manager");
        stage.show();
    }

    private void setupTableView() {
        TableColumn<Employee, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));

        TableColumn<Employee, String> typeColumn = new TableColumn<>("Type");
        typeColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getType()));

        TableColumn<Employee, Double> salaryColumn = new TableColumn<>("Salary");
        salaryColumn.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().calculateSalary()).asObject());

        tableView.getColumns().addAll(nameColumn, typeColumn, salaryColumn);
        tableView.setItems(employeeList);
    }

    private void addEmployee(TextField nameField, ComboBox<String> typeBox, TextField salaryOrRateField, TextField hoursField) {
        String name = nameField.getText();
        String type = typeBox.getValue();
        double salaryOrRate = Double.parseDouble(salaryOrRateField.getText());
        double hours = hoursField.getText().isEmpty() ? 0 : Double.parseDouble(hoursField.getText());

        Employee employee;
        if ("Full-time".equals(type)) {
            employee = new FullTimeEmployee(name, salaryOrRate);
        } else if ("Part-time".equals(type)) {
            employee = new PartTimeEmployee(name, salaryOrRate, hours);
        } else {
            employee = new Contractor(name, salaryOrRate, hours);
        }

        employeeList.add(employee);
        clearFields(nameField, typeBox, salaryOrRateField, hoursField);
    }

    private void clearFields(TextField nameField, ComboBox<String> typeBox, TextField salaryOrRateField, TextField hoursField) {
        nameField.clear();
        typeBox.setValue(null);
        salaryOrRateField.clear();
        hoursField.clear();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

