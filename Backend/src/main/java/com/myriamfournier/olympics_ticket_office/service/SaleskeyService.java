package com.myriamfournier.olympics_ticket_office.service;

import java.util.List;

import com.myriamfournier.olympics_ticket_office.pojo.saleskeys;

public interface SaleskeyService{

    List<saleskeys> getAllSaleskeys();
    List<saleskeys> getAllWithKeysgenerations();


    saleskeys getSaleskeyById(Long id);

    saleskeys createSalekey(Long sale_id);
    
    void createSaleskey(saleskeys saleskeys);

    void updateSaleskeyById(saleskeys saleskeys, Long id);

    void deleteSaleskeyById(Long id);
    
    String generateSaleKeyHash(Long sale_id);


    


}
