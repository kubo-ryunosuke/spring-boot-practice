package com.example.backend.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.backend.application.TaxService;

@Configuration
public class AppConfig {
    
    @Bean
    TaxService taxService() {
        System.out.println("★ [Bean登録] TaxCalculator を生成しました");
        return new TaxService();
    }
}
