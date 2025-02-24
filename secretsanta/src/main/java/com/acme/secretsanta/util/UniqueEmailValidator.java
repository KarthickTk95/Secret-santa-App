package com.acme.secretsanta.util;

import com.acme.secretsanta.repository.EmployeeRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmailId, String> {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void initialize(UniqueEmailId constraintAnnotation) {
        // Initialization logic (if needed)
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null) {
            return true; // Skip validation if email is null
        }
        return !employeeRepository.existsByEmail(email); // Return true if email is unique
    }
}
