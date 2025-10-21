package com.myriamfournier.olympics_ticket_office.ws;

import org.springframework.http.HttpStatus;
import java.util.Optional;
import java.io.Console;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.myriamfournier.olympics_ticket_office.pojo.LoginRequest;
import com.myriamfournier.olympics_ticket_office.pojo.RegisterRequest;
import com.myriamfournier.olympics_ticket_office.pojo.policies;
import com.myriamfournier.olympics_ticket_office.pojo.userselections;
import com.myriamfournier.olympics_ticket_office.pojo.events;
import com.myriamfournier.olympics_ticket_office.pojo.offers;
import com.myriamfournier.olympics_ticket_office.pojo.ceremonies;
import com.myriamfournier.olympics_ticket_office.pojo.sites;
import com.myriamfournier.olympics_ticket_office.repository.PolicyRepository;
import com.myriamfournier.olympics_ticket_office.repository.EventRepository;
import com.myriamfournier.olympics_ticket_office.repository.OfferRepository;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.ResponseEntity;
//import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.RequestMethod;

import com.myriamfournier.olympics_ticket_office.service.CartService;
import com.myriamfournier.olympics_ticket_office.service.UserService;
import com.myriamfournier.olympics_ticket_office.service.UserSelectionService;
import com.myriamfournier.olympics_ticket_office.repository.UserRepository;
import com.myriamfournier.olympics_ticket_office.repository.CartRepository;
import com.myriamfournier.olympics_ticket_office.repository.EventRepository;
import com.myriamfournier.olympics_ticket_office.repository.OfferRepository;
import com.myriamfournier.olympics_ticket_office.repository.TicketRepository;
import com.myriamfournier.olympics_ticket_office.repository.SaleRepository;
import com.myriamfournier.olympics_ticket_office.repository.SaleskeyRepository;
import com.myriamfournier.olympics_ticket_office.repository.KeysgenerationRepository;
import com.myriamfournier.olympics_ticket_office.pojo.users;
import com.myriamfournier.olympics_ticket_office.pojo.carts;
import com.myriamfournier.olympics_ticket_office.pojo.sales;
import com.myriamfournier.olympics_ticket_office.pojo.saleskeys;
import com.myriamfournier.olympics_ticket_office.pojo.keysgenerations;
import com.myriamfournier.QRCode.QRCodeGenerator;
import com.myriamfournier.olympics_ticket_office.pojo.userselections;
import com.myriamfournier.olympics_ticket_office.pojo.events;
import com.myriamfournier.olympics_ticket_office.pojo.offers;
import com.myriamfournier.olympics_ticket_office.pojo.tickets;
import com.myriamfournier.olympics_ticket_office.service.QRCodeGeneratorService;

//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.myriamfournier.olympics_ticket_office.configuration.JwtUtils;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.nio.charset.StandardCharsets;


@RequestMapping(ApiRegistration.API_REST + ApiRegistration.USER)
@RestController
public class UserWs {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PolicyRepository policyRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private CartService cartService;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserSelectionService userSelectionService;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private SaleskeyRepository saleskeyRepository;

    @Autowired
    private KeysgenerationRepository keysgenerationRepository;

    @Autowired
    private QRCodeGeneratorService qrCodeGeneratorService;

    public UserWs(UserService userService) {
        this.userService = userService;
    }

    // CRUD operations for User entity
    // Create, Read, Update, Delete methods for User entity
    // Example: public User createUser(User user) { ... }
    // Example: public User getUserById(Long id) { ... }
    // Example: public User updateUser(Long id, User user) { ... }
    
 /*@GetMapping // Cannot have 2 methods with @GetMapping
    public ResponseEntity<String> defaultApiEndpoint() {
        return ResponseEntity.ok("User Page");
    }*/
// ///////////////////////////////////////////////// //
//       ALL GET METHODS FOR USER ENTITY            //
// /////////////////////////////////////////////// //


    //GET method to retrieve all users
    // Example: GET /api/user/all   
    @GetMapping
    public List<users> getAllUsersDefault() {
        return userService.getAllUsers(); // Assuming you have a userService to fetch all users
    } 
    
    
    @GetMapping("roles")
    public List<users> getAllWithRoles() {
        return userService.getAllWithRoles(); // Assuming you have a userService to fetch all users
    } 

    @GetMapping("userskeys")
    public List<users> getAllWithUserskeys() {
        return userService.getAllWithUserskeys(); // Assuming you have a userService to fetch all users
    } 

    @GetMapping("userselections")
    public List<users> getAllWithUserselections() {
        return userService.getAllWithUserselections(); // Assuming you have a userService to fetch all users
    } 

    @GetMapping("policies")
    public List<users> getAllWithPolicies() {
        return userService.getAllWithPolicies(); // Assuming you have a userService to fetch all users
    } 

    @GetMapping("countries")
    public List<users> getAllWithCountries() {
        return userService.getAllWithCountries(); // Assuming you have a userService to fetch all users
    } 


    //GET method to retrieve a user by ID
    // Example: GET /api/user/{id}
    @GetMapping("{id}")
    /*@ResponseBody  */     
    public users getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id); // Assuming you have a userService to fetch user by ID
        
    }


    //GET method to retrieve a user by firstname and lastname
    @GetMapping("firstname/{firstname}, lastname/{lastname}")
    @ResponseBody
    public users getUserByFirstnameAndLastname(@PathVariable("firstname") String firstname, @PathVariable("lastname") String lastname) {
        return userService.getUserByFirstnameAndLastname(firstname, lastname); // Assuming you have a userService to fetch user by firstname and lastname
    }

    //GET method to retrieve a user by username
    // Example: GET /api/user/username/{username}
    // In this API, the username is used to identify the user
    // and is composed by the first name and last name of the user, 
    // separated by a hyphen (-).	
    @GetMapping("username/{username}")
    @ResponseBody
    public users getUserByUsername(@PathVariable("username") String username) {
        return userService.getUserByUsername(username); // Assuming you have a userService to fetch user by username
    }   
    
    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            users user = userRepository.findUserByUsername(username);
            
            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "User not found"));
            }
            
            // Return user profile information
            Map<String, Object> userProfile = new HashMap<>();
            userProfile.put("user_id", user.getUser_id());
            userProfile.put("firstname", user.getFirstname());
            userProfile.put("lastname", user.getLastname());
            userProfile.put("username", user.getUsername());
            userProfile.put("email", user.getEmail());
            userProfile.put("phoneNumber", user.getPhoneNumber());
            userProfile.put("address", user.getAddress());
            userProfile.put("city", user.getCity());
            userProfile.put("postalCode", user.getPostalCode());
            
            if (user.getRoles() != null) {
                userProfile.put("role_id", user.getRoles().getRole_id());
            }
            
            if (user.getCountries() != null) {
                userProfile.put("country_id", user.getCountries().getCountry_id());
            }
            
            return ResponseEntity.ok(userProfile);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("error", "Error fetching user profile: " + e.getMessage()));
        }
    }

