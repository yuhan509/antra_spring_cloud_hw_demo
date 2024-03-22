package com.example.jpademo.service;

import com.example.jpademo.domain.entity.Department;
import com.example.jpademo.domain.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    Employee findById(int id);

    List<Employee> findByName(String name);

    void save(Employee employee);

    void update(Employee employee);

    void delete(int id);

    void deleteByName(String name);

    List<Employee> findByMinAgeAndDepartmentName(int minAge, String deptName);
}
