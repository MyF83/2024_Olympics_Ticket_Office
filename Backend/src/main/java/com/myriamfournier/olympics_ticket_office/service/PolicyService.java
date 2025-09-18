package com.myriamfournier.olympics_ticket_office.service;

import java.util.List;

import com.myriamfournier.olympics_ticket_office.pojo.policies;

public interface PolicyService{

    List<policies> getAllPolicies();

    policies getPolicyById(Long id);

    void createPolicy(policies policies);

    void updatePolicyById(policies policies, Long id);

    void deletePolicyById(Long id);

   void setActivePolicyById(Long id, boolean isActive);

    policies getActivePolicy(); // Get the active policy

    policies getLastVersion(); // Get the last version of the policy

    policies getLastVersionById(Long id); // Get the last version of the policy by ID

}
