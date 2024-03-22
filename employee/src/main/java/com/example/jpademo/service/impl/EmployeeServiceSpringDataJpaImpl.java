package com.example.jpademo.service.impl;

import com.example.jpademo.domain.entity.Employee;
import com.example.jpademo.repository.EmployeeJpaRepository;
import com.example.jpademo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service("EmployeeServiceSpringDataJpa")
public class EmployeeServiceSpringDataJpaImpl implements EmployeeService {

    private final EmployeeJpaRepository employeeJpaRepository;

    @Autowired
    public EmployeeServiceSpringDataJpaImpl(EmployeeJpaRepository employeeJpaRepository) {
        this.employeeJpaRepository = employeeJpaRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeJpaRepository.findAll();
    }

    @Override
    @Transactional
    public void save(Employee employee) {
        // ? link the department here?
        employeeJpaRepository.save(employee);
    }

    @Override
    @Transactional
    public void update(Employee employee) {
        // ? update and link the department
        employeeJpaRepository.save(employee);
    }

    @Override
    @Transactional
    public void delete(int id) {
        employeeJpaRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteByName(String name) {
        employeeJpaRepository.deleteEmployeeByName(name);
    }

    @Override
    public List<Employee> findByMinAgeAndDepartmentName(int minAge, String deptName) {
        return null;
    }

    @Override
    public Employee findById(int id) {
         Optional<Employee> res =  employeeJpaRepository.findById(id);
         return res.orElseGet(() -> new Employee("NoEmployeeFound!"));
    }

    @Override
    public List<Employee> findByName(String name) {
        return employeeJpaRepository.findEmployeesByName(name);
    }
}