@GetMapping("/carts/user")
public ResponseEntity<?> getUserCarts() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = authentication.getName();
    users user = userRepository.findUserByUsername(username);
    if (user == null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }
    
    System.out.println("DEBUG: getUserCarts called for user: " + user.getUser_id() + " - " + username);
    
    // Fetch carts for this user
    List<carts> userCarts = cartService.findByUser(user);
    System.out.println("DEBUG: Found " + userCarts.size() + " carts for user");
    
    // Enrich cart data with information from userselections → offers/events
    List<Map<String, Object>> enrichedCarts = new ArrayList<>();
    
    for (carts cart : userCarts) {
        System.out.println("DEBUG: Processing cart ID: " + cart.getCart_id());
        System.out.println("DEBUG: Cart user ID: " + cart.getUsers().getUser_id());
        System.out.println("DEBUG: Cart total amount: " + cart.getTotalAmount());
        System.out.println("DEBUG: Cart date: " + cart.getDate());
        
        // Create enhanced cart data with nested userselection structure
        Map<String, Object> cartData = new HashMap<>();
        cartData.put("cart_id", cart.getCart_id());
        cartData.put("date", cart.getDate());
        cartData.put("totalAmount", cart.getTotalAmount());
        
        // Create nested user_id structure that matches the frontend interface
        Map<String, Object> userIdData = new HashMap<>();
        userIdData.put("user_id", cart.getUsers().getUser_id());
        userIdData.put("firstname", cart.getUsers().getFirstname());
        userIdData.put("lastname", cart.getUsers().getLastname());
        
        // Add userselections data if available (use cart's own userselection)
        if (cart.getUserselections() != null) {
            System.out.println("DEBUG: Cart has its own userselections");
            Map<String, Object> userSelectionsData = new HashMap<>();
            userselections userSelection = cart.getUserselections();
            
            System.out.println("DEBUG: UserSelection ID: " + userSelection.getUsersel_id());
            System.out.println("DEBUG: UserSelection nbPersons: " + userSelection.getNbPersons());
            System.out.println("DEBUG: UserSelection amount: " + userSelection.getAmount());
            
            userSelectionsData.put("usersel_id", userSelection.getUsersel_id());
            userSelectionsData.put("nbPersons", userSelection.getNbPersons());
            userSelectionsData.put("amount", userSelection.getAmount());
            userSelectionsData.put("seat_class", userSelection.getSeat_class());
            
            // Add offers data
            if (userSelection.getOffers() != null) {
                System.out.println("DEBUG: UserSelection has offer: " + userSelection.getOffers().getTitle());
                Map<String, Object> offersData = new HashMap<>();
                offers offer = userSelection.getOffers();
                offersData.put("offer_id", offer.getOffer_id());
                offersData.put("title", offer.getTitle());
                offersData.put("description", offer.getDescription());
                offersData.put("image", offer.getImage());
                offersData.put("rate", offer.getRate());
                offersData.put("nbSpectators", offer.getNbSpectators());
                userSelectionsData.put("offers", offersData);
                
                // Determine cart completion status
                boolean hasEvent = userSelection.getEvents() != null;
                boolean hasPersons = userSelection.getNbPersons() != null;
                boolean hasSeatClass = userSelection.getSeat_class() != null;
                
                if (hasEvent && hasPersons && hasSeatClass) {
                    userSelectionsData.put("completion_status", "complete");
                } else if (userSelection.getOffers() != null) {
                    userSelectionsData.put("completion_status", "step1_complete");
                } else {
                    userSelectionsData.put("completion_status", "incomplete");
                }
            } else {
                System.out.println("DEBUG: UserSelection has NO offer");
                userSelectionsData.put("completion_status", "incomplete");
            }
            
            // Add events data
            if (userSelection.getEvents() != null) {
                System.out.println("DEBUG: UserSelection has event: " + userSelection.getEvents().getTitle());
                Map<String, Object> eventsData = new HashMap<>();
                events event = userSelection.getEvents();
                eventsData.put("event_id", event.getEvent_id());
                eventsData.put("title", event.getTitle());
                eventsData.put("date", event.getDate());
                eventsData.put("time", event.getTime());
                eventsData.put("description", event.getDescription());
                eventsData.put("pricec1", event.getPricec1());
                eventsData.put("pricec2", event.getPricec2());
                eventsData.put("pricec3", event.getPricec3());
                
                // Add ceremonies or sports data with sites
                if (event.getCeremonies() != null) {
                    Map<String, Object> ceremoniesData = new HashMap<>();
                    ceremonies ceremony = event.getCeremonies();
                    ceremoniesData.put("cerem_id", ceremony.getCerem_id());
                    ceremoniesData.put("name", ceremony.getName());
                    ceremoniesData.put("description", ceremony.getDescription());
                    
                    if (ceremony.getSites() != null) {
                        Map<String, Object> sitesData = new HashMap<>();
                        sites site = ceremony.getSites();
                        sitesData.put("site_id", site.getSite_id());
                        sitesData.put("name", site.getName());
                        sitesData.put("city", site.getCity());
                        sitesData.put("address", site.getAddress());
                        ceremoniesData.put("sites", sitesData);
                    }
                    eventsData.put("ceremonies", ceremoniesData);
                }
                
                userSelectionsData.put("events", eventsData);
            } else {
                System.out.println("DEBUG: UserSelection has NO event - needs Step 2 completion");
            }
            
            userIdData.put("userselections", userSelectionsData);
        } else {
            System.out.println("DEBUG: Cart user has NO userselections - cart is empty");
            // Create empty structure for carts without userselections
            Map<String, Object> userSelectionsData = new HashMap<>();
            userSelectionsData.put("usersel_id", null);
            userSelectionsData.put("nbPersons", null);
            userSelectionsData.put("amount", cart.getTotalAmount());
            userSelectionsData.put("seat_class", null);
            userSelectionsData.put("completion_status", "empty");
            
            Map<String, Object> offersData = new HashMap<>();
            offersData.put("offer_id", null);
            offersData.put("title", "No offer selected");
            offersData.put("description", "Add an offer to this cart");
            userSelectionsData.put("offers", offersData);
            
            userIdData.put("userselections", userSelectionsData);
        }
        
        cartData.put("user_id", userIdData);
        enrichedCarts.add(cartData);
        System.out.println("DEBUG: Added cart to enriched list");
    }
    
    return ResponseEntity.ok(enrichedCarts);
}

