package fr.clerc.tp3.model;

import com.google.gson.annotations.SerializedName;

public class EmployeeData {
    int id;
    @SerializedName("employee_name")
    private String employeeName;
    @SerializedName("employee_age")
    private int employeeAge;
    @SerializedName("employee_salary")
    private int employeeSalary;

    public EmployeeData(int id, String employeeName, int employeeAge, int employeeSalary) {
        this.id = id;
        this.employeeName = employeeName;
        this.employeeAge = employeeAge;
        this.employeeSalary = employeeSalary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public int getEmployeeAge() { return employeeAge; }

    public void setEmployeeAge(int employeeAge) {
        this.employeeAge = employeeAge;
    }

    public int getEmployeeSalary() {
        return employeeSalary;
    }

    public void setEmployeeSalary(int employeeSalary) {
        this.employeeSalary = employeeSalary;
    }
}
