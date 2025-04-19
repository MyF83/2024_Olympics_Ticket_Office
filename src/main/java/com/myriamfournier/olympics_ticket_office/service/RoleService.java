package com.myriamfournier.olympics_ticket_office.service;

import java.util.List;

import com.myriamfournier.olympics_ticket_office.pojo.roles;

public interface RoleService{

    List<roles> getAllRoles();

    roles getRoleById(Long id);

    void createRole(roles roles);

    void updateRoleById(roles roles, Long id);


    void deleteRoleById(Long id);


    


}
