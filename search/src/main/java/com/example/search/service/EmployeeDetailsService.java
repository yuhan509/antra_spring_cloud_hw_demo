package com.example.search.service;

import com.example.search.dto.DetailsDto;
import com.example.search.dto.EmployeeDto;

public interface EmployeeDetailsService {
    EmployeeDto[] getEmployees();

    DetailsDto getDetails();

    DetailsDto getEmployeesDetails();
}
