package com.example.backend.presentation;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.domain.model.Employee;
import com.example.backend.domain.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeRepository repository;

    @GetMapping("/employees")
    public List<Employee> getAll() {
        return repository.findAll();
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
