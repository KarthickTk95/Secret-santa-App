package com.acme.secretsanta.repository;

import com.acme.secretsanta.model.SecretSantaAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecretSantaRepository extends JpaRepository<SecretSantaAssignment, Long> {
}
