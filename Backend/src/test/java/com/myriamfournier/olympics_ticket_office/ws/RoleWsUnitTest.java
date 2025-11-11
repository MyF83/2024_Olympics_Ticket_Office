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

import com.myriamfournier.olympics_ticket_office.pojo.roles;
import com.myriamfournier.olympics_ticket_office.service.RoleService;

@ExtendWith(MockitoExtension.class)
public class RoleWsUnitTest {

@Mock
    private RoleService roleService;

    @InjectMocks
    private RoleWs roleWs;

    @Test
    public void testGetAllRoles() {
        // Arrange - Create mock roles list
        roles role1 = new roles();
        role1.setRole_id(1L);

        roles role2 = new roles();
        role2.setRole_id(2L);

        List<roles> mockRoles = Arrays.asList(role1, role2);

        when(roleService.getAllRoles()).thenReturn(mockRoles);

        // Act - Call the method being tested
        List<roles> result = roleWs.getAllRoles();

        // Assert - Verify the results
        assertNotNull(result, "Result should not be null");
        assertEquals(2, result.size(), "Result size should be 2");

        // Verify that the service method was called
        verify(roleService).getAllRoles();
    }   

    @Test
    public void testGetRoleById() {
        // Arrange - Create a mock role
        Long roleId = 1L;
        roles mockRole = new roles();
        mockRole.setRole_id(roleId);

        when(roleService.getRoleById(roleId)).thenReturn(mockRole);

        // Act - Call the method being tested
        roles result = roleWs.getRoleById(roleId);

        // Assert - Verify the results
        assertNotNull(result, "Result should not be null");
        assertEquals(roleId, result.getRole_id(), "Role ID should match the requested ID");

        // Verify that the service method was called
        verify(roleService).getRoleById(roleId);    


    }

    @Test
    public void testCreateRole() {
        // Arrange - Create a mock role to be created
        roles newRole = new roles();
        newRole.setRole_id(1L);

        // Act - Call the method being tested
        roleWs.createRole(newRole);

        // Assert - Verify that the service method was called
        verify(roleService).createRole(newRole);    
    }   

    @Test
    public void testUpdateRoleById() {
        // Arrange - Create a mock role to be updated
        Long roleId = 1L;
        roles updateRole = new roles();
        updateRole.setRole_id(roleId);

        // Act - Call the method being tested
        roleWs.updateRoleById(roleId, updateRole);

        // Assert - Verify that the service method was called
        verify(roleService).updateRoleById(updateRole, roleId);

        // Verify that the role ID was set correctly
        assertEquals(roleId, updateRole.getRole_id(), "Updated Role ID should match");
    }
    
    @Test
    public void testDeleteRoleById() {  
        // Arrange - Create mock role ID to be deleted
        Long roleId = 1L;

        // Act - Call the method being tested
        roleWs.deleteRoleById(roleId);

        // Assert - Verify that the service method was called
        verify(roleService).deleteRoleById(roleId);
    }

}
