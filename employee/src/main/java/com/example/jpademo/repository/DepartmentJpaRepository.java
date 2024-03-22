package com.example.jpademo.repository;

import com.example.jpademo.domain.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentJpaRepository extends JpaRepository<Department, Integer> {
}
