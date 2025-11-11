package com.myriamfournier.olympics_ticket_office.ws;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class ApiControllerUnitTest {

    @InjectMocks
    private ApiController apiController;

    @Test
    public void testApiControllerCreation() {
        // Simple test to verify the ApiController can be instantiated
        assertNotNull(apiController, "ApiController should not be null");
    }

    @Test
    public void testDefaultApiEndpoint() {
        // Act - Call the method being tested
        ResponseEntity<String> response = apiController.defaultApiEndpoint();

        // Assert - Verify the results
        assertNotNull(response, "Response should not be null");
        assertEquals(HttpStatus.OK, response.getStatusCode(), "Status code should be 200 OK");
        assertEquals("Welcome to the Olympics Ticket Office API!", response.getBody(), "Response body should match expected message");
    }

    @Test
    public void testDefaultApiEndpointResponseType() {
        // Act - Call the method being tested
        ResponseEntity<String> response = apiController.defaultApiEndpoint();

        // Assert - Verify response characteristics
        assertNotNull(response, "Response should not be null");
        assertNotNull(response.getBody(), "Response body should not be null");
        assertEquals(String.class, response.getBody().getClass(), "Response body should be a String");
    }

}
