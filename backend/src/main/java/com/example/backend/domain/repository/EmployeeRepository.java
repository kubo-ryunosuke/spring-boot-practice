package com.example.backend.domain.repository;

import com.example.backend.domain.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {
    Optional<Employee> findById(Integer id);

    List<Employee> findAll();

    Employee save(Employee employee);

    void deleteById(Integer id);
}
