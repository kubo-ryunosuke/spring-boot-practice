package com.example.backend.infrastructure.repository;

import com.example.backend.infrastructure.entity.EmployeeJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

interface EmployeeJpaRepository extends JpaRepository<EmployeeJpaEntity, Integer> {}
