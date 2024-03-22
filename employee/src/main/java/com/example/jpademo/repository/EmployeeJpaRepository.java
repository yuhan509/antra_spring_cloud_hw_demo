package com.example.jpademo.repository;

import com.example.jpademo.domain.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeJpaRepository extends JpaRepository<Employee, Integer> {

    @Query("SELECT e FROM Employee e WHERE e.name=:name")
    List<Employee> findEmployeesByName(String name);

    @Modifying // using @Modifying, updated/deleted number of entities is returned by spring data jpa
    @Query("delete Employee e where e.name=:name")
    int deleteEmployeeByName(String name);

}
