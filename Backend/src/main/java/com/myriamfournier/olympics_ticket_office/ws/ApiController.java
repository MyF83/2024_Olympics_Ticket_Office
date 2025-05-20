package com.myriamfournier.olympics_ticket_office.ws;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;


@RequestMapping(ApiRegistration.API_REST)
@RestController
public class ApiController {
    @GetMapping
    public ResponseEntity<String> defaultApiEndpoint() {
        return ResponseEntity.ok("Welcome to the Olympics Ticket Office API!");
    }
}

