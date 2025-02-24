package com.acme.secretsanta.controller;

import static org.mockito.Mockito.when;

import com.acme.secretsanta.model.Employee;
import com.acme.secretsanta.model.SecretSantaAssignment;
import com.acme.secretsanta.service.EmployeeService;
import com.acme.secretsanta.service.SecretSantaService;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {SecretSantaController.class})
@ExtendWith(SpringExtension.class)
@Import({EmployeeServiceTestConfig.class, SecretSantaServiceConfig.class})
@DisabledInAotMode
class SecretSantaControllerTest {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private SecretSantaController secretSantaController;

    @Autowired
    private SecretSantaService secretSantaService;


    @Test
    @DisplayName("Test generateAndDownloadAssignments()")
    void testGenerateAndDownloadAssignments() throws Exception {
        // Arrange
        when(secretSantaService.assignSecretSantas(Mockito.<List<Employee>>any())).thenReturn(new ArrayList<>());
        when(secretSantaService.generateCSV(Mockito.<List<SecretSantaAssignment>>any()))
                .thenReturn(new ResponseEntity<>(HttpStatusCode.valueOf(200)));
        when(employeeService.getAllEmployees()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/secretsanta/generate");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(secretSantaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
