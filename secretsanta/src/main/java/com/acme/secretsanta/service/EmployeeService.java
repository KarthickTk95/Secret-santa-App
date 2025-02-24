package com.acme.secretsanta.service;

import com.acme.secretsanta.model.Employee;
import com.acme.secretsanta.repository.EmployeeRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class EmployeeService {
    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

   /* public List<Employee> saveEmployees(List<Employee> employees) {
        return repository.saveAll(employees);
    }*/

    public List<Employee> saveEmployees(@Valid List<Employee> employees) {
        List<Employee> savedEmployees = repository.saveAll(employees);
        System.out.println("Saved Employees: " + savedEmployees.size());
        return savedEmployees;
    }

    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }
}

