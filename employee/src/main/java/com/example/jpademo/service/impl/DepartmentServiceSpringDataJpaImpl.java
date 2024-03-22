package com.example.jpademo.service.impl;

import com.example.jpademo.domain.entity.Department;
import com.example.jpademo.repository.DepartmentJpaRepository;
import com.example.jpademo.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("DepartmentServiceSpringDataJpa")
public class DepartmentServiceSpringDataJpaImpl implements DepartmentService {

    private final DepartmentJpaRepository departmentJpaRepository;

    @Autowired
    public DepartmentServiceSpringDataJpaImpl(DepartmentJpaRepository departmentJpaRepository) {
        this.departmentJpaRepository = departmentJpaRepository;
    }

    @Override
    public List<Department> findAll() {
        return departmentJpaRepository.findAll();
    }

    @Override
    @Transactional
    public void save(Department department) {
        departmentJpaRepository.save(department);
    }

    @Override
    @Transactional
    public void update(Department department) {
        departmentJpaRepository.save(department);
    }

    @Override
    @Transactional
    public void delete(int id) {
        departmentJpaRepository.deleteById(id);
    }

    @Override
    public Department findById(int id) {
        Optional<Department> res = departmentJpaRepository.findById(id);
        return res.orElseGet(() -> new Department("NotFound!"));
    }

}
