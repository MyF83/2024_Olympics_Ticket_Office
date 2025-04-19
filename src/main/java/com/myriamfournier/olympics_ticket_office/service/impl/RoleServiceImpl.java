package com.myriamfournier.olympics_ticket_office.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myriamfournier.olympics_ticket_office.pojo.roles;
import com.myriamfournier.olympics_ticket_office.repository.RoleRepository;
import com.myriamfournier.olympics_ticket_office.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{

@Autowired
    private RoleRepository roleRepository; // Assuming you have a RoleRepository interface


    // Implement the methods defined in RoleService interface here
    
    @Override
    public List<roles> getAllRoles() {
        return roleRepository.findAllRoles();
    }


    @Override
    public roles getRoleById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }



    @Override
    public void createRole(roles roles) {
        roleRepository.save(roles);
    }


    @Override
    public void updateRoleById(roles roles, Long id) {
            // un enregistement est immuable
            // impossible à modifier
            // de ce fait, on doit recuperer l'element, le modifier
            // le remettre
            roles oldRole = getRoleById(id);

        if(oldRole != null){
            oldRole.setName(roles.getName());
            oldRole.setDescription(roles.getDescription());
            roleRepository.save(oldRole);
        }
    }




    @Override
    public void deleteRoleById(Long id) {
        roleRepository.deleteById(id);
    }

  
 

}
