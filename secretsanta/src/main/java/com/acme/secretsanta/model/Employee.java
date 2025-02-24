package com.acme.secretsanta.model;

import com.acme.secretsanta.util.UniqueEmailId;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Setter
@Getter
@Entity
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "Employee name is required")
    @JsonProperty("name")
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    @JsonProperty("email")
    @NotBlank(message = "Employee email is required")
    @Email(message = "Invalid email format")
    //@UniqueEmailId(message = "Email already exists")
    private String email;


    public Employee() {} // Required by JPA

    public Employee(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Employee(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
