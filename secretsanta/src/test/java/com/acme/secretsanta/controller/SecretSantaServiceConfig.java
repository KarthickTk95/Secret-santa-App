package com.acme.secretsanta.controller;

import com.acme.secretsanta.service.SecretSantaService;
import org.mockito.Mock;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import static org.mockito.Mockito.mock;

@TestConfiguration
public class SecretSantaServiceConfig {
    @Bean
    public SecretSantaService secretSantaService(){
        return mock(SecretSantaService.class);
    }
}
