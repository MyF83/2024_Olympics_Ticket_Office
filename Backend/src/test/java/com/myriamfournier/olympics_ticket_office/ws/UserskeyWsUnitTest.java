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

import com.myriamfournier.olympics_ticket_office.pojo.userskeys;
import com.myriamfournier.olympics_ticket_office.pojo.keysgenerations;
import com.myriamfournier.olympics_ticket_office.service.UserskeyService;


@ExtendWith(MockitoExtension.class)
public class UserskeyWsUnitTest {


    @Mock
    private UserskeyService userskeyService;

    @InjectMocks
    private UserskeysWs userskeysWs;

    @Test
    public void testgetAllUserskeysDefault (){
        // Arrange - Create mock userskeys list
        userskeys userskey1 = new userskeys();
        userskey1.setUserkey_id(1L);

        
        userskeys userskey2 = new userskeys();
        userskey2.setUserkey_id(2L);

        
        List<userskeys> mockUserskeys = Arrays.asList(userskey1, userskey2);
        
        when(userskeyService.getAllUserskeys()).thenReturn(mockUserskeys);
        
        // Act - Call the method being tested
        List<userskeys> result = userskeysWs.getAllUserskeysDefault();
        
        // Assert - Verify the results
        assertNotNull(result, "Result should not be null");
        assertEquals(2, result.size(), "Result size should be 2");
        
        // Verify that the service method was called
        verify(userskeyService).getAllUserskeys();
       }

       @Test
    public void testgetAllWithKeysgenerations (){
        // Arrange - Create mock keysgenerations and userskeys list
        keysgenerations keygen1 = new keysgenerations();
        keygen1.setKey_id(1L);
        keygen1.setKeyGenerated("2024-generated-key-1");

        keysgenerations keygen2 = new keysgenerations();
        keygen2.setKey_id(2L);
        keygen2.setKeyGenerated("2024-generated-key-2");

        userskeys userskey1 = new userskeys();
        userskey1.setUserkey_id(1L);
        userskey1.setKeysgenerations(keygen1); // Use proper relationship

        userskeys userskey2 = new userskeys();
        userskey2.setUserkey_id(2L);
        userskey2.setKeysgenerations(keygen2); // Use proper relationship

        List<userskeys> mockUserskeys = Arrays.asList(userskey1, userskey2);

        when(userskeyService.getAllWithKeysgenerations()).thenReturn(mockUserskeys);

        // Act - Call the method being tested
        List<userskeys> result = userskeysWs.getAllWithKeysgenerations();

        // Assert - Verify the results
        assertNotNull(result, "Result should not be null");
        assertEquals(2, result.size(), "Result size should be 2");

        // Verify that the service method was called
        verify(userskeyService).getAllWithKeysgenerations();
    }

       @Test
    public void testgetUserskeyById (){
        // Arrange - Create a mock userskeys  
        userskeys userskey = new userskeys();
        userskey.setUserkey_id(1L);
        when(userskeyService.getUserskeyById(1L)).thenReturn(userskey);
        // Act - Call the method being tested
        userskeys result = userskeysWs.getUserskeyById(1L);
        // Assert - Verify the results
        assertNotNull(result, "Result should not be null");
        assertEquals(1L, result.getUserkey_id(), "Userkey ID should be 1L");
        // Verify that the service method was called
        verify(userskeyService).getUserskeyById(1L);

       }

       @Test
    public void testupdateUserskeyById (){
        // Arrange - Create a mock userskeys
        userskeys userskeyToUpdate = new userskeys();
        userskeyToUpdate.setUserkey_id(1L);
        // Act - Call the method being tested
        userskeysWs.updateUserskeyById(1L, userskeyToUpdate);
        // Assert - Verify that the service method was called
        verify(userskeyService).updateUserskeyById(userskeyToUpdate, 1L);

       }

       @Test
    public void testdeleteUserskeyById (){
        // Arrange - No specific arrangement needed for delete
        Long userkeyIdToDelete = 1L;
        // Act - Call the method being tested
        userskeysWs.deleteUserskeyById(userkeyIdToDelete);
        // Assert - Verify that the service method was called
        verify(userskeyService).deleteUserskeyById(userkeyIdToDelete);
       }
}
