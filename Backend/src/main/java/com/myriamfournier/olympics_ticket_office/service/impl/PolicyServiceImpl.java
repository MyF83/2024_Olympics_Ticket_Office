package com.myriamfournier.olympics_ticket_office.service.impl;

import java.util.List;
// import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myriamfournier.olympics_ticket_office.pojo.policies;
import com.myriamfournier.olympics_ticket_office.repository.PolicyRepository;
import com.myriamfournier.olympics_ticket_office.service.PolicyService;


@Service
public class PolicyServiceImpl implements PolicyService {
    // Implement the methods defined in PolicyService interface here
    // For example:
    // @Override
    // public Policy createPolicy(Policies policies) {
    //     // Implementation code here
    //     return null;
    // }

    
    @Autowired
    private PolicyRepository policyRepository; // Assuming you have a PolicyRepository interface

        
    @Override
    public List<policies> getAllPolicies() {
        return policyRepository.findAllPolicies();
    }


    @Override
    public policies getPolicyById(Long id) {
        return policyRepository.findById(id).orElse(null);
    }



    @Override
    public void createPolicy(policies policies) {
        // save the policy to the database
        policyRepository.save(policies);
    }

    
    @Override
    public void updatePolicyById(policies policies, Long id) {
            // (EN) A record is immutable,
            // (EN) impossible to modify.
            // (EN) Therefore, we must recover the element, modify it
            // (EN) put it back in base.
            // (FR) Un enregistement est immuable
            // (FR) impossible à modifier
            // (FR) De ce fait, on doit recuperer l'element, le modifier
            // (FR) le remettre en base.
            policies oldPolicy = getPolicyById(id);

        if(oldPolicy != null){
            oldPolicy.setTitle(policies.getTitle());
            oldPolicy.setDescription(policies.getDescription());
            oldPolicy.setCreationDate(policies.getCreationDate());
            oldPolicy.setVersion(policies.getVersion());
            policyRepository.save(oldPolicy);
        }
    }


    @Override
    public void deletePolicyById(Long id) {
        policyRepository.deleteById(id);
    }


}
