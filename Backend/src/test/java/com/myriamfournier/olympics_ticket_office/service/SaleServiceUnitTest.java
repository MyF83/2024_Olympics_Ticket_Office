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

import com.myriamfournier.olympics_ticket_office.repository.SaleRepository;
import com.myriamfournier.olympics_ticket_office.repository.UserRepository;
import com.myriamfournier.olympics_ticket_office.service.impl.SaleServiceImpl;
import com.myriamfournier.olympics_ticket_office.service.impl.UserServiceImpl;



/**
 * Unit tests for CartWs REST controller
 */
@ExtendWith(MockitoExtension.class)
public class SaleServiceUnitTest {


    // Mock the repository dependencies that SaleServiceImpl uses
    @Mock
    private SaleRepository saleRepository;



    // Inject the mocks into the service implementation we're testing
    @InjectMocks
    private SaleServiceImpl saleServiceImpl;

    @Test
    public void test() {
        // Arrange

        // Act

        // Assert
    }
}
