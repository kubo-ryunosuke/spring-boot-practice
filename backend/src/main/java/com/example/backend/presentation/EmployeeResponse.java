package com.example.backend.presentation;

import java.time.LocalDate;

import com.example.backend.domain.model.Employee;

public record EmployeeResponse(
        Integer id,
        String name,
        LocalDate joinDate,
        Integer departmentId,
        Integer baseAllowance) {

    public static EmployeeResponse from(Employee employee) {
        return new EmployeeResponse(
                employee.getId(),
                employee.getName(),
                employee.getJoinDate(),
                employee.getDepartmentId(),
                employee.getBaseAllowance());
    }
}
