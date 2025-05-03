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

import com.myriamfournier.olympics_ticket_office.pojo.policies;
import com.myriamfournier.olympics_ticket_office.service.PolicyService;

@RequestMapping(ApiRegistration.API_REST
        + ApiRegistration.POLICY)
@RestController
public class PolicyWs {


    @Autowired
    private PolicyService policyService;



// /////////////////////////////////////////////// //
//       ALL GET METHODS FOR POLICY ENTITY        //
// ///////////////////////////////////////////// //


    //GET method to retrieve all policies
    // Example: GET /api/policy/all   
    // @GetMapping("all")
    @GetMapping
    public List<policies> getAllPolicies() {
        return policyService.getAllPolicies(); // Assuming you have a policyService to fetch all policies
    }  


    //GET method to retrieve a policy by ID
    // Example: GET /api/policy/{id}
    @GetMapping("{id}")
    /*@ResponseBody  */     
    public policies getPolicyById(@PathVariable("id") Long id) {
        return policyService.getPolicyById(id); // Assuming you have a policyService to fetch policy by ID
    }



// /////////////////////////////////////////////// //
//       ALL POST METHODS FOR POLICY ENTITY       //
// ///////////////////////////////////////////// //


    @PostMapping
    public void createPolicy(@RequestBody policies policies){
        policyService.createPolicy(policies);
    }



// /////////////////////////////////////////////// //
//       ALL PUT METHODS FOR POLICY ENTITY        //
// ///////////////////////////////////////////// //

    //PUT method to update an existing policy
    // Example: PUT /api/policy/update/{id}
    @PutMapping("{id}")
    public void updateRPolicyById(@PathVariable("id") Long id, @RequestBody policies policies) {
        policyService.updatePolicyById(policies, id); // Assuming you have a policyService to update policy by ID

    }



// /////////////////////////////////////////////// //
//       ALL DELETE METHODS FOR POLICY ENTITY     //
// ///////////////////////////////////////////// //

    @DeleteMapping("{id}")
    public void deletePolicyById(@PathVariable Long id){
        policyService.deletePolicyById(id);

    }


}
