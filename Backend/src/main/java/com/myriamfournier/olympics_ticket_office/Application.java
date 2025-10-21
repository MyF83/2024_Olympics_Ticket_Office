package com.myriamfournier.olympics_ticket_office;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.myriamfournier.olympics_ticket_office", "com.myriamfournier.QRCode"})
public class Application {

	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		
		System.err.println("Application started");

		
		}
	
		

}
