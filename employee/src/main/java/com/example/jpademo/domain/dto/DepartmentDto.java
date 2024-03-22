package com.example.jpademo.domain.dto;

import com.example.jpademo.domain.entity.Department;
import com.example.jpademo.domain.entity.Employee;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DepartmentDto {

    private int id;
    private String name;
    private List<EmployeeDto> employees;


}
