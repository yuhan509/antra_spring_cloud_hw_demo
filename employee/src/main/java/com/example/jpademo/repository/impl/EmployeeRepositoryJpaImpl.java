package com.example.jpademo.repository.impl;

import com.example.jpademo.domain.entity.Department;
import com.example.jpademo.domain.entity.Employee;
import com.example.jpademo.repository.EmployeeRepository;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
public class EmployeeRepositoryJpaImpl implements EmployeeRepository {

    private final EntityManager entityManager;

    @Autowired
    public EmployeeRepositoryJpaImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Employee employee) {
        entityManager.persist(employee);
    }

    @Override
    public List<Employee> findAll() {
        // create query
        TypedQuery<Employee> theQuery = entityManager.createQuery("FROM Employee", Employee.class);
        // return query results
        return theQuery.getResultList();
    }

    @Override
    public Employee findById(int id) {
        return entityManager.find(Employee.class, id);
    }

    @Override
    public List<Employee> findEmployeeByMinAgeAndDepartment(int minAge, String departmentName) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);

        Root<Employee> employee = cq.from(Employee.class);
        Join<Employee, Department> departmentJoin = employee.join("department");
        Predicate minAgePredicate = cb.gt(employee.get("age"), minAge);
        Predicate deptNamePredicate = cb.equal(departmentJoin.get("name"), departmentName);
        cq.where(minAgePredicate, deptNamePredicate);

        TypedQuery<Employee> query = entityManager.createQuery(cq);
        return query.getResultList();
    }


    @Override
    @Transactional
    public void update(Employee employee) {
        System.out.println(employee);
        entityManager.merge(employee);
    }

    @Override
    @Transactional
    public void delete(int id) {
        Employee employee = entityManager.find(Employee.class, id);
        entityManager.remove(employee);
    }
}
