package com.acme.secretsanta.service;

import com.acme.secretsanta.model.Employee;
import com.acme.secretsanta.model.SecretSantaAssignment;
import com.acme.secretsanta.repository.SecretSantaRepository;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.*;
import java.util.*;

@Service
public class SecretSantaService {
    private final SecretSantaRepository repository;

    public SecretSantaService(SecretSantaRepository repository) {
        this.repository = repository;
    }

    public List<SecretSantaAssignment> assignSecretSantas(List<Employee> employees) {
        List<Employee> receivers = new ArrayList<>(employees);
        Collections.shuffle(receivers);

        Map<Employee, Employee> assignments = new HashMap<>();
        for (Employee giver : employees) {
            Employee receiver;
            do {
                receiver = receivers.get(new Random().nextInt(receivers.size()));
            } while (giver.equals(receiver) || assignments.containsValue(receiver));

            assignments.put(giver, receiver);
            receivers.remove(receiver);
        }

        List<SecretSantaAssignment> assignmentList = new ArrayList<>();
        for (Map.Entry<Employee, Employee> entry : assignments.entrySet()) {
            assignmentList.add(new SecretSantaAssignment(null, entry.getKey(), entry.getValue()));
        }

        return repository.saveAll(assignmentList);
    }


    public ResponseEntity<Resource> generateCSV(List<SecretSantaAssignment> assignments) throws IOException {
        System.out.println("generate csv ");
        String fileName = "secret_santa_assignments.csv";
        File file = new File(fileName);

        try (CSVWriter writer = new CSVWriter(new FileWriter(file))) {
            String[] header = {"Employee_Name", "Employee_EmailID", "Secret_Child_Name", "Secret_Child_EmailID"};
            writer.writeNext(header);

            for (SecretSantaAssignment assignment : assignments) {
                String[] row = {
                        assignment.getGiver().getName(),
                        assignment.getGiver().getEmail(),
                        assignment.getReceiver().getName(),
                        assignment.getReceiver().getEmail()
                };
                writer.writeNext(row);
            }
        }

        ByteArrayResource resource = new ByteArrayResource(java.nio.file.Files.readAllBytes(file.toPath()));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(resource);
    }
}

