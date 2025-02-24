package com.acme.secretsanta.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity
//@Data
@NoArgsConstructor
//@AllArgsConstructor
public class SecretSantaAssignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonManagedReference
    private Long id;

    @ManyToOne
    @JsonManagedReference
    private Employee giver;

    @ManyToOne
    @JsonManagedReference
    private Employee receiver;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getGiver() {
        return giver;
    }

    public void setGiver(Employee giver) {
        this.giver = giver;
    }

    public Employee getReceiver() {
        return receiver;
    }

    public void setReceiver(Employee receiver) {
        this.receiver = receiver;
    }

    public SecretSantaAssignment(Long id, Employee giver, Employee receiver) {
        this.id = id;
        this.giver = giver;
        this.receiver = receiver;
    }

}

