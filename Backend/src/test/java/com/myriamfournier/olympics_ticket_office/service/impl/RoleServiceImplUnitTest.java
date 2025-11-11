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

import com.myriamfournier.olympics_ticket_office.repository.RoleRepository;
import com.myriamfournier.olympics_ticket_office.pojo.roles;


@ExtendWith(MockitoExtension.class)
public class RoleServiceImplUnitTest {

    // Mock the repository dependencies that RoleServiceImpl uses
    @Mock
    private RoleRepository roleRepository;

    // Inject the mocks into the service implementation we're testing
    @InjectMocks
    private RoleServiceImpl roleServiceImpl;

    @Test
    public void testGetAllRoles() {
        // Arrange
        List<roles> expectedRoles = Arrays.asList(new roles(), new roles());
        when(roleRepository.findAllRoles()).thenReturn(expectedRoles);
        // Act
        List<roles> actualRoles = roleServiceImpl.getAllRoles();
        // Assert
        assertNotNull(actualRoles);
        assertEquals(expectedRoles.size(), actualRoles.size());
        assertTrue(actualRoles.containsAll(expectedRoles));
        verify(roleRepository).findAllRoles();
    }

    @Test
    public void testGetRoleById() {
        // Arrange
        roles role = new roles();
        when(roleRepository.findById(1L)).thenReturn(Optional.of(role));
        // Act
        roles actualRole = roleServiceImpl.getRoleById(1L);
        // Assert
        assertNotNull(actualRole);
        assertEquals(role, actualRole);
        verify(roleRepository).findById(1L);
    }

    @Test
    public void testCreateRole() {
        // Arrange
        roles role = new roles();
        // Act
        roleServiceImpl.createRole(role);
        // Assert
        verify(roleRepository).save(role);
    }

    @Test
    public void testUpdateRoleById() {
        // Arrange
        roles existingRole = new roles();
        roles updateRole = new roles();
        when(roleRepository.findById(1L)).thenReturn(Optional.of(existingRole));
        // Act
        roleServiceImpl.updateRoleById(updateRole, 1L);
        // Assert
        verify(roleRepository).findById(1L);
        verify(roleRepository).save(any());
    }

    @Test
    public void testDeleteRoleById() {
        // Arrange
        Long roleId = 1L;
        // Act
        roleServiceImpl.deleteRoleById(roleId);
        // Assert
        verify(roleRepository).deleteById(roleId);
    }

}
