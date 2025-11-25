package com.example.backend.presentation;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.application.EmployeeService;
import com.example.backend.application.EmployeeService.AllowanceResult;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeApplicationService;

    @GetMapping("/employees")
    public List<EmployeeResponse> getAll() {
        return employeeApplicationService.getEmployees()
                .stream()
                .map(EmployeeResponse::from)
                .toList();
    }

    @GetMapping("/employees/bonuses")
    public List<AllowanceResult> getBonuses(@RequestParam(name = "dept", required = false) Integer deptId) {
        var employees = employeeApplicationService.getEmployees();
        return employeeApplicationService.getAllowanceResults(employees, deptId, LocalDate.now());
    }

    @GetMapping("/employees/hello")
    public String hello() {
        return "Hello Spring";
    }

    @GetMapping("/employees/hello-json")
    public Map<String, String> helloJson() {
        return Map.of("message", "Hello Spring JSON");
    }
}
