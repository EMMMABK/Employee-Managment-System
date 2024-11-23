package org.example.employeemanagementsystem;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;

public class HelloController {
    @FXML
    private TableView<Employee> tableView;
    @FXML
    private TableColumn<Employee, String> nameColumn;
    @FXML
    private TableColumn<Employee, String> typeColumn;
    @FXML
    private TableColumn<Employee, Double> salaryColumn;
    @FXML
    private TextField nameField;
    @FXML
    private ComboBox<String> typeBox;
    @FXML
    private TextField salaryOrRateField;
    @FXML
    private TextField hoursField;

    private final ObservableList<Employee> employeeList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Initialize TableView columns
        nameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));
        typeColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getType()));
        salaryColumn.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().calculateSalary()).asObject());
        tableView.setItems(employeeList);

        // Populate ComboBox with employee types
        typeBox.getItems().addAll("Full-time", "Part-time", "Contractor");
    }

    @FXML
    protected void addEmployee() {
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
        clearFields();
    }

    private void clearFields() {
        nameField.clear();
        typeBox.setValue(null);
        salaryOrRateField.clear();
        hoursField.clear();
    }
}
