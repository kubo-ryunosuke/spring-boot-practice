package com.example.backend.infrastructure.repository;

import com.example.backend.domain.model.Employee;
import com.example.backend.domain.repository.EmployeeRepository;
import com.example.backend.infrastructure.translator.EmployeeTranslator;

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
        return jpaRepository.findById(id)
                .map(EmployeeTranslator::toDomain);
    }

    @Override
    public List<Employee> findAll() {
        return jpaRepository.findAll()
            .stream()
            .map(e -> EmployeeTranslator.toDomain(e))
            .toList();
    }

    @Override
    public Employee save(Employee employee) {
        // todo: どう扱うか検討
        throw new UnsupportedOperationException("EmployeeRepository.save は未実装です");
    }

    @Override
    public void deleteById(Integer id) {
        jpaRepository.deleteById(id);
    }
}
