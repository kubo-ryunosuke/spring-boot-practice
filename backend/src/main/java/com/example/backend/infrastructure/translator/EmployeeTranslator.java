package com.example.backend.infrastructure.translator;

import com.example.backend.domain.model.Employee;
import com.example.backend.infrastructure.entity.EmployeeJpaEntity;

public class EmployeeTranslator {
    public static Employee toDomain(EmployeeJpaEntity employee) {
        return new Employee(
            employee.getId(),
            employee.getName(),
            employee.getJoinDate(),
            employee.getDepartmentId(),
            employee.getBaseAllowance()
        );
    }

    public static EmployeeJpaEntity toJpaEntity(Employee employee) {
        return new EmployeeJpaEntity(
            employee.getId(),
            employee.getName(),
            employee.getJoinDate(),
            employee.getDepartmentId(),
            employee.getBaseAllowance()
        );
    }
}
