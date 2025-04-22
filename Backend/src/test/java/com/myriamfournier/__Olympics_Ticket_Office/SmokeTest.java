package com.myriamfournier.__Olympics_Ticket_Office;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.myriamfournier.olympics_ticket_office.Application;


// A smokeTest simply verify that the application context loads successfully
    // It does not test any functionality of the application, 
    // but it ensures that the application can start up without any issues.
@SpringBootTest(classes = Application.class) // This annontation is to execute the test in the context of the application

class SmokeTest {

	@Test
	void contextLoads() {
	}

}
