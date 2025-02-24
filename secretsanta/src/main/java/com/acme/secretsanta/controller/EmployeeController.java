package com.acme.secretsanta.controller;

import com.acme.secretsanta.model.Employee;
import com.acme.secretsanta.service.EmployeeService;
import com.acme.secretsanta.util.CSVUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }


 /*   @PostMapping("/upload")
    public List<Employee> uploadEmployees(@RequestParam("file") MultipartFile file) throws IOException {
        List<Employee> employees = CSVUtil.parseCSV(file);
        // System.out.println("saved employees :  "  + savedEmployees);
        return service.saveEmployees(employees);
    }*/

    /**
     * Handles the upload of a CSV file containing employee data. The method parses
     * the CSV file, validates its contents, saves the employees, and returns the
     * saved employees or appropriate error responses.
     *
     * @param file the MultipartFile object representing the uploaded CSV file.
     *             It must not be empty and should have a valid CSV format.
     * @return a ResponseEntity containing one of the following:
     *         - The saved list of employees if the upload and save operations are successful.
     *         - An error message with HTTP status 400 (BAD_REQUEST) if the file is empty
     *           or contains invalid data.
     *         - An error message with HTTP status 500 (INTERNAL_SERVER_ERROR) if file
     *           processing fails due to I/O errors.
     */

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/upload")
    public ResponseEntity<?> uploadEmployees(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("File is empty. Please upload a valid CSV file.");
            }

            List<Employee> employees = CSVUtil.parseCSV(file);
            List<Employee> savedEmployees = service.saveEmployees(employees);
            return ResponseEntity.ok(savedEmployees);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to process the file: " + e.getMessage());
        }
    }


    /**
     * Retrieves a list of all employees.
     *
     * @return a list of Employee objects representing all employees present in the system.
     */
    @GetMapping
    public List<Employee> getEmployees() {
        return service.getAllEmployees();
    }
}

