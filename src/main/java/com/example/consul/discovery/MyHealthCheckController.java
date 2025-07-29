package com.example.consul.discovery;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyHealthCheckController {

    @GetMapping("/my-health-check")
    public String healthCheck() {
        return "good";
    }
}
