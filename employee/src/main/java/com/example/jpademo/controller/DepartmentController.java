package com.example.jpademo.controller;

import com.example.jpademo.domain.dto.DepartmentDto;
import com.example.jpademo.domain.entity.Department;
import com.example.jpademo.service.DepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(@Qualifier("DepartmentServiceJpaHibernate") DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    // get all department
    @GetMapping("")
    public ResponseEntity<List<Department>> getAll() {
        return new ResponseEntity<>(departmentService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getById(@PathVariable int id) {
        return new ResponseEntity<>(departmentService.findById(id), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Department> addDepartment(@RequestBody Department dept) {
        departmentService.save(dept);
        return new ResponseEntity<>(dept, HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<Department> updateDepartment(@RequestBody Department dept) {
        departmentService.update(dept);
        return new ResponseEntity<>(dept, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable int id) {
        departmentService.delete(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }


}
