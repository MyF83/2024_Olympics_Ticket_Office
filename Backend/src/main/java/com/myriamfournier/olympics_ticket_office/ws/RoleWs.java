package com.myriamfournier.olympics_ticket_office.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myriamfournier.olympics_ticket_office.pojo.roles;
import com.myriamfournier.olympics_ticket_office.service.RoleService;

@RequestMapping(ApiRegistration.API_REST
        + ApiRegistration.ROLE)
@RestController
public class RoleWs {


    @Autowired
    private RoleService roleService;



// ///////////////////////////////////////////// //
//       ALL GET METHODS FOR ROLE ENTITY        //
// /////////////////////////////////////////// //


    //GET method to retrieve all roles
    // Example: GET /api/role/all   
    // @GetMapping("all")
    @GetMapping
    public List<roles> getAllRoles() {
        return roleService.getAllRoles(); // Assuming you have a roleService to fetch all roles
    }  


    //GET method to retrieve a role by ID
    // Example: GET /api/role/{id}
    @GetMapping("{id}")
    /*@ResponseBody  */     
    public roles getRoleById(@PathVariable("id") Long id) {
        return roleService.getRoleById(id); // Assuming you have a roleService to fetch role by ID
    }



// ///////////////////////////////////////////// //
//       ALL POST METHODS FOR ROLE ENTITY       //
// /////////////////////////////////////////// //


    @PostMapping
    public void createRole(@RequestBody roles roles){
            roleService.createRole(roles);
    }



// ///////////////////////////////////////////// //
//       ALL PUT METHODS FOR ROLE ENTITY        //
// /////////////////////////////////////////// //

    //PUT method to update an existing role
    // Example: PUT /api/role/update/{id}
    @PutMapping("{id}")
    public void updateRoleById(@PathVariable("id") Long id, @RequestBody roles roles) {
        roleService.updateRoleById(roles, id); // Assuming you have a roleService to update role by ID

    }



// ///////////////////////////////////////////// //
//       ALL DELETE METHODS FOR ROLE ENTITY     //
// /////////////////////////////////////////// //

    @DeleteMapping("{id}")
    public void deleteRoleById(@PathVariable Long id){
        roleService.deleteRoleById(id);

    }


}