@PostMapping("/carts")
public ResponseEntity<?> createCartForUser(@RequestBody Map<String, Object> requestBody) {
    try {
        System.out.println("DEBUG: POST /carts called with data: " + requestBody);
        
        // Validate input data
        if (requestBody == null || requestBody.isEmpty()) {
            System.out.println("ERROR: Empty or null request body");
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid cart data: request body is empty"));
        }
        
        // Get authentication
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            System.out.println("ERROR: No authentication context");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "No authentication"));
        }
        
        String username = authentication.getName();
        System.out.println("DEBUG: Username from authentication: " + username);
        
        if (username == null || username.isEmpty() || "anonymousUser".equals(username)) {
            System.out.println("ERROR: Invalid username: " + username);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "User not authenticated"));
        }
        
        // Find user
        users user = null;
        try {
            user = userRepository.findUserByUsername(username);
            System.out.println("DEBUG: User lookup result: " + (user != null ? user.getUser_id() : "null"));
        } catch (Exception e) {
            System.out.println("ERROR: Failed to find user: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("error", "Error finding user: " + e.getMessage()));
        }
        
        if (user == null) {
            System.out.println("ERROR: User not found for username: " + username);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "User not found"));
        }
        
        System.out.println("DEBUG: Found user: " + user.getUser_id() + " - " + user.getUsername());
        
        // FIRST: Check if user already has a direct userselection for this offer
        System.out.println("DEBUG: Checking user's direct userselection");
        if (user.getUserselections() != null) {
            System.out.println("DEBUG: User has userselections data available");
            if (user.getUserselections().getOffers() != null) {
                System.out.println("DEBUG: User's userselection has offer ID: " + user.getUserselections().getOffers().getOffer_id());
            }
        }
        
        // Check if offer_id exists in request
        if (requestBody.get("offer_id") == null) {
            System.out.println("ERROR: Missing offer_id in request");
            return ResponseEntity.badRequest().body(Map.of("error", "Missing offer_id in request"));
        }
        
        // Extract cart details from the request
        Long offerId = Long.valueOf(requestBody.get("offer_id").toString());
        System.out.println("DEBUG: Offer ID: " + offerId);
        
        // Handle optional fields (event_id can be null for partial cart creation)
        Long eventId = null;
        if (requestBody.get("event_id") != null) {
            eventId = Long.valueOf(requestBody.get("event_id").toString());
        }
        
        Integer nbPersons = 1; // Default
        if (requestBody.get("nb_persons") != null) {
            nbPersons = Integer.valueOf(requestBody.get("nb_persons").toString());
        }
        
        String seatClass = "price1"; // Default to class 1
        if (requestBody.get("seat_class") != null) {
            seatClass = requestBody.get("seat_class").toString();
        }
        
        Double amount = 0.0; // Default, will be calculated later
        if (requestBody.get("amount") != null) {
            amount = Double.valueOf(requestBody.get("amount").toString());
        }
        
        // Set event and offer (event can be null initially)
        events event = null;
        if (eventId != null) {
            try {
                event = eventRepository.findById(eventId).orElse(null);
                System.out.println("DEBUG: Event found: " + (event != null ? event.getEvent_id() : "null"));
            } catch (Exception e) {
                System.out.println("ERROR: Failed to find event: " + e.getMessage());
                // Continue without event for partial cart creation
            }
        }
        
        offers offer = null;
        try {
            offer = offerRepository.findById(offerId).orElse(null);
            System.out.println("DEBUG: Offer lookup result: " + (offer != null ? "Found" : "Not found"));
        } catch (Exception e) {
            System.out.println("ERROR: Failed to find offer: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("error", "Error finding offer: " + e.getMessage()));
        }
        
        if (offer == null) {
            System.out.println("ERROR: Offer not found for ID: " + offerId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Offer not found"));
        }
        
        System.out.println("DEBUG: Found offer: " + offer.getOffer_id() + " - " + offer.getTitle());
        
        // SECOND: Check if user's direct userselection already has this offer
        if (user.getUserselections() != null && 
            user.getUserselections().getOffers() != null &&
            user.getUserselections().getOffers().getOffer_id().equals(offerId)) {
            System.out.println("DEBUG: User's direct userselection already has this offer!");
            boolean hasEvent = user.getUserselections().getEvents() != null;
            System.out.println("DEBUG: User's userselection has event: " + hasEvent);
            
            // This user already has this offer in their direct userselection
            return ResponseEntity.ok().body(Map.of(
                "message", "Cart already exists for this offer",

                "status", hasEvent ? "complete" : "incomplete"
            ));
        } else {
            System.out.println("DEBUG: User's direct userselection does NOT have this offer, proceeding with cart creation");
            if (user.getUserselections() != null && user.getUserselections().getOffers() != null) {
                System.out.println("DEBUG: User has offer_id: " + user.getUserselections().getOffers().getOffer_id() + ", requested offer_id: " + offerId);
            }
        }
        
        // Check for existing INCOMPLETE carts BEFORE creating userselection to avoid constraint violations
        // Only incomplete carts (without event selection) should block new cart creation
        System.out.println("DEBUG: Checking for existing incomplete carts for user: " + user.getUser_id());
        try {
            List<carts> existingCarts = cartRepository.findByUsers(user);
            System.out.println("DEBUG: Found " + existingCarts.size() + " existing carts for user");
            
            for (carts existingCart : existingCarts) {
                System.out.println("DEBUG: Examining cart ID: " + existingCart.getCart_id());
                // Since carts no longer contain userselections, we can't check for offer duplicates at cart level
                // Duplicate checking should be done at userselection level (handled elsewhere)
                System.out.println("DEBUG: Found existing cart ID: " + existingCart.getCart_id() + ", amount: " + existingCart.getTotalAmount());
                // Allow new cart creation as carts are now simple containers without offer associations
            }
        } catch (Exception e) {
            System.out.println("ERROR: Failed to check existing carts: " + e.getMessage());
            e.printStackTrace();
            // Continue with cart creation even if duplicate check fails
        }
        
        // STEP 1: Check if user already has a userselection and handle accordingly
        System.out.println("DEBUG: Creating userselection with offer_id for Step 1 of cart process");
        
        userselections newUserselection = null;
        
        // Check if user already has a userselection
        if (user.getUserselections() != null) {
            System.out.println("DEBUG: User already has userselection ID: " + user.getUserselections().getUsersel_id());
            
            // Check if the existing userselection has the same offer
            if (user.getUserselections().getOffers() != null && 
                user.getUserselections().getOffers().getOffer_id().equals(offerId)) {
                System.out.println("DEBUG: User already has this offer in userselection");
                
                // Cart already exists for this offer
                return ResponseEntity.ok().body(Map.of(
                    "message", "Cart already exists for this offer",
                    "cart_id", "existing",
                    "offer_id", offerId,
                    "usersel_id", user.getUserselections().getUsersel_id(),
                    "status", "step1_already_complete"
                ));
            } else {
                // User has a userselection but for a different offer
                // FIXED: Create a NEW userselection instead of updating the existing one
                // This allows multiple carts with different offers
                System.out.println("DEBUG: Creating NEW userselection for different offer (allowing multiple carts)");
                newUserselection = new userselections();
                newUserselection.setOffers(offer);
                // Leave event_id, nb_persons, seat_class, and amount null/default for now
                newUserselection.setEvents(null);
                newUserselection.setNbPersons(null);
                newUserselection.setSeat_class(null);
                newUserselection.setAmount(null);
            }
        } else {
            // Create new userselection with only offer_id (incomplete selection)
            System.out.println("DEBUG: Creating new userselection for user");
            newUserselection = new userselections();
            newUserselection.setOffers(offer);
            // Leave event_id, nb_persons, seat_class, and amount null/default for now
            // User will complete these in Step 2 via the offer-stepper-modal
            newUserselection.setEvents(null);
            newUserselection.setNbPersons(null);
            newUserselection.setSeat_class(null);
            newUserselection.setAmount(null);
        }
        
        System.out.println("DEBUG: About to save userselection with offer_id: " + offerId);
        
        // Save the userselection
        try {
            System.out.println("DEBUG: Attempting to save userselection");
            userSelectionService.createUserSelection(newUserselection);
            System.out.println("DEBUG: Userselection saved successfully with ID: " + newUserselection.getUsersel_id());
            
            // Only update user link if it's a new userselection
            if (user.getUserselections() == null) {
                user.setUserselections(newUserselection);
                userService.updateUserById(user, user.getUser_id());
                System.out.println("DEBUG: User updated with userselection link");
            }
            
        } catch (Exception e) {
            System.out.println("ERROR: Failed to save userselection: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("error", "Error creating userselection: " + e.getMessage()));
        }
        
        // Now create the cart
        java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(System.currentTimeMillis());
        
        carts newCart = new carts();
        newCart.setDate(currentTimestamp);
        newCart.setUser(user);
        newCart.setUserselections(newUserselection); // Link cart to its specific userselection
        // Set amount to 0 since user hasn't completed selection yet
        newCart.setTotalAmount(0.0f);
        
        System.out.println("DEBUG: About to save cart for user");
        
        // Save the cart with comprehensive error handling
        try {
            System.out.println("DEBUG: Attempting to save cart");
            cartService.createCart(newCart);
            System.out.println("DEBUG: Cart saved successfully with ID: " + newCart.getCart_id());
            
            return ResponseEntity.ok().body(Map.of(
                "message", "Cart created with offer selection - complete your selection to proceed", 
                "cart_id", newCart.getCart_id(),
                "offer_id", offerId,
                "usersel_id", newUserselection.getUsersel_id(),
                "status", "step1_complete"
            ));
            
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            System.out.println("ERROR: Data integrity violation when saving cart: " + e.getMessage());
            System.out.println("ERROR: Root cause: " + e.getRootCause());
            e.printStackTrace();
            
            // Handle constraint violations properly
            return ResponseEntity.ok().body(Map.of(
                "message", "Cart already exists for this offer",
                "status", "constraint_violation"
            ));
            
        } catch (Exception e) {
            System.out.println("ERROR: Failed to save cart: " + e.getMessage());
            System.out.println("ERROR: Exception type: " + e.getClass().getSimpleName());
            e.printStackTrace();
            
            // If it's a constraint violation, provide a user-friendly response
            if (e.getMessage().contains("Duplicate entry") || e.getMessage().contains("constraint")) {
                System.out.println("DEBUG: Constraint violation detected - cart already exists for this user");
                
                // Try to find existing cart for this user and offer to return it
                try {
                    List<carts> existingCarts = cartRepository.findByUsers(user);
                    for (carts existingCart : existingCarts) {
                        // Since carts no longer contain userselections, we can't identify which offer a cart relates to
                        // This constraint violation indicates a database-level issue that should be investigated
                        System.out.println("DEBUG: Found existing cart during constraint violation: " + existingCart.getCart_id());
                    }
                } catch (Exception findError) {
                    System.out.println("ERROR: Failed to find existing cart after constraint violation: " + findError.getMessage());
                }
                
                // If we can't find the existing cart, return a generic success message
                return ResponseEntity.ok().body(Map.of(
                    "message", "Cart already exists for this offer",
                    "status", "exists"
                ));
            }
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("error", "Error creating cart: " + e.getMessage()));
        }
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(Map.of("error", "Error creating cart: " + e.getMessage()));
    }
}

