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
    private final TaxService taxService;

    public record AllowanceResult(String name, long years, int totalAmount) {
    }

    // 勤続手当単価
    private static final int ALLOWANCE_RATE_PER_YEAR = 10000;

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    // todo: 実装キモめ、AIレビュー挟む
    public List<AllowanceResult> getAllowanceResults(List<Employee> list, Integer deptId, LocalDate targetDate) {
        return list.stream()
                .filter(e -> {
                    return deptId == null
                            || (e.getDepartmentId() != null
                                    && e.getDepartmentId().equals(deptId));
                })
                .map(e -> {
                    long years = ChronoUnit.YEARS.between(e.getJoinDate(), targetDate);
                    var baseAllowance = Optional.ofNullable(e.getBaseAllowance());
                    int amount = (int) years * ALLOWANCE_RATE_PER_YEAR + baseAllowance.orElse(0);

                    int tax = taxService.calculateTax(amount);
                    System.out.println(e.getName() + "さんの税金: " + tax + "円");

                    return new AllowanceResult(e.getName(), years, amount);
                })
                .toList();

    }
}
