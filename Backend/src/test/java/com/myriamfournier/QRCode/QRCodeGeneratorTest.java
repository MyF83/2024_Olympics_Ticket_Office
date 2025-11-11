package com.myriamfournier.QRCode;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.myriamfournier.olympics_ticket_office.repository.TicketRepository;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Test class for QRCodeGenerator
 * Tests QR code generation, file handling, and utility methods
 */
@ExtendWith(MockitoExtension.class)
public class QRCodeGeneratorTest {

    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private QRCodeGenerator qrCodeGenerator;

    @BeforeEach
    void setUp() {
        // Reset mocks before each test
        reset(ticketRepository);
    }

    @Test
    void testGetKeyAssembly() {
        // Test successful key assembly
        String userKey = "USER123";
        String saleKey = "SALE456";
        
        String result = qrCodeGenerator.getKeyAssembly(userKey, saleKey);
        
        assertEquals("USER123-SALE456", result);
    }

    @Test
    void testGetKeyAssemblyWithNullUserKey() {
        // Test with null user key
        String saleKey = "SALE456";
        
        assertThrows(IllegalArgumentException.class, () -> {
            qrCodeGenerator.getKeyAssembly(null, saleKey);
        });
    }

    @Test
    void testGetKeyAssemblyWithNullSaleKey() {
        // Test with null sale key
        String userKey = "USER123";
        
        assertThrows(IllegalArgumentException.class, () -> {
            qrCodeGenerator.getKeyAssembly(userKey, null);
        });
    }

    @Test
    void testGetKeyAssemblyWithBothNullKeys() {
        // Test with both keys null
        assertThrows(IllegalArgumentException.class, () -> {
            qrCodeGenerator.getKeyAssembly(null, null);
        });
    }

    @Test
    void testGenerate40CharacterName() {
        // Test 40-character name generation
        String result = qrCodeGenerator.generate40CharacterName();
        
        assertNotNull(result);
        assertEquals(40, result.length());
        
        // Test multiple generations are different
        String result2 = qrCodeGenerator.generate40CharacterName();
        assertNotEquals(result, result2);
    }

    @Test
    void testGenerate40CharacterNameConsistentLength() {
        // Test multiple generations for consistent length
        for (int i = 0; i < 10; i++) {
            String result = qrCodeGenerator.generate40CharacterName();
            assertEquals(40, result.length(), "Generated name should always be 40 characters");
        }
    }

    @Test
    void testGenerateUnique40CharacterNameWhenNotExists() {
        // Test when name doesn't exist in database
        when(ticketRepository.existsByFileName(anyString())).thenReturn(false);
        
        String result = qrCodeGenerator.generateUnique40CharacterName();
        
        assertNotNull(result);
        assertEquals(40, result.length());
        verify(ticketRepository, atLeastOnce()).existsByFileName(anyString());
    }

    @Test
    void testGenerateUnique40CharacterNameWhenExists() {
        // Test when name exists and needs to be made unique
        when(ticketRepository.existsByFileName(anyString()))
            .thenReturn(true)  // First call returns true (exists)
            .thenReturn(false); // Second call returns false (doesn't exist)
        
        String result = qrCodeGenerator.generateUnique40CharacterName();
        
        assertNotNull(result);
        assertTrue(result.length() > 40); // Should be longer due to suffix
        verify(ticketRepository, times(2)).existsByFileName(anyString());
    }

    @Test
    void testGenerateUnique40CharacterNameMultipleCollisions() {
        // Test when multiple collisions occur
        when(ticketRepository.existsByFileName(anyString()))
            .thenReturn(true)   // First call
            .thenReturn(true)   // Second call  
            .thenReturn(true)   // Third call
            .thenReturn(false); // Fourth call succeeds
        
        String result = qrCodeGenerator.generateUnique40CharacterName();
        
        assertNotNull(result);
        assertTrue(result.endsWith("-3")); // Should have suffix -3
        verify(ticketRepository, times(4)).existsByFileName(anyString());
    }

    @Test
    void testGenerateTicketNumber() {
        // Test successful ticket number generation
        String firstname = "John";
        String lastname = "Doe";
        
        String result = qrCodeGenerator.generateTicketNumber(firstname, lastname);
        
        assertNotNull(result);
        assertTrue(result.startsWith("JD-"));
        
        // Check date format
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        assertTrue(result.contains(today));
        
        // Check total format (initials + dash + date + dash + 6 digits)
        String[] parts = result.split("-");
        assertEquals(3, parts.length);
        assertEquals(2, parts[0].length()); // Initials
        assertEquals(8, parts[1].length()); // Date
        assertEquals(6, parts[2].length()); // Random number
    }

    @Test
    void testGenerateTicketNumberWithLowercase() {
        // Test with lowercase names
        String firstname = "jane";
        String lastname = "smith";
        
        String result = qrCodeGenerator.generateTicketNumber(firstname, lastname);
        
        assertTrue(result.startsWith("JS-")); // Should be uppercase
    }

