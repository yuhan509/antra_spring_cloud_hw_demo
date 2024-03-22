package com.example.jpademo.service.impl;

import com.example.jpademo.domain.entity.Department;
import com.example.jpademo.repository.DepartmentRepository;
import com.example.jpademo.service.DepartmentService;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("DepartmentServiceJpaHibernate")
public class DepartmentServiceJpaImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceJpaImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department findById(int id) {
        return departmentRepository.findById(id);
    }

    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    @Transactional
    public void save(Department department) {
        Department newDept = new Department();
        newDept.setName(department.getName());
//        System.out.println("department " + department);
//        System.out.println("new department " + newDept);
        departmentRepository.save(newDept);
    }

    @Override
    @Transactional
    public void update(Department department) {
        Department newDept = new Department();
        newDept.setName(department.getName());
        newDept.setId(department.getId());
        departmentRepository.update(newDept);
    }

    @Override
    @Transactional
    public void delete(int id) {
        departmentRepository.delete(id);
    }
}
