package com.example.jpademo.repository;

import com.example.jpademo.domain.entity.Department;
import javax.transaction.Transactional;

import java.util.List;

public interface DepartmentRepository {

    void save(Department department);

    List<Department> findAll();

    Department findById(int id);

    Department findByIdJoinFetch(int theId);

    void update(Department department);

    void delete(int id);

  //  int deleteAll();
}