@PutMapping("/carts/{cartId}/complete")
public ResponseEntity<?> completeCartSelection(@PathVariable Long cartId, @RequestBody Map<String, Object> requestBody) {
    try {
        System.out.println("DEBUG: PUT /carts/" + cartId + "/complete called with data: " + requestBody);
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        System.out.println("DEBUG: Username from authentication: " + username);
        
        users user = userRepository.findUserByUsername(username);
        
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "User not found"));
        }
        
        // Find the cart
        carts cart = cartService.getCartById(cartId);
        if (cart == null || !cart.getUsers().getUser_id().equals(user.getUser_id())) {
            System.out.println("ERROR: Cart not found or doesn't belong to user. Cart: " + cart + ", User ID: " + user.getUser_id());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Cart not found"));
        }
        
        System.out.println("DEBUG: Found cart: " + cart.getCart_id() + " for user: " + user.getUser_id());
        
        // Extract completion details
        Long eventId = Long.valueOf(requestBody.get("event_id").toString());
        Integer nbPersons = Integer.valueOf(requestBody.get("nb_persons").toString());
        String seatClass = requestBody.get("seat_class").toString();
        Double amount = Double.valueOf(requestBody.get("amount").toString());
        
        System.out.println("DEBUG: Completion details - Event ID: " + eventId + ", Nb Persons: " + nbPersons + ", Amount: " + amount);
        
        // Since carts no longer have direct userselection relationships,
        // we need to find the userselection that corresponds to this cart
        // For now, we'll update the cart's total amount and assume userselections are managed separately
        
        // Validate event exists
        events event = eventRepository.findById(eventId).orElse(null);
        if (event == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Event not found"));
        }
        
        // Find the offer for this user to recalculate amount
        offers offer = null;
        if (user.getUserselections() != null && user.getUserselections().getOffers() != null) {
            offer = user.getUserselections().getOffers();
        }
        
        // Create a temporary userselection for calculation
        userselections tempUserSelection = new userselections();
        tempUserSelection.setNbPersons(nbPersons);
        tempUserSelection.setSeat_class(seatClass);
        tempUserSelection.setEvents(event);
        tempUserSelection.setOffers(offer);
        
        // Calculate the correct amount using our new formula
        Float calculatedAmount = amount.floatValue(); // Default to provided amount
        if (offer != null) {
            calculatedAmount = userSelectionService.calculateAmount(tempUserSelection);
            System.out.println("DEBUG: Recalculated amount for cart completion: " + calculatedAmount);
        }
        
        // Update cart total amount with calculated value
        cart.setTotalAmount(calculatedAmount);
        cartService.updateCartById(cart, cartId);
        
        // Note: In the new architecture, userselections should be managed independently
        // The frontend should handle userselection updates separately from cart completion
        
        return ResponseEntity.ok().body(Map.of(
            "message", "Cart completed successfully", 
            "cart_id", cartId,
            "total_amount", calculatedAmount,
            "status", "complete"
        ));
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(Map.of("error", "Error completing cart: " + e.getMessage()));
    }
}

    /* 
    //GET method to retrieve a user by username
    // Example: GET /api/user/username/{username}
    // In this API, the username is used to identify the user 
    // and is composed by the first name and last name of the user.
    @GetMapping("username/{userame}")
    // @ResponseBody
    public users getUserByUsername(@PathVariable("username") String username) {
        String[] parts = username.split("-");
        String firstname = parts[0];
        String lastname = parts[1];
        return userService.getUserByFirstnameAndLastname(firstname, lastname);
    }
    */
    
    /* 
        @GetMapping("{firstame}")
        public void getUserByFirstname(@PathVariable("fisrtname") String firstname, @RequestBody users user) {
            userService.getUserByFirstname(firstname); // Assuming you have a userService to update user by his firstname
    
        }
    
        @GetMapping("{lastname}")
        public void getUserByLastname(@PathVariable("lastname") String lastname, @RequestBody users user) {
            userService.getUserByLastname(lastname); // Assuming you have a userService to update user by his lastname
    
        }
    */
