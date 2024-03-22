package com.example.jpademo.repository.impl;

import com.example.jpademo.domain.entity.Department;
import com.example.jpademo.repository.DepartmentRepository;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartmentRepositoryJpaImpl implements DepartmentRepository {

    private final EntityManager entityManager;

    @Autowired
    public DepartmentRepositoryJpaImpl(EntityManager em) {
        this.entityManager = em;
    }

    // implement save method
    @Override
    @Transactional
    public void save(Department department) {
        //System.out.println(department);
        entityManager.persist(department);
    }

    @Override
    public List<Department> findAll() {
        // create query
        TypedQuery<Department> theQuery = entityManager.createQuery("FROM Department", Department.class);
        // return query results
        return theQuery.getResultList();
    }

    @Override
    public Department findById(int id) {
        return entityManager.find(Department.class, id);
    }

    @Override
    public Department findByIdJoinFetch(int id) {

        // create query
        TypedQuery<Department> query = entityManager.createQuery(
                "select d from Department d "
                        + "JOIN FETCH d.employees "
                        + "where d.id = :data", Department.class);
        query.setParameter("data", id);
        // execute query
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void update(Department department) {
        entityManager.merge(department);
    }

    @Override
    @Transactional
    public void delete(int id) {
        // retrieve
        Department department = entityManager.find(Department.class, id);
        // delete
        entityManager.remove(department);
    }

//    @Override
//    @Transactional
//    public int deleteAll() {
//        int numRowsDeleted = entityManager.createQuery("DELETE FROM Department").executeUpdate();
//        return numRowsDeleted;
//    }

}
