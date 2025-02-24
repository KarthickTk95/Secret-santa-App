package com.acme.secretsanta.util;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD }) // Applies to fields
@Retention(RetentionPolicy.RUNTIME) // Available at runtime
@Constraint(validatedBy = UniqueEmailValidator.class) // Links to the validator
public @interface UniqueEmailId {
    String message() default "Email already exists"; // Default error message

    Class<?>[] groups() default {}; // Validation groups

    Class<? extends Payload>[] payload() default {}; // Payload for metadata
}
