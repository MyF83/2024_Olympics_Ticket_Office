package com.myriamfournier.olympics_ticket_office.service;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.doNothing;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.myriamfournier.olympics_ticket_office.repository.KeysgenerationRepository;
import com.myriamfournier.olympics_ticket_office.repository.UserRepository;
import com.myriamfournier.olympics_ticket_office.service.impl.KeysgenerationServiceImpl;
import com.myriamfournier.olympics_ticket_office.service.impl.UserServiceImpl;


/**
 * Unit tests for CartWs REST controller
 */
@ExtendWith(MockitoExtension.class)
public class KeysgenerationServiceUnitTest {

    // Mock the repository dependencies that KeysgenerationServiceImpl uses
    @Mock
    private KeysgenerationRepository keysgenerationRepository;



    // Inject the mocks into the service implementation we're testing
    @InjectMocks
    private KeysgenerationServiceImpl keysgenerationServiceImpl;

    @Test
    public void test() {
        // Arrange

        // Act

        // Assert
    }
}
