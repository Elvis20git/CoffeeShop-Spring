package com.Employee3.repository;

import com.Employee3.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {


    Optional<Employee> findByEmailAndPassword(String email, String password );
    Optional<Employee> findFirstByEmail(String email);
}
