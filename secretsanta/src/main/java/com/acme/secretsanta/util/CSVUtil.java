package com.acme.secretsanta.util;

import com.acme.secretsanta.model.Employee;
import org.apache.commons.csv.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVUtil {

  /*  public static List<Employee> parseCSV(MultipartFile file) throws IOException {
        List<Employee> employees = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());
            for (CSVRecord record : csvParser) {
                employees.add(new Employee(null, record.get("Employee_Name"), record.get("Employee_EmailID")));
            }
        }
        return employees;
    }*/

    public static List<Employee> parseCSV(MultipartFile file) throws IOException {
        // Validate file type
        if (!isCSVFile(file)) {
            throw new IllegalArgumentException("Invalid file type. Please upload a CSV file.");
        }

        List<Employee> employees = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());
            for (CSVRecord record : csvParser) {
                employees.add(new Employee(null, record.get("Employee_Name"), record.get("Employee_EmailID")));
            }
        }
        return employees;
    }

    // Helper method to validate file type
    private static boolean isCSVFile(MultipartFile file) {
        // Check MIME type or file extension
        String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();

        // Validate MIME type
        if (contentType != null && contentType.equals("text/csv")) {
            return true;
        }

        // Validate file extension
        return fileName != null && fileName.toLowerCase().endsWith(".csv");
    }
}
