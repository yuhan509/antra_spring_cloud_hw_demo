package com.example.jpademo.controller;

import com.example.jpademo.domain.EmployeeDtoConverter;
import com.example.jpademo.domain.dto.EmployeeDto;
import com.example.jpademo.domain.entity.Employee;
import com.example.jpademo.exception.DataNotFoundException;
import com.example.jpademo.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    private final EmployeeDtoConverter employeeDtoConverter;

    @Autowired
    public EmployeeController(@Qualifier("EmployeeServiceJpaHibernate") EmployeeService employeeService, EmployeeDtoConverter employeeDtoConverter) {
        this.employeeService = employeeService;
        this.employeeDtoConverter = employeeDtoConverter;
    }


    @GetMapping("")
    @Operation(summary = "get all employees")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get all employees")})
    public ResponseEntity<List<EmployeeDto>> getAll() {
        List<Employee> employees = employeeService.findAll();
        List<EmployeeDto> employeeDtos = employees.stream().map(employeeDtoConverter::convertToDto).collect(Collectors.toList());
        return new ResponseEntity<>(employeeDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getById(@PathVariable int id) {
        Employee employee = employeeService.findById(id);
        if (employee == null) throw new DataNotFoundException("No employee found that matches your specified id.");
        return new ResponseEntity<>(employeeDtoConverter.convertToDto(employee), HttpStatus.OK);
    }

    @GetMapping("/criteria")
    public ResponseEntity<List<EmployeeDto>> getById(@RequestParam int minAge, String departmentName) {
        List<Employee> employees = employeeService.findByMinAgeAndDepartmentName(minAge, departmentName);
        List<EmployeeDto> employeeDtos = employees.stream().map(employeeDtoConverter::convertToDto).collect(Collectors.toList());
        return new ResponseEntity<>(employeeDtos, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<EmployeeDto>> getByName(@RequestParam String name) {
        List<Employee> employees = employeeService.findByName(name);
        List<EmployeeDto> employeeDtos = employees.stream().map(employeeDtoConverter::convertToDto).collect(Collectors.toList());
        return new ResponseEntity<>(employeeDtos, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        employeeService.save(employee);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
        employeeService.update(employee);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int id) {
        employeeService.delete(id);
        return new ResponseEntity<>("Employee deleted", HttpStatus.OK);
    }

    @DeleteMapping("/")
    public ResponseEntity<String> deleteEmployeeByDepartmentId(@RequestParam String name) {
        employeeService.deleteByName(name);
        return new ResponseEntity<>("name:" + name + " deleted", HttpStatus.OK);
    }



}