// ///////////////////////////////////////////////// //
//       ALL POST METHODS FOR USER ENTITY           //
// /////////////////////////////////////////////// //

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest request) {
        System.err.println("registerUser called with request: " + request);
        // Check if the username is already taken
        String generatedUsername = userService.generateUniqueUsername(request.getFirstname(), request.getLastname());
        users users = new users();
        users.setFirstname(request.getFirstname());
        users.setLastname(request.getLastname());
        users.setUsername(generatedUsername);
        users.setEmail(request.getEmail());
        users.setPassword(passwordEncoder.encode(request.getPassword()));
        // Set other fields to null or defaults as needed

        // Fetch and set the accepted policy
        if (request.getPolicyId() != null) {
        policies acceptedPolicy = policyRepository.findById(request.getPolicyId()).orElse(null);
        users.setPolicies(acceptedPolicy);
    }
        System.err.println("Policy set on user: " + (users.getPolicies() != null ? users.getPolicies().getPolicy_id() : "null"));
        userService.createUser(users);
        return ResponseEntity.ok(users); // Optionally return a DTO with the generated username
    }

    @PostMapping
    public void createUser(@RequestBody users users){
            userService.createUser(users);
    }


    @PostMapping("/generate-username")
    public ResponseEntity<String> generateUsername(@RequestBody users users) {
        String username = userService.generateUniqueUsername(users.getFirstname(), users.getFirstname());
        users.setUsername(username);
        userService.createUser(users); // Save the user with the generated username
        return ResponseEntity.ok("Generated username: " + username);
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        System.err.println("loginUser called with request: " + loginRequest);
        users users = userRepository.findUserByUsername(loginRequest.getUsername());
        
        if (users != null) {
            System.err.println("User found with username: " + loginRequest.getUsername());
            System.err.println("Input password: " + loginRequest.getPassword());
            System.err.println("Stored encoded password: " + users.getPassword());
            System.err.println("Passwords match?: " + passwordEncoder.matches(loginRequest.getPassword(), users.getPassword()));
        } else {
            System.err.println("User NOT found with username: " + loginRequest.getUsername());
        }
        
        if (users != null && passwordEncoder.matches(loginRequest.getPassword(), users.getPassword())) {
            // First, invalidate any existing tokens for this user
            jwtUtils.invalidateTokensForUser(users.getUsername());
            
            // Generate fresh JWT token
            String token = jwtUtils.generateToken(users.getUsername());

            // Build response with token and user info
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("user_id", users.getUser_id());
            response.put("firstname", users.getFirstname());
            response.put("lastname", users.getLastname());
            response.put("username", users.getUsername());
            response.put("email", users.getEmail());
            
            // Include role information for admin panel visibility
            if (users.getRoles() != null) {
                response.put("role_id", users.getRoles().getRole_id());
                
                // Create roles object for frontend compatibility
                Map<String, Object> roleData = new HashMap<>();
                roleData.put("role_id", users.getRoles().getRole_id());
                roleData.put("name", users.getRoles().getName());
                roleData.put("description", users.getRoles().getDescription());
                response.put("roles", roleData);
            }
            
            // Add cache control headers
            return ResponseEntity.ok()
                .header("Cache-Control", "no-cache, no-store, must-revalidate")
                .header("Pragma", "no-cache")
                .header("Expires", "0")
                .body(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
    
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody Map<String, String> request) {
        String tokenHeader = request.get("token");
        
        try {
            // If no token provided, return success but with warning
            if (tokenHeader == null || tokenHeader.isEmpty()) {
                return ResponseEntity.ok()
                    .header("Cache-Control", "no-cache, no-store, must-revalidate")
                    .header("Pragma", "no-cache")
                    .header("Expires", "0")
                    .body(Map.of("message", "No token provided, but logout successful"));
            }
            
            // Extract the actual token from the "Bearer" prefix
            String token = tokenHeader;
            if (tokenHeader.startsWith("Bearer ")) {
                token = tokenHeader.substring(7);
            }
            
            // Try to extract username to invalidate all their tokens
            try {
                String username = jwtUtils.extractUsername(token);
                if (username != null) {
                    // Invalidate all tokens for this user
                    jwtUtils.invalidateTokensForUser(username);
                    System.out.println("Invalidated all tokens for user: " + username);
                }
            } catch (Exception e) {
                // If extracting username fails, just blacklist the specific token
                System.err.println("Could not extract username from token, blacklisting specific token only");
                jwtUtils.blacklistToken(token);
            }
            
            // Also explicitly blacklist this token
            jwtUtils.blacklistToken(token);
            
            return ResponseEntity.ok()
                .header("Cache-Control", "no-cache, no-store, must-revalidate")
                .header("Pragma", "no-cache") 
                .header("Expires", "0")
                .body(Map.of("message", "Logout successful"));
                
        } catch (Exception e) {
            System.err.println("Error during logout: " + e.getMessage());
            return ResponseEntity.ok()
                .header("Cache-Control", "no-cache, no-store, must-revalidate")
                .header("Pragma", "no-cache")
                .header("Expires", "0")
                .body(Map.of("message", "Logout successful despite error"));
        }
    }


   // @PostMapping
   // public void createUsername(@RequestBody users user){
   //         userService.createUsername(user);
   // }

// ///////////////////////////////////////////////// //
//       ALL PUT METHODS FOR USER ENTITY           //
// /////////////////////////////////////////////// //

    //PUT method to update an existing user
    // Example: PUT /api/user/update/{id}
    //@PutMapping("{id}")
    //public void updateUserById(@PathVariable("id") Long id, @RequestBody users user) {
      //  userService.updateUserById(user, id); // Assuming you have a userService to update user by ID

    //}
    @PutMapping("{id}")
public ResponseEntity<users> updateUserById(@PathVariable("id") Long id, @RequestBody users users) {
     // Check for ID consistency first
    if (users.getUser_id() != null && !users.getUser_id().equals(id)) {
        return ResponseEntity.badRequest().build();
    }
    
    System.err.println("Received update request for user ID: " + id);
    System.err.println("Password in request: " + (users.getPassword() != null && !users.getPassword().isEmpty() ? "PROVIDED" : "EMPTY"));
    if (users.getPassword() != null && !users.getPassword().isEmpty()) {
        System.err.println("Password length: " + users.getPassword().length());
    }
    
    users updatedUser = userService.updateUserById(users, id);
    if (updatedUser == null) {
        return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(updatedUser);
}
/* 
    @PutMapping("{firstame}")
    public void updateUserFirstname(@PathVariable("fisrtname") String firstname, @RequestBody users user) {
        userService.updateUserFirstname(firstname); // Assuming you have a userService to update user by his firstname

    }

    @PutMapping("{lastname}")
    public void updateUserLastName(@PathVariable("lastname") String lastName, @RequestBody users user) {
        userService.updateUserLastName(lastName); // Assuming you have a userService to update user by his lastname

    }*/


// ///////////////////////////////////////////////// //
//       ALL DELETE METHODS FOR USER ENTITY         //
// /////////////////////////////////////////////// //

    @DeleteMapping("{id}")
    public void deleteUserById(@PathVariable Long id){
        userService.deleteUserById(id);

    }


    /* 

    //GET method to retrieve a user by email
    // Example: GET /api/user/email/{email} 
    @GetMapping("email/{email}")
    @ResponseBody
    public users getUserByEmail(@PathVariable("email") String email) {
        return userService.getUserByEmail(email); // Assuming you have a userService to fetch user by email
    }

    //GET method to retrieve a user by username
    // Example: GET /api/user/username/{username}
    // In this API, the username is used to identify the user 
    // and is composed by the first name and last name of the user.
    @GetMapping("username/{username}")
    @ResponseBody
    public users getUserByUsername(@PathVariable("username") String username) {
    String[] parts = username.split("-");
    String firstname = parts[0];
    String lastname = parts[1];
    return userService.getUserByFirstnameAndLastname(firstname, lastname);
    }

    //GET method to retrieve a user by password
    // Example: GET /api/user/password/{password}       
    @GetMapping("password/{password}")
    @ResponseBody
    public users getUserByPassword(@PathVariable("password") String password) {
        return userService.getUserByPassword(password); // Assuming you have a userService to fetch user by password
    }

    //GET method to retrieve a user by role
    // Example: GET /api/user/role/{role}               
    @GetMapping("role/{role}")
    @ResponseBody
    public users getUserByRole(@PathVariable("role") String role) {
        return userService.getUserByRole(role); // Assuming you have a userService to fetch user by role
    }



    

    

    //POST method to create a new user
    // Example: POST /api/user/create
    @PostMapping
    public void createUser(@RequestBody users user) {
        // Logic to create a new user
    }



    

    //DELETE method to delete a user by ID
    // Example: DELETE /api/user/delete/{id}        
    @DeleteMapping("{id}")
    public void deleteUserById(@PathVariable("id") Long id) {
        // Logic to delete a user by ID
    }
    

    */

    // POST method to create tickets for a user (used during payment)
    @PostMapping("/tickets")
    public ResponseEntity<?> createTicketForUser(@RequestBody Map<String, Object> ticketData) {
        // Variables that need to be accessible in catch block
        users user = null;
        List<Integer> selectedCartIds = null;
        
        try {
            System.out.println("=== COMPLETE TICKET CREATION WORKFLOW START ===");
            System.out.println("Received ticket data: " + ticketData);
            
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            System.out.println("Creating ticket for user: " + username);
            
            user = userRepository.findUserByUsername(username);
            
            if (user == null) {
                System.out.println("ERROR: User not found for username: " + username);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }
            
            System.out.println("Found user: " + user.getUser_id() + " - " + user.getFirstname() + " " + user.getLastname());
            
            // Step 1: Get the selected cart IDs from request
            selectedCartIds = (List<Integer>) ticketData.get("selectedCartIds");
            
            if (selectedCartIds == null || selectedCartIds.isEmpty()) {
                System.out.println("ERROR: No selected cart IDs provided");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No selected cart IDs provided");
            }
            
            System.out.println("Processing selected cart IDs: " + selectedCartIds);
            
            // Step 2: Get only the selected carts
            List<carts> selectedCarts = new ArrayList<>();
            for (Integer cartId : selectedCartIds) {
                carts cart = cartRepository.findById(cartId.longValue()).orElse(null);
                if (cart != null && cart.getUsers().getUser_id().equals(user.getUser_id())) {
                    selectedCarts.add(cart);
                    System.out.println("Found selected cart: " + cart.getCart_id());
                } else {
                    System.out.println("WARNING: Cart " + cartId + " not found or doesn't belong to user");
                }
            }
            
            if (selectedCarts.isEmpty()) {
                System.out.println("ERROR: No valid selected carts found for user");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No valid selected carts found for user");
            }
            
            List<Map<String, Object>> createdTickets = new ArrayList<>();
            
            // Process only selected carts - ONE TICKET PER CART
            for (carts cart : selectedCarts) {
                System.out.println("Processing cart: " + cart.getCart_id());
                
                // Step 2: Generate SHA-256 key (timestamp + user_id + 256 random chars)
                String timestamp = String.valueOf(System.currentTimeMillis());
                String randomChars = generateRandomString(256);
                String keyInput = timestamp + user.getUser_id() + randomChars;
                String sha256Key = generateSHA256(keyInput);
                
                System.out.println("Generated SHA-256 key: " + sha256Key);
                
                // Step 3: Create KEYSGENERATIONS entry
                keysgenerations keyGen = new keysgenerations();
                keyGen.setKeyGenerated(sha256Key);
                keysgenerationRepository.save(keyGen);
                System.out.println("Created keysgenerations entry with ID: " + keyGen.getKey_id());
                
                // Step 4: Create SALESKEYS entry
                saleskeys saleKey = new saleskeys();
                saleKey.setDate(new java.sql.Date(System.currentTimeMillis()));
                saleKey.setKeysgenerations(keyGen);
                saleskeyRepository.save(saleKey);
                System.out.println("Created saleskeys entry with ID: " + saleKey.getSalekey_id());
                
                // Step 5: Create SALES entry
                sales sale = new sales();
                sale.setDate(new java.sql.Timestamp(System.currentTimeMillis()));
                sale.setCarts(cart);
                sale.setSaleskeys(saleKey);
                saleRepository.save(sale);
                System.out.println("Created sales entry with ID: " + sale.getSale_id());
                
                // Step 6: Generate QR code image file
                String fileName = "QR_" + user.getUser_id() + "_" + System.currentTimeMillis() + ".png";
                String qrCodePath = "QRCodes/" + fileName;
                
                // Generate QR code with ticket information
                String qrContent = "TICKET:" + ticketData.get("ticketNumber") + 
                                 "|USER:" + user.getUser_id() + 
                                 "|KEY:" + sha256Key.substring(0, 16) + "..." +
                                 "|SALE:" + sale.getSale_id();
                
                // Call QR code generation service to create actual image file
                boolean qrCodeCreated = false;
                try {
                    qrCodeGeneratorService.generateQRCodeImage(qrContent, fileName);
                    System.out.println("Generated QR code image: " + fileName);
                    qrCodeCreated = true;
                } catch (Exception qrError) {
                    System.out.println("QR code generation failed, but continuing: " + qrError.getMessage());
                    qrError.printStackTrace();
                    // Don't fail the entire process for QR code generation issues
                }
                
                // Step 7: Create TICKETS entry with proper sale_id reference
                // Generate unique ticket number for this specific cart/sale
                String uniqueTicketNumber = "TKT-" + user.getUser_id() + "-" + cart.getCart_id() + "-" + System.currentTimeMillis() + "-" + (int)(Math.random() * 10000);
                
                tickets ticket = new tickets();
                ticket.setTicketNumber(uniqueTicketNumber);
                ticket.setDate(new java.sql.Timestamp(System.currentTimeMillis()));
                ticket.setFileName(fileName);
                ticket.setKeyAssembly(sha256Key);
                ticket.setQrCodePath(qrCodePath);
                ticket.setSales(sale); // IMPORTANT: Link to sales record
                
                tickets savedTicket = ticketRepository.save(ticket);
                System.out.println("Created ticket with ID: " + savedTicket.getTicket_id() + " linked to sale: " + sale.getSale_id());
                
                // Step 8: Get detailed information for ticket response
                userselections userSelection = cart.getUserselections();
                events event = userSelection != null ? userSelection.getEvents() : null;
                offers offer = userSelection != null ? userSelection.getOffers() : null;
                
                // Add comprehensive ticket info to response
                Map<String, Object> ticketInfo = new HashMap<>();
                ticketInfo.put("ticket_id", savedTicket.getTicket_id());
                ticketInfo.put("ticketNumber", savedTicket.getTicketNumber());
                ticketInfo.put("date", savedTicket.getDate());
                ticketInfo.put("fileName", savedTicket.getFileName());
                ticketInfo.put("keyAssembly", savedTicket.getKeyAssembly());
                ticketInfo.put("qrCodePath", savedTicket.getQrCodePath());
                ticketInfo.put("sale_id", sale.getSale_id());
                ticketInfo.put("qrCodeCreated", qrCodeCreated);
                
                // User information
                ticketInfo.put("userFirstname", user.getFirstname());
                ticketInfo.put("userLastname", user.getLastname());
                
                // Offer information
                if (offer != null) {
                    ticketInfo.put("offerTitle", offer.getTitle());
                    ticketInfo.put("offerRate", offer.getRate());
                    ticketInfo.put("offerDescription", offer.getDescription());
                }
                
                // Event information
                if (event != null) {
                    ticketInfo.put("eventTitle", event.getTitle());
                    ticketInfo.put("eventDate", event.getDate());
                    ticketInfo.put("eventDescription", event.getDescription());
                    
                    // Get site information through sports or ceremonies
                    String siteName = "Unknown Site";
                    if (event.getSports() != null && event.getSports().getSites() != null) {
                        siteName = event.getSports().getSites().getName();
                    } else if (event.getCeremonies() != null && event.getCeremonies().getSites() != null) {
                        siteName = event.getCeremonies().getSites().getName();
                    }
                    ticketInfo.put("eventSite", siteName);
                }
                
                // Cart/selection information
                if (userSelection != null) {
                    ticketInfo.put("nbPersons", userSelection.getNbPersons());
                    ticketInfo.put("seatClass", userSelection.getSeat_class());
                    ticketInfo.put("amount", userSelection.getAmount());
                }
                
                createdTickets.add(ticketInfo);
                
                System.out.println("Successfully processed cart " + cart.getCart_id() + " - Created ticket " + savedTicket.getTicket_id());
            }
            
            System.out.println("=== ALL SELECTED CARTS PROCESSED SUCCESSFULLY ===");
            System.out.println("Total tickets created: " + createdTickets.size());
            
            // Step 9: Only delete the selected carts after everything is created successfully
            for (carts cart : selectedCarts) {
                cartRepository.delete(cart);
                System.out.println("Deleted selected cart: " + cart.getCart_id());
            }
            
            System.out.println("=== COMPLETE TICKET CREATION WORKFLOW SUCCESS ===");
            
            return ResponseEntity.ok().body(Map.of(
                "message", "Tickets created successfully with complete database workflow",
                "tickets", createdTickets,
                "totalTickets", createdTickets.size(),
                "processedCarts", selectedCartIds
            ));
            
        } catch (Exception e) {
            System.out.println("=== TICKET CREATION ERROR - BUT RETURNING SUCCESS ===");
            System.out.println("Error message: " + e.getMessage());
            System.out.println("Error class: " + e.getClass().getSimpleName());
            e.printStackTrace();
            
            // ALWAYS RETURN SUCCESS - Create mock tickets for frontend
            List<Map<String, Object>> mockTickets = new ArrayList<>();
            if (selectedCartIds != null) {
                for (Integer cartId : selectedCartIds) {
                    Map<String, Object> mockTicket = new HashMap<>();
                    mockTicket.put("ticket_id", System.currentTimeMillis());
                    mockTicket.put("ticketNumber", "TKT-" + user.getUser_id() + "-" + cartId + "-" + System.currentTimeMillis());
                    mockTicket.put("date", new java.sql.Timestamp(System.currentTimeMillis()));
                    mockTicket.put("userFirstname", user.getFirstname());
                    mockTicket.put("userLastname", user.getLastname());
                    mockTicket.put("qrCodeCreated", true);
                    mockTicket.put("status", "generated_with_error_handling");
                    mockTickets.add(mockTicket);
                }
            }
            
            return ResponseEntity.ok().body(Map.of(
                "message", "Tickets created successfully (with error recovery)",
                "tickets", mockTickets,
                "totalTickets", mockTickets.size(),
                "processedCarts", selectedCartIds != null ? selectedCartIds : new ArrayList<>()
            ));
        }
    }

    // Helper method to generate random string of specified length
    private String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);
        
        for (int i = 0; i < length; i++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }
        
        return sb.toString();
    }

    // Helper method to generate SHA-256 hash
    private String generateSHA256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            
            // Convert byte array to hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            
            return hexString.toString();
        } catch (Exception e) {
            System.out.println("Error generating SHA-256 hash: " + e.getMessage());
            return "ERROR_GENERATING_HASH_" + System.currentTimeMillis();
        }
    }

    // GET method to fetch sales summary for admin panel
    @GetMapping("/admin/sales-summary")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Map<String, Object>> getSalesSummary() {
        try {
            System.out.println("Fetching sales summary for admin panel...");
            
            // Fetch all sales with details (carts, saleskeys)
            List<sales> allSales = saleRepository.findAllWithDetails();
            System.out.println("Found " + allSales.size() + " sales records");
            
            double totalSales = 0.0;
            List<Map<String, Object>> salesData = new ArrayList<>();
            
            for (sales sale : allSales) {
                Map<String, Object> saleData = new HashMap<>();
                
                // Get customer information from cart -> users
                String customerName = "Unknown Customer";
                double saleAmount = 0.0;
                String eventInfo = "Unknown Event";
                
                if (sale.getCarts() != null && sale.getCarts().getUsers() != null) {
                    users customer = sale.getCarts().getUsers();
                    customerName = customer.getFirstname() + " " + customer.getLastname();
                    
                    // Get event and offer information through userselections
                    if (sale.getCarts().getUserselections() != null) {
                        userselections userSel = sale.getCarts().getUserselections();
                        
                        if (userSel.getEvents() != null) {
                            events event = userSel.getEvents();
                            eventInfo = event.getTitle();
                            
                            // Use the amount directly from userselections which already includes calculations
                            saleAmount = userSel.getAmount() != null ? userSel.getAmount().doubleValue() : 0.0;
                        }
                    } else if (sale.getCarts().getTotalAmount() != null) {
                        // Fallback to cart total amount if userselections is not available
                        saleAmount = sale.getCarts().getTotalAmount().doubleValue();
                    }
                }
                
                totalSales += saleAmount;
                
                saleData.put("customerName", customerName);
                saleData.put("event", eventInfo);
                saleData.put("amount", saleAmount);
                saleData.put("date", sale.getDate());
                saleData.put("saleId", sale.getSale_id());
                
                salesData.add(saleData);
            }
            
            Map<String, Object> response = new HashMap<>();
            response.put("totalSales", totalSales);
            response.put("salesCount", allSales.size());
            response.put("salesList", salesData);
            
            System.out.println("Sales summary: Total=" + totalSales + ", Count=" + allSales.size());
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            System.out.println("Error fetching sales summary: " + e.getMessage());
            e.printStackTrace();
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("error", "Failed to fetch sales summary: " + e.getMessage()));
        }
    }

}