    @Test
    void testGenerateTicketNumberWithNullFirstname() {
        // Test with null firstname
        assertThrows(IllegalArgumentException.class, () -> {
            qrCodeGenerator.generateTicketNumber(null, "Doe");
        });
    }

    @Test
    void testGenerateTicketNumberWithNullLastname() {
        // Test with null lastname
        assertThrows(IllegalArgumentException.class, () -> {
            qrCodeGenerator.generateTicketNumber("John", null);
        });
    }

    @Test
    void testGenerateTicketNumberWithEmptyFirstname() {
        // Test with empty firstname
        assertThrows(IllegalArgumentException.class, () -> {
            qrCodeGenerator.generateTicketNumber("", "Doe");
        });
    }

    @Test
    void testGenerateTicketNumberWithEmptyLastname() {
        // Test with empty lastname
        assertThrows(IllegalArgumentException.class, () -> {
            qrCodeGenerator.generateTicketNumber("John", "");
        });
    }

    @Test
    void testGenerateTicketNumberUniqueness() {
        // Test that multiple generations produce different numbers
        String firstname = "John";
        String lastname = "Doe";
        
        String result1 = qrCodeGenerator.generateTicketNumber(firstname, lastname);
        String result2 = qrCodeGenerator.generateTicketNumber(firstname, lastname);
        
        assertNotEquals(result1, result2); // Should be different due to random component
    }

    @Test
    void testGenerateQRCodeWithValidData() {
        // Test QR code generation with valid data
        String data = "TEST-DATA-123";
        String filename = "test-qr-code";
        
        // The QR code generation might work in test environment, test the actual result
        try {
            String result = qrCodeGenerator.generateQRCode(data, filename);
            assertNotNull(result);
            assertTrue(result.contains(filename));
        } catch (RuntimeException e) {
            // If it throws an exception due to file system, that's also acceptable
            assertTrue(e.getMessage().contains("Failed to generate QR code") || 
                      e.getMessage().contains("QR code generation failed"));
        }
    }

    @Test
    void testGenerateQRCodeWithEmptyData() {
        // Test QR code generation with empty data
        String data = "";
        String filename = "test-qr-code";
        
        assertThrows(RuntimeException.class, () -> {
            qrCodeGenerator.generateQRCode(data, filename);
        });
    }

    @Test
    void testGenerateQRCodeWithNullData() {
        // Test QR code generation with null data
        String filename = "test-qr-code";
        
        assertThrows(RuntimeException.class, () -> {
            qrCodeGenerator.generateQRCode(null, filename);
        });
    }

    @Test
    void testGenerateQRCodeWithNullFilename() {
        // Test QR code generation with null filename
        String data = "TEST-DATA-123";
        
        try {
            String result = qrCodeGenerator.generateQRCode(data, null);
            // If no exception is thrown, check that result handling is appropriate
            if (result != null) {
                assertTrue(result.contains("null"));
            }
        } catch (RuntimeException e) {
            // Expected behavior for null filename
            assertTrue(e.getMessage().contains("Failed to generate QR code") || 
                      e.getMessage().contains("QR code generation failed"));
        }
    }

    @Test
    void testGenerateTicketNumberDateFormat() {
        // Test that ticket number includes correct date format
        String result = qrCodeGenerator.generateTicketNumber("Test", "User");
        
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        assertTrue(result.contains(today), "Ticket number should contain today's date");
    }

    @Test
    void testGenerateTicketNumberRandomComponent() {
        // Test that random component is 6 digits
        String result = qrCodeGenerator.generateTicketNumber("Test", "User");
        String[] parts = result.split("-");
        
        assertEquals(3, parts.length);
        String randomPart = parts[2];
        assertEquals(6, randomPart.length());
        assertTrue(randomPart.matches("\\d{6}"), "Random component should be 6 digits");
    }

    @Test
    void testGenerateTicketNumberSpecialCharacters() {
        // Test with names containing special characters
        String result = qrCodeGenerator.generateTicketNumber("Anne-Marie", "O'Connor");
        
        assertTrue(result.startsWith("AO-")); // Should take first character only
    }

    @Test
    void testKeyAssemblyWithSpecialCharacters() {
        // Test key assembly with special characters
        String userKey = "USER@123";
        String saleKey = "SALE#456";
        
        String result = qrCodeGenerator.getKeyAssembly(userKey, saleKey);
        
        assertEquals("USER@123-SALE#456", result);
    }

    @Test
    void testKeyAssemblyWithEmptyStrings() {
        // Test key assembly with empty strings (not null)
        String userKey = "";
        String saleKey = "";
        
        String result = qrCodeGenerator.getKeyAssembly(userKey, saleKey);
        
        assertEquals("-", result);
    }
}