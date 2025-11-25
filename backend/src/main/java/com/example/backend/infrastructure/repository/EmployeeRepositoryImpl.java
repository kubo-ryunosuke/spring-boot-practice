package com.example.backend.infrastructure.repository;

import com.example.backend.domain.model.Employee;
import com.example.backend.domain.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final EmployeeJpaRepository jpaRepository;

    @Override
    public Optional<Employee> findById(Integer id) {
        return jpaRepository.findById(id);
    }

    @Override
    public List<Employee> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public Employee save(Employee employee) {
        return jpaRepository.save(employee);
    }

    @Override
    public void deleteById(Integer id) {
        jpaRepository.deleteById(id);
    }
}
