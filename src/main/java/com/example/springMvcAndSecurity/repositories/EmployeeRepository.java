package com.example.springMvcAndSecurity.repositories;

import com.example.springMvcAndSecurity.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

}
