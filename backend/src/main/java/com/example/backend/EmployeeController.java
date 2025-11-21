package com.example.backend;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
    @GetMapping("/employees/hello")
    public String hello() {
        return "Hello Spring";
    }

    @GetMapping("/employees/hello-json")
    public Map<String, String> helloJson() {
        return Map.of("message", "Hello Spring");
    }
}
