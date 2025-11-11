package com.myriamfournier.olympics_ticket_office.service.impl;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.myriamfournier.olympics_ticket_office.repository.PolicyRepository;


@ExtendWith(MockitoExtension.class)
public class PolicyServiceImplUnitTest {

    // Mock the repository dependencies that CartServiceImpl uses
    @Mock
    private PolicyRepository policyRepository;



    // Inject the mocks into the service implementation we're testing
    @InjectMocks
    private PolicyServiceImpl policyServiceImpl;

    @Test
    public void testGetAllPolicies() {
        // Arrange
        List<com.myriamfournier.olympics_ticket_office.pojo.policies> policiesList = Arrays.asList(new com.myriamfournier.olympics_ticket_office.pojo.policies(), new com.myriamfournier.olympics_ticket_office.pojo.policies());
        when(policyRepository.findAllPolicies()).thenReturn(policiesList);
        // Act
        List<com.myriamfournier.olympics_ticket_office.pojo.policies> result = policyServiceImpl.getAllPolicies();
        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
    }

        @Test
    public void testGetPolicyById() {
        // Arrange
        com.myriamfournier.olympics_ticket_office.pojo.policies policy = new com.myriamfournier.olympics_ticket_office.pojo.policies();
        when(policyRepository.findById(any())).thenReturn(Optional.of(policy));
        // Act
        com.myriamfournier.olympics_ticket_office.pojo.policies result = policyServiceImpl.getPolicyById(1L);
        // Assert
        assertNotNull(result);
        assertEquals(policy, result);
    }

        @Test
    public void testCreatePolicy() {
        // Arrange
        com.myriamfournier.olympics_ticket_office.pojo.policies policy = new com.myriamfournier.olympics_ticket_office.pojo.policies();
        when(policyRepository.save(any())).thenReturn(policy);
        // Act
        policyServiceImpl.createPolicy(policy);
        // Assert
        verify(policyRepository).save(policy);
    }

        @Test
    public void testUpdatePolicyById() {
        // Arrange
        com.myriamfournier.olympics_ticket_office.pojo.policies policy = new com.myriamfournier.olympics_ticket_office.pojo.policies();
        when(policyRepository.findById(1L)).thenReturn(Optional.of(policy)); 
        when(policyRepository.save(any())).thenReturn(policy);
        // Act
        policyServiceImpl.updatePolicyById(policy, 1L);
        // Assert
        verify(policyRepository).save(any());
    }

        @Test
    public void testDeletePolicyById() {
        // Arrange
        Long policyId = 1L;
        policyServiceImpl.deletePolicyById(policyId);
        // Act
        verify(policyRepository).deleteById(policyId);
        // Assert
        assertTrue(true);
    }
    

        @Test
    public void testSetActivePolicyById() {
        // Arrange
        com.myriamfournier.olympics_ticket_office.pojo.policies policy = new com.myriamfournier.olympics_ticket_office.pojo.policies();
        when(policyRepository.findById(1L)).thenReturn(Optional.of(policy)); 
        when(policyRepository.save(any())).thenReturn(policy);
        // Act
        policyServiceImpl.setActivePolicyById(1L, true);
        // Assert
        verify(policyRepository).save(any());
    }

        @Test
    public void testGetLastVersionById() {
        // Arrange
        com.myriamfournier.olympics_ticket_office.pojo.policies policy = new com.myriamfournier.olympics_ticket_office.pojo.policies();
        when(policyRepository.findLastVersionById(1L)).thenReturn(policy);
        // Act
        com.myriamfournier.olympics_ticket_office.pojo.policies result = policyServiceImpl.getLastVersionById(1L);
        // Assert
        assertNotNull(result);
        assertEquals(policy, result);
    }

        @Test
    public void testGetLastVersion() {
        // Arrange
        com.myriamfournier.olympics_ticket_office.pojo.policies policy = new com.myriamfournier.olympics_ticket_office.pojo.policies();
        when(policyRepository.findLastVersion()).thenReturn(policy);
        // Act
        com.myriamfournier.olympics_ticket_office.pojo.policies result = policyServiceImpl.getLastVersion();
        // Assert
        assertNotNull(result);
        assertEquals(policy, result);
    }

        @Test
    public void testGetActivePolicy() {
        // Arrange
        com.myriamfournier.olympics_ticket_office.pojo.policies policy = new com.myriamfournier.olympics_ticket_office.pojo.policies();
        when(policyRepository.findActivePolicy()).thenReturn(policy);
        // Act
        com.myriamfournier.olympics_ticket_office.pojo.policies result = policyServiceImpl.getActivePolicy();
        // Assert
        assertNotNull(result);
        assertEquals(policy, result);
    }

   

}
