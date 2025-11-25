package com.example.backend.infrastructure.repository;

import com.example.backend.domain.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

interface EmployeeJpaRepository extends JpaRepository<Employee, Integer> {}
