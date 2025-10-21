package com.myriamfournier.olympics_ticket_office.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.myriamfournier.QRCode.QRCodeGenerator;

@Configuration
public class QRCodeConfig {
    
    @Bean
    public QRCodeGenerator qrCodeGenerator() {
        return new QRCodeGenerator();
    }
}