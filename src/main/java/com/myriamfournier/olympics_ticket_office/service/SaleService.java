package com.myriamfournier.olympics_ticket_office.service;

import java.util.List;

import com.myriamfournier.olympics_ticket_office.pojo.sales;

public interface SaleService{

    List<sales> getAllSales();

    sales getSaleById(Long id);

    void createSale(sales sales);

    void updateSaleById(sales sales, Long id);


    void deleteSaleById(Long id);


    


}
