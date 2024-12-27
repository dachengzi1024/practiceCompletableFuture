package com.cxm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private List<EmployeeName> employeeName;
    private List<EmployeeAddresses> employeeAddresses;
    private List<EmployeePhones> employeePhones;


    public List<EmployeeName> getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(List<EmployeeName> employeeName) {
        this.employeeName = employeeName;
    }

    public List<EmployeeAddresses> getEmployeeAddresses() {
        return employeeAddresses;
    }

    public void setEmployeeAddresses(List<EmployeeAddresses> employeeAddresses) {
        this.employeeAddresses = employeeAddresses;
    }

    public List<EmployeePhones> getEmployeePhones() {
        return employeePhones;
    }

    public void setEmployeePhones(List<EmployeePhones> employeePhones) {
        this.employeePhones = employeePhones;
    }
}
