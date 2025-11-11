package com.myriamfournier.olympics_ticket_office.ws;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.myriamfournier.olympics_ticket_office.pojo.policies;
import com.myriamfournier.olympics_ticket_office.service.PolicyService;

@ExtendWith(MockitoExtension.class)
public class PolicyWsUnitTest {

    @Mock
    private PolicyService policyService;

    @InjectMocks
    private PolicyWs policyWs;


    @Test
    public void testGetAllPolicies() {  
        // Arrange - Create mock policies list
        policies policy1 = new policies();
        policy1.setPolicy_id(1L);

        policies policy2 = new policies();
        policy2.setPolicy_id(2L);

        List<policies> mockPolicies = Arrays.asList(policy1, policy2);

        when(policyService.getAllPolicies()).thenReturn(mockPolicies);

        // Act - Call the method being tested
        List<policies> result = policyWs.getAllPolicies();

        // Assert - Verify the results
        assertNotNull(result, "Result should not be null");
        assertEquals(2, result.size(), "Result size should be 2");

        // Verify that the service method was called
        verify(policyService).getAllPolicies();
    }

    @Test
    public void testGetPolicyById() {
        // Arrange - Create a mock policy
        Long policyId = 1L;
        policies mockPolicy = new policies();
        mockPolicy.setPolicy_id(policyId);

        when(policyService.getPolicyById(policyId)).thenReturn(mockPolicy);

        // Act - Call the method being tested
        policies result = policyWs.getPolicyById(policyId);

        // Assert - Verify the results
        assertNotNull(result, "Result should not be null");
        assertEquals(policyId, result.getPolicy_id(), "Policy ID should match");

        // Verify that the service method was called
        verify(policyService).getPolicyById(policyId);
    }   


    @Test
    public void testCreatePolicy() {
        // Arrange - Create a new policy
        policies newPolicy = new policies();
        newPolicy.setPolicy_id(1L);

        // Act - Call the method being tested
        policyWs.createPolicy(newPolicy);

        // Assert - Verify that the service method was called
        verify(policyService).createPolicy(newPolicy);    
    }   

    @Test
    public void testUpdatePolicyById() {
        // Arrange - Create a policy to update
        Long policyId = 1L;
        policies updatePolicy = new policies();
        updatePolicy.setPolicy_id(policyId);

        // Act - Call the method being tested
        policyWs.updateRPolicyById(policyId, updatePolicy);

        // Assert - Verify that the service method was called
        verify(policyService).updatePolicyById(updatePolicy, policyId);
    }

    @Test
    public void testDeletePolicyById() {
        // Arrange - Create a mock policy ID
        Long policyId = 1L;

        // Act - Call the method being tested
        policyWs.deletePolicyById(policyId);

        // Assert - Verify that the service method was called
        verify(policyService).deletePolicyById(policyId);    
    }
}
