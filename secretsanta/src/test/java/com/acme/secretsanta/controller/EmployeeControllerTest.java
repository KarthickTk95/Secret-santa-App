package com.acme.secretsanta.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.acme.secretsanta.repository.EmployeeRepository;
import com.acme.secretsanta.service.EmployeeService;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;

@ContextConfiguration(classes = {EmployeeController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
@Import({EmployeeServiceTestConfig.class})
class EmployeeControllerTest {
    @Autowired
    private EmployeeController employeeController;

    @Autowired
    private EmployeeService employeeService;

    @Test
    @DisplayName("Test getEmployees()")
    void testGetEmployees() throws Exception {
        // Arrange
        when(employeeService.getAllEmployees()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/employees");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(employeeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Test {@link EmployeeController#uploadEmployees(MultipartFile)}.
     * <p>
     * Method under test: {@link EmployeeController#uploadEmployees(MultipartFile)}
     */
    @Test
    @DisplayName("Test uploadEmployees(MultipartFile)")
    void testUploadEmployees() throws IOException {

        // Arrange
        EmployeeController employeeController = new EmployeeController(new EmployeeService(mock(EmployeeRepository.class)));

        // Act
        ResponseEntity<?> actualUploadEmployeesResult = employeeController.uploadEmployees(
                new MockMultipartFile(".csv", "foo.txt", "text/plain", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))));

        // Assert
        HttpStatusCode statusCode = actualUploadEmployeesResult.getStatusCode();
        assertTrue(statusCode instanceof HttpStatus);
        assertEquals("Invalid file type. Please upload a CSV file.", actualUploadEmployeesResult.getBody());
        assertEquals(400, actualUploadEmployeesResult.getStatusCodeValue());
        assertEquals(HttpStatus.BAD_REQUEST, statusCode);
        assertTrue(actualUploadEmployeesResult.hasBody());
        assertTrue(actualUploadEmployeesResult.getHeaders().isEmpty());
    }

    @Test
    @DisplayName("Test uploadEmployees(MultipartFile); then calls isEmpty()")
    void testUploadEmployees_thenCallsIsEmpty() {

        // Arrange
        EmployeeController employeeController = new EmployeeController(new EmployeeService(mock(EmployeeRepository.class)));
        MockMultipartFile file = mock(MockMultipartFile.class);
        when(file.isEmpty()).thenThrow(new IllegalArgumentException("Invalid file type. Please upload a CSV file."));

        // Act
        ResponseEntity<?> actualUploadEmployeesResult = employeeController.uploadEmployees(file);

        // Assert
        verify(file).isEmpty();
        HttpStatusCode statusCode = actualUploadEmployeesResult.getStatusCode();
        assertTrue(statusCode instanceof HttpStatus);
        assertEquals("Invalid file type. Please upload a CSV file.", actualUploadEmployeesResult.getBody());
        assertEquals(400, actualUploadEmployeesResult.getStatusCodeValue());
        assertEquals(HttpStatus.BAD_REQUEST, statusCode);
        assertTrue(actualUploadEmployeesResult.hasBody());
        assertTrue(actualUploadEmployeesResult.getHeaders().isEmpty());
    }

    @Test
    @DisplayName("Test uploadEmployees(MultipartFile); then return Body is 'File is empty. Please upload a valid CSV file.'")
    void testUploadEmployees_thenReturnBodyIsFileIsEmptyPleaseUploadAValidCsvFile() throws IOException {
        // Arrange
        EmployeeController employeeController = new EmployeeController(new EmployeeService(mock(EmployeeRepository.class)));

        // Act
        ResponseEntity<?> actualUploadEmployeesResult = employeeController
                .uploadEmployees(new MockMultipartFile("Name", new ByteArrayInputStream(new byte[]{})));

        // Assert
        HttpStatusCode statusCode = actualUploadEmployeesResult.getStatusCode();
        assertTrue(statusCode instanceof HttpStatus);
        assertEquals("File is empty. Please upload a valid CSV file.", actualUploadEmployeesResult.getBody());
        assertEquals(400, actualUploadEmployeesResult.getStatusCodeValue());
        assertEquals(HttpStatus.BAD_REQUEST, statusCode);
        assertTrue(actualUploadEmployeesResult.hasBody());
        assertTrue(actualUploadEmployeesResult.getHeaders().isEmpty());
    }

    @Test
    @DisplayName("Test uploadEmployees(MultipartFile); when ByteArrayInputStream(byte[]) with 'AXAXAXAX' Bytes is 'UTF-8'")
    void testUploadEmployees_whenByteArrayInputStreamWithAxaxaxaxBytesIsUtf8() throws IOException {
        // Arrange
        EmployeeController employeeController = new EmployeeController(new EmployeeService(mock(EmployeeRepository.class)));

        // Act
        ResponseEntity<?> actualUploadEmployeesResult = employeeController
                .uploadEmployees(new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))));

        // Assert
        HttpStatusCode statusCode = actualUploadEmployeesResult.getStatusCode();
        assertTrue(statusCode instanceof HttpStatus);
        assertEquals("Invalid file type. Please upload a CSV file.", actualUploadEmployeesResult.getBody());
        assertEquals(400, actualUploadEmployeesResult.getStatusCodeValue());
        assertEquals(HttpStatus.BAD_REQUEST, statusCode);
        assertTrue(actualUploadEmployeesResult.hasBody());
        assertTrue(actualUploadEmployeesResult.getHeaders().isEmpty());
    }
}
