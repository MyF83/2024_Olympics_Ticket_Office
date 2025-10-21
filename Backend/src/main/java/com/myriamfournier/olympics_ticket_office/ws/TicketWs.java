package com.myriamfournier.olympics_ticket_office.ws;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myriamfournier.olympics_ticket_office.pojo.carts;
import com.myriamfournier.olympics_ticket_office.pojo.events;
import com.myriamfournier.olympics_ticket_office.pojo.offers;
import com.myriamfournier.olympics_ticket_office.pojo.sales;
import com.myriamfournier.olympics_ticket_office.pojo.saleskeys;
import com.myriamfournier.olympics_ticket_office.pojo.tickets;
import com.myriamfournier.olympics_ticket_office.pojo.users;
import com.myriamfournier.olympics_ticket_office.pojo.userskeys;
import com.myriamfournier.olympics_ticket_office.service.CartService;
import com.myriamfournier.olympics_ticket_office.service.EventService;
import com.myriamfournier.olympics_ticket_office.service.OfferService;
import com.myriamfournier.olympics_ticket_office.service.QRCodeGeneratorService;
import com.myriamfournier.olympics_ticket_office.service.SaleService;
import com.myriamfournier.olympics_ticket_office.service.SaleskeyService;
import com.myriamfournier.olympics_ticket_office.service.TicketService;
import com.myriamfournier.olympics_ticket_office.service.UserService;
import com.myriamfournier.olympics_ticket_office.service.UserskeyService;

@RestController
@RequestMapping("/api/tickets")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TicketWs {

    @Autowired
    private TicketService ticketService;
    
    @Autowired
    private CartService cartService;
    
    @Autowired
    private SaleService saleService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private EventService eventService;
    
    @Autowired
    private OfferService offerService;
    
    @Autowired
    private UserskeyService userskeyService;
    
    @Autowired
    private SaleskeyService saleskeyService;
    
    @Autowired
    private QRCodeGeneratorService qrCodeGeneratorService;

    // Get all tickets
    @GetMapping
    public ResponseEntity<List<tickets>> getAllTickets() {
        try {
            List<tickets> ticketsList = ticketService.getAllTickets();
            return ResponseEntity.ok(ticketsList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // Get tickets with all details
    @GetMapping("/details")
    public ResponseEntity<List<tickets>> getAllTicketsWithDetails() {
        try {
            List<tickets> ticketsList = ticketService.getAllWithDetails();
            return ResponseEntity.ok(ticketsList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Get ticket by ID
    @GetMapping("/{id}")
    public ResponseEntity<tickets> getTicketById(@PathVariable Long id) {
        try {
            tickets ticket = ticketService.getTicketById(id);
            if (ticket != null) {
                return ResponseEntity.ok(ticket);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Create ticket (basic creation without full workflow)
    @PostMapping
    public ResponseEntity<tickets> createTicket(@RequestBody tickets ticket) {
        try {
            ticketService.createTicket(ticket);
            return ResponseEntity.status(HttpStatus.CREATED).body(ticket);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    
    // Complete ticket creation workflow after successful payment
    @PostMapping("/create-after-payment/{cartId}")
    public ResponseEntity<tickets> createTicketAfterPayment(@PathVariable Long cartId) {
        try {
            // 1. Get cart information
            carts cart = cartService.getCartById(cartId);
            if (cart == null) {
                return ResponseEntity.badRequest().build();
            }
            
            // 2. Get user information
            users user = cart.getUsers();
            if (user == null) {
                return ResponseEntity.badRequest().build();
            }
            
            // 3. Get event and offer information - using placeholder IDs for now
            // TODO: These should come from cart or be passed as parameters
            events event = null; // eventService.getEventById(eventId);
            offers offer = null; // offerService.getOfferById(offerId);
            
            if (event == null || offer == null) {
                return ResponseEntity.badRequest().build();
            }
            
            // 4. Create sale record
            sales sale = new sales();
            sale.setDate(new Timestamp(System.currentTimeMillis()));
            sale.setCarts(cart);
            saleService.createSale(sale);
            
            // 5. Generate user key and sale key
            userskeys userKey = userskeyService.createUserkey(user.getUser_id());
            saleskeys saleKey = saleskeyService.createSalekey(sale.getSale_id());
            
            // 6. Create keyAssembly (128 hex characters: 64 user + 64 sale)
            String userKeyHash = userskeyService.generateUserKeyHash(user.getUser_id());
            String saleKeyHash = saleskeyService.generateSaleKeyHash(sale.getSale_id());
            String keyAssembly = userKeyHash + saleKeyHash; // 128 hex characters total
            
            // 7. Generate QR code with comprehensive data
            String qrFileName = qrCodeGeneratorService.generateQRCodeWithTicketData(
                user, event, offer, cart, keyAssembly
            );
            
            // 8. Create ticket record
            tickets ticket = new tickets();
            ticket.setSales(sale);
            ticket.setDate(new Timestamp(System.currentTimeMillis()));
            ticket.setKeyAssembly(keyAssembly);
            ticket.setQrCodePath("QRCodes/" + qrFileName);
            ticket.setTicketNumber("TKT-" + System.currentTimeMillis()); // Generate unique ticket number
            ticket.setFileName(qrFileName);
            
            ticketService.createTicket(ticket);
            
            return ResponseEntity.status(HttpStatus.CREATED).body(ticket);
            
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Update ticket
    @PutMapping("/{id}")
    public ResponseEntity<tickets> updateTicket(@PathVariable Long id, @RequestBody tickets ticket) {
        try {
            tickets existingTicket = ticketService.getTicketById(id);
            if (existingTicket != null) {
                ticket.setTicket_id(id);
                ticketService.updateTicket(ticket);
                return ResponseEntity.ok(ticket);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // Delete ticket
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
        try {
            tickets existingTicket = ticketService.getTicketById(id);
            if (existingTicket != null) {
                ticketService.deleteTicketById(id);
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // Get tickets by user ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<tickets>> getTicketsByUserId(@PathVariable Long userId) {
        try {
            List<tickets> userTickets = ticketService.getTicketsByUserId(userId);
            return ResponseEntity.ok(userTickets);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // Get tickets by sale ID
    @GetMapping("/sale/{saleId}")
    public ResponseEntity<List<tickets>> getTicketsBySaleId(@PathVariable Long saleId) {
        try {
            List<tickets> saleTickets = ticketService.getTicketsBySaleId(saleId);
            return ResponseEntity.ok(saleTickets);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}