package com.example.search.controller;

import com.example.search.dto.DetailsDto;
import com.example.search.dto.EmployeeDto;
import com.example.search.service.EmployeeDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SearchController {

    private final EmployeeDetailsService employeeDetailsService;

    @Autowired
    public SearchController(EmployeeDetailsService employeeDetailsService) {
        this.employeeDetailsService = employeeDetailsService;
    }


    @GetMapping("/weather/search")
    public ResponseEntity<?> getSearch() {
        //TODO
        return new ResponseEntity<>("this is search service", HttpStatus.OK);
    }

    @GetMapping("weather/employees")
    public ResponseEntity<EmployeeDto[]> getEmployees() {
       return  new ResponseEntity<>(employeeDetailsService.getEmployees(), HttpStatus.OK);
    }

    @GetMapping("weather/details")
    public ResponseEntity<DetailsDto> getDetails() {
        return  new ResponseEntity<>(employeeDetailsService.getDetails(), HttpStatus.OK);
    }

    @GetMapping("weather/employees/details")
    public ResponseEntity<DetailsDto> getEmployeesDetails() {
        return  new ResponseEntity<>(employeeDetailsService.getEmployeesDetails(), HttpStatus.OK);
    }
}
