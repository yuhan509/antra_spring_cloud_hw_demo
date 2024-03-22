package com.example.jpademo.service;

import com.example.jpademo.domain.entity.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {

    List<Department> findAll();

    Department findById(int id);

    void save(Department department);

    void update(Department department);

    void delete(int id);


}
