package com.example.backend.presentation;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.application.EmployeeService;

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

    @GetMapping("/employees/hello")
    public String hello() {
        return "Hello Spring";
    }

    @GetMapping("/employees/hello-json")
    public Map<String, String> helloJson() {
        return Map.of("message", "Hello Spring JSON");
    }
}
