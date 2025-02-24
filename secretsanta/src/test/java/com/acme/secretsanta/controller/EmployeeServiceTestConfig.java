package com.acme.secretsanta.controller;

import com.acme.secretsanta.service.EmployeeService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import static org.mockito.Mockito.mock;

@TestConfiguration
public class EmployeeServiceTestConfig {
    @Bean
    public EmployeeService employeeService() {
        return mock(EmployeeService.class);
    }
}

