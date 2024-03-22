package com.example.jpademo.repository;

import com.example.jpademo.domain.entity.Employee;
import com.example.jpademo.repository.EmployeeRepository;
import com.example.jpademo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("EmployeeServiceJpaHibernate")
public class EmployeeServiceJpaImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceJpaImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int id) {
        return employeeRepository.findById(id);
    }

    @Override
    public List<Employee> findByName(String name) {
        return null;
    }

    @Override
    public void save(Employee employee) {
        Employee newEmployee = new Employee();
        newEmployee.setAge(employee.getAge());
        newEmployee.setSalary(employee.getSalary());
        newEmployee.setName(employee.getName());
        employeeRepository.save(newEmployee);
    }

    @Override
    public void update(Employee employee) {
        Employee newEmployee = new Employee();
        newEmployee.setAge(employee.getAge());
        newEmployee.setSalary(employee.getSalary());
        newEmployee.setName(employee.getName());
        newEmployee.setId(employee.getId());
        System.out.println("newEmployee " + newEmployee );
        employeeRepository.update(newEmployee);
    }

    @Override
    public void delete(int id) {
        employeeRepository.delete(id);
    }

    @Override
    public void deleteByName(String name) {
        // work to be done
    }

    @Override
    public List<Employee> findByMinAgeAndDepartmentName(int minAge, String deptName) {
       return employeeRepository.findEmployeeByMinAgeAndDepartment(minAge, deptName);
    }

}
