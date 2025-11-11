package com.myriamfournier.olympics_ticket_office.configuration;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.myriamfournier.QRCode.QRCodeGenerator;

/**
 * Test class for QRCodeConfig
 * Tests the configuration Bean creation for QR code functionality
 */
public class QRCodeConfigTest {

    private QRCodeConfig qrCodeConfig;

    @BeforeEach
    void setUp() {
        qrCodeConfig = new QRCodeConfig();
    }

    @Test
    void testQRCodeGeneratorBeanCreation() {
        // Test that qrCodeGenerator bean can be created
        QRCodeGenerator qrCodeGenerator = qrCodeConfig.qrCodeGenerator();
        
        assertNotNull(qrCodeGenerator);
        assertTrue(qrCodeGenerator instanceof QRCodeGenerator);
    }

    @Test
    void testQRCodeGeneratorBeanIsNewInstance() {
        // Test that each call creates a new instance
        QRCodeGenerator generator1 = qrCodeConfig.qrCodeGenerator();
        QRCodeGenerator generator2 = qrCodeConfig.qrCodeGenerator();
        
        assertNotNull(generator1);
        assertNotNull(generator2);
        // Note: In Spring, beans are singletons by default, but since we're testing the method directly,
        // each call creates a new instance
        assertNotSame(generator1, generator2);
    }

    @Test
    void testConfigurationClassAnnotations() {
        // Test that the class has proper annotations
        assertTrue(qrCodeConfig.getClass().isAnnotationPresent(Configuration.class));
    }

    @Test
    void testBeanMethodAnnotation() throws NoSuchMethodException {
        // Test that bean method has @Bean annotation
        assertTrue(qrCodeConfig.getClass()
            .getMethod("qrCodeGenerator")
            .isAnnotationPresent(Bean.class));
    }

    @Test
    void testQRCodeGeneratorFunctionality() {
        // Test that the created bean has working functionality
        QRCodeGenerator generator = qrCodeConfig.qrCodeGenerator();
        
        // Test basic functionality
        String result = generator.getKeyAssembly("test", "key");
        assertEquals("test-key", result);
        
        String name = generator.generate40CharacterName();
        assertEquals(40, name.length());
        
        String ticketNumber = generator.generateTicketNumber("John", "Doe");
        assertTrue(ticketNumber.startsWith("JD-"));
    }

    @Test
    void testMultipleBeanInstances() {
        // Test creating multiple bean instances and their independence
        QRCodeGenerator generator1 = qrCodeConfig.qrCodeGenerator();
        QRCodeGenerator generator2 = qrCodeConfig.qrCodeGenerator();
        
        // Both should work independently
        String name1 = generator1.generate40CharacterName();
        String name2 = generator2.generate40CharacterName();
        
        assertEquals(40, name1.length());
        assertEquals(40, name2.length());
        assertNotEquals(name1, name2); // Should be different due to random generation
    }

    @Test
    void testConfigurationIntegrity() {
        // Test that configuration maintains integrity
        assertNotNull(qrCodeConfig);
        
        // Multiple calls should not affect the config object
        qrCodeConfig.qrCodeGenerator();
        qrCodeConfig.qrCodeGenerator();
        qrCodeConfig.qrCodeGenerator();
        
        // Config should still be working
        QRCodeGenerator generator = qrCodeConfig.qrCodeGenerator();
        assertNotNull(generator);
    }
}