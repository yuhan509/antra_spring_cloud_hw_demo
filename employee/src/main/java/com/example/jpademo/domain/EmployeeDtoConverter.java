package com.example.jpademo.domain;

import com.example.jpademo.domain.dto.EmployeeDto;
import com.example.jpademo.domain.entity.Department;
import com.example.jpademo.domain.entity.Employee;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeDtoConverter {
    private final ModelMapper modelMapper;

    @Autowired
    public EmployeeDtoConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public EmployeeDto convertToDto(Employee employee) {
        if (employee == null) return null;
        EmployeeDto dto = modelMapper.map(employee, EmployeeDto.class);
        Department department = employee.getDepartment();
        if (department != null) {
            dto.setDepartment(department.getName());
        }
        return dto;
    }
}
