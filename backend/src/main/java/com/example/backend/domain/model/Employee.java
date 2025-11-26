package com.example.backend.domain.model;

import java.time.LocalDate;

import lombok.Getter;

@Getter
public class Employee {
    private Integer id;
    private String name;
    private LocalDate joinDate;
    private Integer departmentId;
    private Integer baseAllowance;

    public Employee(String name, LocalDate joinDate, Integer departmentId, Integer baseAllowance) {
        this.name = name;
        this.joinDate = joinDate;
        this.departmentId = departmentId;
        this.baseAllowance = baseAllowance;
    }

    public Employee(Integer id, String name, LocalDate joinDate, Integer departmentId, Integer baseAllowance) {
        this.id = id;
        this.name = name;
        this.joinDate = joinDate;
        this.departmentId = departmentId;
        this.baseAllowance = baseAllowance;
    }
}
