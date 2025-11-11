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

import com.myriamfournier.olympics_ticket_office.repository.SaleskeyRepository;
import com.myriamfournier.olympics_ticket_office.repository.UserRepository;
import com.myriamfournier.olympics_ticket_office.service.impl.SaleskeyServiceImpl;
import com.myriamfournier.olympics_ticket_office.service.impl.UserServiceImpl;


/**
 * Unit tests for CartWs REST controller
 */
@ExtendWith(MockitoExtension.class)
public class SaleskeyServiceUnitTest {


    // Mock the repository dependencies that SaleskeyServiceImpl uses
    @Mock
    private SaleskeyRepository saleskeyRepository;



    // Inject the mocks into the service implementation we're testing
    @InjectMocks
    private SaleskeyServiceImpl saleskeyServiceImpl;

    @Test
    public void test() {
        // Arrange

        // Act

        // Assert
    }
}
