package com.acme.secretsanta.controller;

import com.acme.secretsanta.model.Employee;
import com.acme.secretsanta.model.SecretSantaAssignment;
import com.acme.secretsanta.service.EmployeeService;
import com.acme.secretsanta.service.SecretSantaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.Resource;


import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/secretsanta")
public class SecretSantaController {
    private final SecretSantaService secretSantaService;
    private final EmployeeService employeeService;

    public SecretSantaController(SecretSantaService secretSantaService, EmployeeService employeeService) {
        this.secretSantaService = secretSantaService;
        this.employeeService = employeeService;
    }

/*    @PostMapping("/generate")
    public List<SecretSantaAssignment> generateAssignments() {
        List<Employee> employees = employeeService.getAllEmployees();
        return secretSantaService.assignSecretSantas(employees);
    }*/

    /**
     * Generates Secret Santa assignments for all employees and provides a CSV file
     * containing the assignments for download. The assignments are generated such
     * that each employee is assigned a "Secret Santa" recipient, ensuring no
     * employee is assigned to themselves.
     *
     * The method performs the following steps:
     * 1. Retrieves all employees from the employee service.
     * 2. Creates Secret Santa assignments using the SecretSantaService.
     * 3. Generates a CSV file containing the assignments and provides it as a downloadable
     *    resource in the response.
     *
     * @return a ResponseEntity containing the CSV file as a downloadable resource.
     *         The response has appropriate headers to trigger a file download in the
     *         client browser.
     * @throws IOException if there are errors generating the CSV file.
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/generate")
    public ResponseEntity<Resource> generateAndDownloadAssignments() throws IOException {
        List<Employee> employees = employeeService.getAllEmployees();
        List<SecretSantaAssignment> assignments = secretSantaService.assignSecretSantas(employees);
        return secretSantaService.generateCSV(assignments);
    }


}
