package com.myriamfournier.olympics_ticket_office.pojo;
import com.myriamfournier.olympics_ticket_office.pojo.offers;
import java.util.List;

public class MergeCartRequest {
    
    
    
    private Long userId;
    private List<offers> items;
    // getters and setters

   public MergeCartRequest() {
}

public MergeCartRequest(Long userId, List<offers> items) {
    this.userId = userId;   
    this.items = items;
    // Default constructor

    }

public Long getUserId() {
    return userId;
}

public List<offers> getItems() {
    return items;
}


}