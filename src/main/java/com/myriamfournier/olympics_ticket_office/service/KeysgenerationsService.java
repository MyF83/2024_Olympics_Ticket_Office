package com.myriamfournier.olympics_ticket_office.service;

import java.util.List;

import com.myriamfournier.olympics_ticket_office.pojo.keysgenerations;

public interface KeysgenerationsService{

    List<keysgenerations> getAllKeysgenerations();

    keysgenerations getKeysgenerationById(Long id);

    void createKeysgeneration(keysgenerations keysgenerations);

    void updateKeysgenerationById(keysgenerations keysgenerations, Long id);


    void deleteKeysgenerationById(Long id);


    


}
