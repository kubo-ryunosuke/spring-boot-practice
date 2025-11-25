package com.example.backend.application;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.backend.domain.model.Employee;
import com.example.backend.domain.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public record AllowanceResult(String name, long years, int totalAmount) {
    }

    // 勤続手当単価
    private static final int ALLOWANCE_RATE_PER_YEAR = 10000;

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public List<AllowanceResult> getAllowanceResults(List<Employee> list, Integer deptId, LocalDate targetDate) {
        return list.stream()
                .filter(e -> {
                    if (deptId == null) {
                        return true;
                    }

                    var departmentId = Optional.ofNullable(e.getDepartmentId());
                    var targetDeptId = Optional.ofNullable(deptId);
                    return departmentId.equals(targetDeptId);
                })
                .map(e -> {
                    long years = ChronoUnit.YEARS.between(e.getJoinDate(), targetDate);
                    var baseAllowance = Optional.ofNullable(e.getBaseAllowance());
                    int amount = (int) years * ALLOWANCE_RATE_PER_YEAR + baseAllowance.orElse(0);
                    return new AllowanceResult(e.getName(), years, amount);
                })
                .toList();

    }
}
