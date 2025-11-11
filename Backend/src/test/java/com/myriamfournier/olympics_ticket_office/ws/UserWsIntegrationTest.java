package com.myriamfournier.olympics_ticket_office.ws;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.containsString;

import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;

import com.myriamfournier.olympics_ticket_office.pojo.users;
import com.myriamfournier.olympics_ticket_office.pojo.roles;
import com.myriamfournier.olympics_ticket_office.pojo.countries;
import com.myriamfournier.olympics_ticket_office.pojo.carts;
import com.myriamfournier.olympics_ticket_office.pojo.offers;
import com.myriamfournier.olympics_ticket_office.pojo.userselections;
import com.myriamfournier.olympics_ticket_office.pojo.LoginRequest;
import com.myriamfournier.olympics_ticket_office.pojo.RegisterRequest;
import com.myriamfournier.olympics_ticket_office.pojo.tickets;
import com.myriamfournier.olympics_ticket_office.pojo.sales;
import com.myriamfournier.olympics_ticket_office.pojo.saleskeys;
import com.myriamfournier.olympics_ticket_office.pojo.keysgenerations;
import com.myriamfournier.olympics_ticket_office.pojo.events;
import com.myriamfournier.olympics_ticket_office.pojo.ceremonies;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myriamfournier.olympics_ticket_office.repository.UserRepository;
import com.myriamfournier.olympics_ticket_office.service.UserService;
import com.myriamfournier.olympics_ticket_office.service.CartService;
import com.myriamfournier.olympics_ticket_office.repository.CartRepository;
import com.myriamfournier.olympics_ticket_office.service.UserSelectionService;
import com.myriamfournier.olympics_ticket_office.repository.EventRepository;
import com.myriamfournier.olympics_ticket_office.repository.OfferRepository;
import com.myriamfournier.olympics_ticket_office.repository.TicketRepository;
import com.myriamfournier.olympics_ticket_office.repository.SaleRepository;
import com.myriamfournier.olympics_ticket_office.repository.SaleskeyRepository;
import com.myriamfournier.olympics_ticket_office.repository.KeysgenerationRepository;
import com.myriamfournier.olympics_ticket_office.repository.PolicyRepository;
import com.myriamfournier.olympics_ticket_office.service.QRCodeGeneratorService;
import com.myriamfournier.olympics_ticket_office.configuration.JwtUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Integration test for UserWs getCurrentUser endpoint
 * This test focuses specifically on the /api/user/me endpoint behavior
 */
public class UserWsIntegrationTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;
    
    @Mock
    private UserRepository userRepository;
    
    @Mock
    private PasswordEncoder passwordEncoder;
    
    @Mock
    private PolicyRepository policyRepository;
    
    @Mock
    private CartService cartService;
    
    @Mock
    private CartRepository cartRepository;
    
    @Mock
    private UserSelectionService userSelectionService;
    
    @Mock
    private EventRepository eventRepository;
    
    @Mock
    private OfferRepository offerRepository;
    
    @Mock
    private TicketRepository ticketRepository;
    
    @Mock
    private SaleRepository saleRepository;
    
    @Mock
    private SaleskeyRepository saleskeyRepository;
    
    @Mock
    private KeysgenerationRepository keysgenerationRepository;
    
    @Mock
    private QRCodeGeneratorService qrCodeGeneratorService;

    @Mock
    private JwtUtils jwtUtils;

    @InjectMocks
    private UserWs userWs;

    private ObjectMapper objectMapper = new ObjectMapper();
    private users testUser;
    private users testUser2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        // Manually inject mocks into UserWs using reflection since @Autowired fields
        // are not automatically handled by @InjectMocks
        ReflectionTestUtils.setField(userWs, "userRepository", userRepository);
        ReflectionTestUtils.setField(userWs, "userService", userService);
        ReflectionTestUtils.setField(userWs, "passwordEncoder", passwordEncoder);
        ReflectionTestUtils.setField(userWs, "policyRepository", policyRepository);
        ReflectionTestUtils.setField(userWs, "cartService", cartService);
        ReflectionTestUtils.setField(userWs, "cartRepository", cartRepository);
        ReflectionTestUtils.setField(userWs, "userSelectionService", userSelectionService);
        ReflectionTestUtils.setField(userWs, "eventRepository", eventRepository);
        ReflectionTestUtils.setField(userWs, "offerRepository", offerRepository);
        ReflectionTestUtils.setField(userWs, "ticketRepository", ticketRepository);
        ReflectionTestUtils.setField(userWs, "saleRepository", saleRepository);
        ReflectionTestUtils.setField(userWs, "saleskeyRepository", saleskeyRepository);
        ReflectionTestUtils.setField(userWs, "keysgenerationRepository", keysgenerationRepository);
        ReflectionTestUtils.setField(userWs, "qrCodeGeneratorService", qrCodeGeneratorService);
        ReflectionTestUtils.setField(userWs, "jwtUtils", jwtUtils);
        
        mockMvc = MockMvcBuilders.standaloneSetup(userWs).build();
        
        // Create test user
        testUser = new users();
        testUser.setUser_id(1L);
        testUser.setFirstname("Integration");
        testUser.setLastname("Test");
        testUser.setUsername("integration-test-user");
        testUser.setEmail("integration@test.com");
        testUser.setPassword("test-password");
        testUser.setPhoneNumber("1234567890");
        testUser.setAddress("123 Test Street");
        testUser.setCity("Test City");
        testUser.setPostalCode("12345");
        
        // Create second test user
        testUser2 = new users();
        testUser2.setUser_id(2L);
        testUser2.setFirstname("John");
        testUser2.setLastname("Doe");
        testUser2.setUsername("john-doe");
        testUser2.setEmail("john@example.com");
        testUser2.setPassword("john-password");
    }

    @Test
    void testGetCurrentUser_Success() throws Exception {
        // Mock security context
        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("integration-test-user");
        SecurityContextHolder.setContext(securityContext);
        
        // Mock repository to return the test user
        when(userRepository.findUserByUsername("integration-test-user")).thenReturn(testUser);

        mockMvc.perform(get("/api/user/me")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname").value("Integration"))
                .andExpect(jsonPath("$.lastname").value("Test"))
                .andExpect(jsonPath("$.username").value("integration-test-user"))
                .andExpect(jsonPath("$.email").value("integration@test.com"));

        // Verify repository was called
        verify(userRepository).findUserByUsername("integration-test-user");
        
        // Clean up security context
        SecurityContextHolder.clearContext();
    }

    @Test
    void testGetCurrentUser_UserNotFound() throws Exception {
        // Mock security context
        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("nonexistent-user");
        SecurityContextHolder.setContext(securityContext);
        
        // Mock repository to return null (user not found)
        when(userRepository.findUserByUsername("nonexistent-user")).thenReturn(null);

        mockMvc.perform(get("/api/user/me")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        // Verify repository was called
        verify(userRepository).findUserByUsername("nonexistent-user");
        
        // Clean up security context
        SecurityContextHolder.clearContext();
    }

    @Test
    void testGetUserById_Success() throws Exception {
        // Mock userService to return the test user
        when(userService.getUserById(1L)).thenReturn(testUser);

        mockMvc.perform(get("/api/user/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user_id").value(1))
                .andExpect(jsonPath("$.firstname").value("Integration"))
                .andExpect(jsonPath("$.lastname").value("Test"))
                .andExpect(jsonPath("$.username").value("integration-test-user"));

        // Verify service was called
        verify(userService).getUserById(1L);
    }

    @Test
    void testGetUserById_NotFound() throws Exception {
        // Mock userService to return null (user not found)
        when(userService.getUserById(999L)).thenReturn(null);

        mockMvc.perform(get("/api/user/999")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()); // Note: The actual endpoint might return null, not 404

        // Verify service was called
        verify(userService).getUserById(999L);
    }

    @Test
    void testGetUserByUsername_Success() throws Exception {
        // Mock userService to return the test user
        when(userService.getUserByUsername("john-doe")).thenReturn(testUser2);

        mockMvc.perform(get("/api/user/username/john-doe")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname").value("John"))
                .andExpect(jsonPath("$.lastname").value("Doe"))
                .andExpect(jsonPath("$.username").value("john-doe"));

        // Verify service was called
        verify(userService).getUserByUsername("john-doe");
    }

    @Test
    void testGetUserByUsername_NotFound() throws Exception {
        // Mock userService to return null (user not found)
        when(userService.getUserByUsername("nonexistent-user")).thenReturn(null);

        mockMvc.perform(get("/api/user/username/nonexistent-user")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()); // Note: The endpoint returns null, not 404

        // Verify service was called
        verify(userService).getUserByUsername("nonexistent-user");
    }

    @Test
    void testGetAllUsers_Success() throws Exception {
        // Mock userService to return list of users
        List<users> userList = Arrays.asList(testUser, testUser2);
        when(userService.getAllUsers()).thenReturn(userList);

        mockMvc.perform(get("/api/user")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].firstname").value("Integration"))
                .andExpect(jsonPath("$[1].firstname").value("John"));

        // Verify service was called
        verify(userService).getAllUsers();
    }

    @Test
    void testGetUserCarts_UserNotFound() throws Exception {
        // Mock security context
        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("nonexistent-user");
        SecurityContextHolder.setContext(securityContext);
        
        // Mock repository to return null (user not found)
        when(userRepository.findUserByUsername("nonexistent-user")).thenReturn(null);

        mockMvc.perform(get("/api/user/carts/user")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        // Verify repository was called
        verify(userRepository).findUserByUsername("nonexistent-user");
        
        // Clean up security context
        SecurityContextHolder.clearContext();
    }

    @Test
    void testLogin_Success() throws Exception {
        // Given
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("testuser");
        loginRequest.setPassword("password123");
        
        users mockUser = new users();
        mockUser.setUser_id(1L);
        mockUser.setUsername("testuser");
        mockUser.setPassword("$2a$10$encoded.password.hash"); // Encoded password
        
        when(userRepository.findUserByUsername("testuser")).thenReturn(mockUser);
        when(passwordEncoder.matches("password123", "$2a$10$encoded.password.hash")).thenReturn(true);
        when(jwtUtils.generateToken("testuser")).thenReturn("fake.jwt.token");

        // When & Then
        mockMvc.perform(post("/api/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("fake.jwt.token"))
                .andExpect(jsonPath("$.username").value("testuser"))
                .andExpect(jsonPath("$.user_id").value(1L));

        // Verify 
        verify(userRepository).findUserByUsername("testuser");
        verify(passwordEncoder, times(2)).matches("password123", "$2a$10$encoded.password.hash");
        verify(jwtUtils).invalidateTokensForUser("testuser");
        verify(jwtUtils).generateToken("testuser");
    }

    @Test
    void testLogin_InvalidCredentials() throws Exception {
        // Given
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("testuser");
        loginRequest.setPassword("wrongpassword");
        
        users mockUser = new users();
        mockUser.setUsername("testuser");
        mockUser.setPassword("$2a$10$encoded.password.hash");
        
        when(userRepository.findUserByUsername("testuser")).thenReturn(mockUser);
        when(passwordEncoder.matches("wrongpassword", "$2a$10$encoded.password.hash")).thenReturn(false);

        // When & Then
        mockMvc.perform(post("/api/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string("Invalid credentials"));

        verify(userRepository).findUserByUsername("testuser");
        verify(passwordEncoder, times(2)).matches("wrongpassword", "$2a$10$encoded.password.hash");
    }

    @Test
    void testLogin_UserNotFound() throws Exception {
        // Given
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("nonexistent");
        loginRequest.setPassword("password123");
        
        when(userRepository.findUserByUsername("nonexistent")).thenReturn(null);

        // When & Then
        mockMvc.perform(post("/api/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string("Invalid credentials"));

        verify(userRepository).findUserByUsername("nonexistent");
    }

    @Test
    void testRegisterUser_Success() throws Exception {
        // Given
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setFirstname("New");
        registerRequest.setLastname("User");
        registerRequest.setPassword("password123");
        registerRequest.setEmail("newuser@test.com");
        registerRequest.setPolicyId(1L);
        
        when(userService.generateUniqueUsername("New", "User")).thenReturn("User-New");
        when(passwordEncoder.encode("password123")).thenReturn("$2a$10$encoded.password.hash");

        // When & Then
        mockMvc.perform(post("/api/user/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(registerRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname").value("New"))
                .andExpect(jsonPath("$.lastname").value("User"))
                .andExpect(jsonPath("$.username").value("User-New"));

        verify(userService).generateUniqueUsername("New", "User");
        verify(passwordEncoder).encode("password123");
        verify(userService).createUser(any(users.class));
    }

    @Test
    void testLogout_Success() throws Exception {
        // Given
        Map<String, String> logoutRequest = new HashMap<>();
        logoutRequest.put("username", "testuser");

        // When & Then
        mockMvc.perform(post("/api/user/logout")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(logoutRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("No token provided, but logout successful"));
    }

    @Test
    void testCreateCartForUser_Success() throws Exception {
        // Setup authentication
        Authentication auth = mock(Authentication.class);
        when(auth.getName()).thenReturn("testuser");
        SecurityContextHolder.getContext().setAuthentication(auth);

        // Mock user lookup
        users testUser = new users();
        testUser.setUser_id(1L);
        testUser.setUsername("testuser");
        when(userRepository.findUserByUsername("testuser")).thenReturn(testUser);

        // Mock offer lookup
        offers testOffer = new offers();
        testOffer.setOffer_id(1L);
        testOffer.setTitle("Test Offer");
        when(offerRepository.findById(1L)).thenReturn(java.util.Optional.of(testOffer));

        // Mock empty cart list
        when(cartRepository.findByUsers(testUser)).thenReturn(new ArrayList<>());

        // Mock userselection creation with ID setting
        doAnswer(invocation -> {
            userselections usersel = invocation.getArgument(0);
            usersel.setUsersel_id(1L); // Set the ID after "saving"
            return null;
        }).when(userSelectionService).createUserSelection(any(userselections.class));

        // Mock cart creation with ID setting
        doAnswer(invocation -> {
            carts cart = invocation.getArgument(0);
            cart.setCart_id(1L); // Set the ID after "saving"
            return null;
        }).when(cartService).createCart(any(carts.class));

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("offer_id", 1L);
        requestBody.put("nb_persons", 2);
        requestBody.put("seat_class", "price1");

        mockMvc.perform(post("/api/user/carts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Cart created with offer selection - complete your selection to proceed"))
                .andExpect(jsonPath("$.cart_id").value(1))
                .andExpect(jsonPath("$.offer_id").value(1))
                .andExpect(jsonPath("$.status").value("step1_complete"));
    }

    @Test
    void testCreateCartForUser_EmptyRequestBody() throws Exception {
        // Setup authentication
        Authentication auth = mock(Authentication.class);
        when(auth.getName()).thenReturn("testuser");
        SecurityContextHolder.getContext().setAuthentication(auth);

        mockMvc.perform(post("/api/user/carts")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error").value("Invalid cart data: request body is empty"));
    }

    @Test
    void testCreateCartForUser_NoAuthentication() throws Exception {
        // Clear authentication context
        SecurityContextHolder.clearContext();

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("offer_id", 1L);

        mockMvc.perform(post("/api/user/carts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(status().isUnauthorized())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error").value("No authentication"));
    }

    @Test
    void testCreateCartForUser_UserNotFound() throws Exception {
        // Setup authentication
        Authentication auth = mock(Authentication.class);
        when(auth.getName()).thenReturn("nonexistent");
        SecurityContextHolder.getContext().setAuthentication(auth);

        // Mock user not found
        when(userRepository.findUserByUsername("nonexistent")).thenReturn(null);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("offer_id", 1L);

        mockMvc.perform(post("/api/user/carts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error").value("User not found"));
    }

    @Test
    void testCreateCartForUser_MissingOfferId() throws Exception {
        // Setup authentication
        Authentication auth = mock(Authentication.class);
        when(auth.getName()).thenReturn("testuser");
        SecurityContextHolder.getContext().setAuthentication(auth);

        // Mock user lookup
        users testUser = new users();
        testUser.setUser_id(1L);
        testUser.setUsername("testuser");
        when(userRepository.findUserByUsername("testuser")).thenReturn(testUser);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("nb_persons", 2);

        mockMvc.perform(post("/api/user/carts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error").value("Missing offer_id in request"));
    }

    @Test
    void testCreateCartForUser_OfferNotFound() throws Exception {
        // Setup authentication
        Authentication auth = mock(Authentication.class);
        when(auth.getName()).thenReturn("testuser");
        SecurityContextHolder.getContext().setAuthentication(auth);

        // Mock user lookup
        users testUser = new users();
        testUser.setUser_id(1L);
        testUser.setUsername("testuser");
        when(userRepository.findUserByUsername("testuser")).thenReturn(testUser);

        // Mock offer not found
        when(offerRepository.findById(999L)).thenReturn(java.util.Optional.empty());

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("offer_id", 999L);

        mockMvc.perform(post("/api/user/carts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error").value("Offer not found"));
    }

    // ===== TICKET CREATION TESTS =====
    // Tests for createTicketForUser method - 663 missed instructions target

    @Test
    void testCreateTicketForUser_Success() throws Exception {
        // Setup authentication
        Authentication auth = mock(Authentication.class);
        when(auth.getName()).thenReturn("testuser");
        SecurityContextHolder.getContext().setAuthentication(auth);

        // Mock user lookup
        users testUser = new users();
        testUser.setUser_id(1L);
        testUser.setUsername("testuser");
        testUser.setFirstname("John");
        testUser.setLastname("Doe");
        when(userRepository.findUserByUsername("testuser")).thenReturn(testUser);

        // Create mock cart with userselection
        carts mockCart = new carts();
        mockCart.setCart_id(1L);
        mockCart.setUsers(testUser);
        
        userselections mockUserSelection = new userselections();
        mockUserSelection.setUsersel_id(1L);
        mockUserSelection.setNbPersons(2);
        mockUserSelection.setSeat_class("VIP");
        mockUserSelection.setAmount(150.0f);
        mockCart.setUserselections(mockUserSelection);

        // Mock offer
        offers mockOffer = new offers();
        mockOffer.setOffer_id(1L);
        mockOffer.setTitle("Olympic Final");
        mockOffer.setRate(50);
        mockOffer.setDescription("Final event");
        mockUserSelection.setOffers(mockOffer);

        // Mock event  
        events mockEvent = new events();
        mockEvent.setEvent_id(1L);
        mockEvent.setTitle("Swimming Final");
        mockEvent.setDescription("100m Freestyle Final");
        mockEvent.setDate(new java.sql.Date(System.currentTimeMillis()));
        mockUserSelection.setEvents(mockEvent);

        // Mock cart repository responses
        when(cartRepository.findById(1L)).thenReturn(java.util.Optional.of(mockCart));

        // Mock entity creation with ID setting
        doAnswer(invocation -> {
            keysgenerations keyGen = invocation.getArgument(0);
            keyGen.setKey_id(1L);
            return keyGen;
        }).when(keysgenerationRepository).save(any(keysgenerations.class));

        doAnswer(invocation -> {
            saleskeys saleKey = invocation.getArgument(0);
            saleKey.setSalekey_id(1L);
            return saleKey;
        }).when(saleskeyRepository).save(any(saleskeys.class));

        doAnswer(invocation -> {
            sales sale = invocation.getArgument(0);
            sale.setSale_id(1L);
            return sale;
        }).when(saleRepository).save(any(sales.class));

        doAnswer(invocation -> {
            tickets ticket = invocation.getArgument(0);
            ticket.setTicket_id(1L);
            return ticket;
        }).when(ticketRepository).save(any(tickets.class));

        // Mock QR code generation success
        doNothing().when(qrCodeGeneratorService).generateQRCodeImage(anyString(), anyString());

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("selectedCartIds", Arrays.asList(1));
        requestBody.put("ticketNumber", "TKT-TEST-001");

        mockMvc.perform(post("/api/user/tickets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Tickets created successfully with complete database workflow"))
                .andExpect(jsonPath("$.totalTickets").value(1))
                .andExpect(jsonPath("$.tickets").isArray())
                .andExpect(jsonPath("$.tickets[0].userFirstname").value("John"))
                .andExpect(jsonPath("$.tickets[0].userLastname").value("Doe"))
                .andExpect(jsonPath("$.tickets[0].offerTitle").value("Olympic Final"))
                .andExpect(jsonPath("$.tickets[0].eventTitle").value("Swimming Final"));

        // Verify cart deletion
        verify(cartRepository).delete(mockCart);
    }

    @Test
    void testCreateTicketForUser_EmptySelectedCartIds() throws Exception {
        // Setup authentication
        Authentication auth = mock(Authentication.class);
        when(auth.getName()).thenReturn("testuser");
        SecurityContextHolder.getContext().setAuthentication(auth);

        // Mock user lookup
        users testUser = new users();
        testUser.setUser_id(1L);
        testUser.setUsername("testuser");
        when(userRepository.findUserByUsername("testuser")).thenReturn(testUser);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("selectedCartIds", new ArrayList<>());

        mockMvc.perform(post("/api/user/tickets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("No selected cart IDs provided"));
    }

    @Test
    void testCreateTicketForUser_NoAuthentication() throws Exception {
        // Clear authentication context
        SecurityContextHolder.clearContext();

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("selectedCartIds", Arrays.asList(1));

        // Should still return success due to error handling in createTicketForUser
        mockMvc.perform(post("/api/user/tickets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Tickets created successfully (with error recovery)"));
    }

    @Test
    void testCreateTicketForUser_UserNotFound() throws Exception {
        // Setup authentication
        Authentication auth = mock(Authentication.class);
        when(auth.getName()).thenReturn("nonexistent-user");
        SecurityContextHolder.getContext().setAuthentication(auth);

        // Mock user not found
        when(userRepository.findUserByUsername("nonexistent-user")).thenReturn(null);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("selectedCartIds", Arrays.asList(1));

        mockMvc.perform(post("/api/user/tickets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(status().isNotFound())
                .andExpect(content().string("User not found"));
    }

    @Test
    void testCreateTicketForUser_CartNotFound() throws Exception {
        // Setup authentication
        Authentication auth = mock(Authentication.class);
        when(auth.getName()).thenReturn("testuser");
        SecurityContextHolder.getContext().setAuthentication(auth);

        // Mock user lookup
        users testUser = new users();
        testUser.setUser_id(1L);
        testUser.setUsername("testuser");
        when(userRepository.findUserByUsername("testuser")).thenReturn(testUser);

        // Mock cart not found
        when(cartRepository.findById(999L)).thenReturn(java.util.Optional.empty());

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("selectedCartIds", Arrays.asList(999));

        mockMvc.perform(post("/api/user/tickets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("No valid selected carts found for user"));
    }

    @Test
    void testCreateTicketForUser_CartBelongsToOtherUser() throws Exception {
        // Setup authentication
        Authentication auth = mock(Authentication.class);
        when(auth.getName()).thenReturn("testuser");
        SecurityContextHolder.getContext().setAuthentication(auth);

        // Mock user lookup
        users testUser = new users();
        testUser.setUser_id(1L);
        testUser.setUsername("testuser");
        when(userRepository.findUserByUsername("testuser")).thenReturn(testUser);

        // Create cart that belongs to different user
        users otherUser = new users();
        otherUser.setUser_id(2L);
        
        carts otherUserCart = new carts();
        otherUserCart.setCart_id(1L);
        otherUserCart.setUsers(otherUser);
        
        when(cartRepository.findById(1L)).thenReturn(java.util.Optional.of(otherUserCart));

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("selectedCartIds", Arrays.asList(1));

        mockMvc.perform(post("/api/user/tickets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("No valid selected carts found for user"));
    }

    @Test
    void testCreateTicketForUser_QRCodeGenerationFailure() throws Exception {
        // Setup authentication
        Authentication auth = mock(Authentication.class);
        when(auth.getName()).thenReturn("testuser");
        SecurityContextHolder.getContext().setAuthentication(auth);

        // Mock user lookup
        users testUser = new users();
        testUser.setUser_id(1L);
        testUser.setUsername("testuser");
        testUser.setFirstname("John");
        testUser.setLastname("Doe");
        when(userRepository.findUserByUsername("testuser")).thenReturn(testUser);

        // Create mock cart with minimal data
        carts mockCart = new carts();
        mockCart.setCart_id(1L);
        mockCart.setUsers(testUser);
        
        userselections mockUserSelection = new userselections();
        mockUserSelection.setUsersel_id(1L);
        mockCart.setUserselections(mockUserSelection);

        when(cartRepository.findById(1L)).thenReturn(java.util.Optional.of(mockCart));

        // Mock entity creation
        doAnswer(invocation -> {
            keysgenerations keyGen = invocation.getArgument(0);
            keyGen.setKey_id(1L);
            return keyGen;
        }).when(keysgenerationRepository).save(any(keysgenerations.class));

        doAnswer(invocation -> {
            saleskeys saleKey = invocation.getArgument(0);
            saleKey.setSalekey_id(1L);
            return saleKey;
        }).when(saleskeyRepository).save(any(saleskeys.class));

        doAnswer(invocation -> {
            sales sale = invocation.getArgument(0);
            sale.setSale_id(1L);
            return sale;
        }).when(saleRepository).save(any(sales.class));

        doAnswer(invocation -> {
            tickets ticket = invocation.getArgument(0);
            ticket.setTicket_id(1L);
            return ticket;
        }).when(ticketRepository).save(any(tickets.class));

        // Mock QR code generation failure - throw Exception (not RuntimeException) so it gets caught
        doThrow(new Exception("QR Code generation failed")).when(qrCodeGeneratorService).generateQRCodeImage(anyString(), anyString());

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("selectedCartIds", Arrays.asList(1));

        // Should still return success even with QR code failure
        mockMvc.perform(post("/api/user/tickets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Tickets created successfully with complete database workflow"));
    }

    @Test
    void testCreateTicketForUser_DatabaseException() throws Exception {
        // Setup authentication
        Authentication auth = mock(Authentication.class);
        when(auth.getName()).thenReturn("testuser");
        SecurityContextHolder.getContext().setAuthentication(auth);

        // Mock user lookup
        users testUser = new users();
        testUser.setUser_id(1L);
        testUser.setUsername("testuser");
        testUser.setFirstname("John");
        testUser.setLastname("Doe");
        when(userRepository.findUserByUsername("testuser")).thenReturn(testUser);

        // Create mock cart
        carts mockCart = new carts();
        mockCart.setCart_id(1L);
        mockCart.setUsers(testUser);
        
        userselections mockUserSelection = new userselections();
        mockUserSelection.setUsersel_id(1L);
        mockCart.setUserselections(mockUserSelection);

        when(cartRepository.findById(1L)).thenReturn(java.util.Optional.of(mockCart));

        // Mock database exception during keysgenerations save
        when(keysgenerationRepository.save(any(keysgenerations.class))).thenThrow(new RuntimeException("Database connection failed"));

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("selectedCartIds", Arrays.asList(1));

        // Should still return success due to error handling
        mockMvc.perform(post("/api/user/tickets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Tickets created successfully (with error recovery)"))
                .andExpect(jsonPath("$.tickets").isArray());
    }

    // ========================================================================================
    // COMPREHENSIVE TESTS FOR MAJOR UNCOVERED METHODS (678 missed instructions total)
    // ========================================================================================

    @Test
    @WithMockUser(username = "testuser")
    public void testGetUserCarts_Success() throws Exception {
        // Mock authentication
        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn("testuser");
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        // Setup test user
        users mockUser = new users();
        mockUser.setUser_id(1L);
        mockUser.setUsername("testuser");
        mockUser.setFirstname("John");
        mockUser.setLastname("Doe");

        // Setup a simple cart
        carts mockCart = new carts();
        mockCart.setCart_id(1L);
        mockCart.setUsers(mockUser);
        mockCart.setUserselections(null); // Simple empty cart
        mockCart.setTotalAmount(0.0f);

        when(userRepository.findUserByUsername("testuser")).thenReturn(mockUser);
        when(cartService.findByUser(mockUser)).thenReturn(Arrays.asList(mockCart));

        mockMvc.perform(get("/api/user/carts/user"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].cart_id").value(1));

        SecurityContextHolder.clearContext();
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testGetSalesSummary_EmptyResults() throws Exception {
        when(saleRepository.findAllWithDetails()).thenReturn(Arrays.asList());

        mockMvc.perform(get("/api/user/admin/sales-summary"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalSales").value(0.0))
                .andExpect(jsonPath("$.salesCount").value(0))
                .andExpect(jsonPath("$.salesList").isArray())
                .andExpect(jsonPath("$.salesList").isEmpty());
    }

    @Test 
    @WithMockUser(username = "testuser")
    public void testCompleteCartSelection_UserNotFound() throws Exception {
        // Mock authentication properly
        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn("testuser");
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        when(userRepository.findUserByUsername("testuser")).thenReturn(null);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("event_id", 1);
        requestBody.put("nb_persons", 2);
        requestBody.put("seat_class", "price1");
        requestBody.put("amount", 200.0);

        mockMvc.perform(put("/api/user/carts/1/complete")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("User not found"));
        
        // Clean up
        SecurityContextHolder.clearContext();
    }

    @Test 
    @WithMockUser(username = "testuser")
    public void testCompleteCartSelection_Success() throws Exception {
        // Mock authentication properly
        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn("testuser");
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        // Setup test user
        users mockUser = new users();
        mockUser.setUser_id(1L);
        mockUser.setUsername("testuser");
        mockUser.setFirstname("John");
        mockUser.setLastname("Doe");

        // Setup test cart
        carts mockCart = new carts();
        mockCart.setCart_id(1L);
        mockCart.setUsers(mockUser);

        // Setup test event
        events mockEvent = new events();
        mockEvent.setEvent_id(1L);
        mockEvent.setTitle("Test Event");

        // Setup test offer
        offers mockOffer = new offers();
        mockOffer.setOffer_id(1L);
        mockOffer.setTitle("Test Offer");
        mockOffer.setRate(100);

        // Setup test userselection
        userselections mockUserSelection = new userselections();
        mockUserSelection.setUsersel_id(1L);
        mockUserSelection.setOffers(mockOffer);
        mockUser.setUserselections(mockUserSelection);

        when(userRepository.findUserByUsername("testuser")).thenReturn(mockUser);
        when(cartService.getCartById(1L)).thenReturn(mockCart);
        when(eventRepository.findById(1L)).thenReturn(Optional.of(mockEvent));
        when(userSelectionService.calculateAmount(any(userselections.class))).thenReturn(200.0f);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("event_id", 1);
        requestBody.put("nb_persons", 2);
        requestBody.put("seat_class", "price1");
        requestBody.put("amount", 200.0);

        mockMvc.perform(put("/api/user/carts/1/complete")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(status().isOk());
        
        // Clean up
        SecurityContextHolder.clearContext();
    }

    @Test 
    @WithMockUser(username = "testuser")
    public void testCompleteCartSelection_CartNotFound() throws Exception {
        // Mock authentication properly
        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn("testuser");
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        // Setup test user
        users mockUser = new users();
        mockUser.setUser_id(1L);
        mockUser.setUsername("testuser");

        when(userRepository.findUserByUsername("testuser")).thenReturn(mockUser);
        when(cartService.getCartById(999L)).thenReturn(null);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("event_id", 1);
        requestBody.put("nb_persons", 2);
        requestBody.put("seat_class", "price1");
        requestBody.put("amount", 200.0);

        mockMvc.perform(put("/api/user/carts/999/complete")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Cart not found"));
        
        // Clean up
        SecurityContextHolder.clearContext();
    }

    @Test 
    @WithMockUser(username = "testuser")
    public void testGetSalesSummary_WithData() throws Exception {
        // Mock authentication properly for admin access
        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn("admin");
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        // Setup test sales data with proper nested structure
        users mockUser = new users();
        mockUser.setFirstname("John");
        mockUser.setLastname("Doe");
        
        events mockEvent = new events();
        mockEvent.setTitle("Test Event");
        
        userselections mockUserSel1 = new userselections();
        mockUserSel1.setAmount(100.50f);
        mockUserSel1.setEvents(mockEvent);
        
        carts mockCart1 = new carts();
        mockCart1.setUsers(mockUser);
        mockCart1.setUserselections(mockUserSel1);
        mockCart1.setTotalAmount(100.50);
        
        sales mockSale1 = new sales();
        mockSale1.setSale_id(1L);
        mockSale1.setCarts(mockCart1);
        
        userselections mockUserSel2 = new userselections();
        mockUserSel2.setAmount(75.25f);
        mockUserSel2.setEvents(mockEvent);
        
        carts mockCart2 = new carts();
        mockCart2.setUsers(mockUser);
        mockCart2.setUserselections(mockUserSel2);
        mockCart2.setTotalAmount(75.25);
        
        sales mockSale2 = new sales();
        mockSale2.setSale_id(2L);
        mockSale2.setCarts(mockCart2);

        List<sales> mockSales = List.of(mockSale1, mockSale2);
        when(saleRepository.findAllWithDetails()).thenReturn(mockSales);

        mockMvc.perform(get("/api/user/admin/sales-summary")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalSales").value(175.75)) // 100.50 + 75.25
                .andExpect(jsonPath("$.salesList").isArray())
                .andExpect(jsonPath("$.salesList.length()").value(2));
        
        // Clean up
        SecurityContextHolder.clearContext();
    }

    @Test 
    @WithMockUser(username = "testuser")
    public void testGetUserCarts_WithUserSelections() throws Exception {
        // Mock authentication properly
        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn("testuser");
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        // Setup test user
        users mockUser = new users();
        mockUser.setUser_id(1L);
        mockUser.setUsername("testuser");

        // Setup test cart with userselections
        carts mockCart = new carts();
        mockCart.setCart_id(1L);
        mockCart.setUsers(mockUser);
        mockCart.setTotalAmount(250.0f);

        // Setup userselections
        userselections mockUserSelection = new userselections();
        mockUserSelection.setUsersel_id(1L);
        mockUserSelection.setNbPersons(2);
        mockUserSelection.setSeat_class("price1");

        // Setup offer and event
        offers mockOffer = new offers();
        mockOffer.setOffer_id(1L);
        mockOffer.setTitle("Test Offer");
        mockUserSelection.setOffers(mockOffer);

        events mockEvent = new events();
        mockEvent.setEvent_id(1L);
        mockEvent.setTitle("Test Event");
        mockUserSelection.setEvents(mockEvent);

        mockUser.setUserselections(mockUserSelection);

        when(userRepository.findUserByUsername("testuser")).thenReturn(mockUser);
        when(cartService.findByUser(mockUser)).thenReturn(List.of(mockCart));

        mockMvc.perform(get("/api/user/carts/user")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].cart_id").value(1))
                .andExpect(jsonPath("$[0].totalAmount").value(250.0));
        
        // Clean up
        SecurityContextHolder.clearContext();
    }

    // ===== ADVANCED CREATECARTFORUSER TESTS =====
    // Targeting 360 missed instructions for 80% coverage

    @Test
    void testCreateCartForUser_WithExistingUserSelections() throws Exception {
        // Setup authentication
        Authentication auth = mock(Authentication.class);
        when(auth.getName()).thenReturn("testuser");
        SecurityContextHolder.getContext().setAuthentication(auth);

        // Mock user with existing userselections
        users testUser = new users();
        testUser.setUser_id(1L);
        testUser.setUsername("testuser");

        userselections existingUserSelection = new userselections();
        existingUserSelection.setUsersel_id(1L);
        offers existingOffer = new offers();
        existingOffer.setOffer_id(2L);
        existingUserSelection.setOffers(existingOffer);
        testUser.setUserselections(existingUserSelection);

        when(userRepository.findUserByUsername("testuser")).thenReturn(testUser);

        // Mock offer lookup
        offers testOffer = new offers();
        testOffer.setOffer_id(1L);
        when(offerRepository.findById(1L)).thenReturn(java.util.Optional.of(testOffer));

        // Mock empty cart list
        when(cartRepository.findByUsers(testUser)).thenReturn(new ArrayList<>());

        // Mock userselection creation with ID setting
        doAnswer(invocation -> {
            userselections usersel = invocation.getArgument(0);
            usersel.setUsersel_id(2L); // Set the ID after "saving"
            return null;
        }).when(userSelectionService).createUserSelection(any(userselections.class));

        // Mock cart creation with ID setting
        doAnswer(invocation -> {
            carts cart = invocation.getArgument(0);
            cart.setCart_id(1L); // Set the ID after "saving"
            return null;
        }).when(cartService).createCart(any(carts.class));

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("offer_id", 1L);
        requestBody.put("nb_persons", 3);

        mockMvc.perform(post("/api/user/carts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(status().isOk());
    }

    @Test
    void testCreateCartForUser_WithEventId() throws Exception {
        // Setup authentication
        Authentication auth = mock(Authentication.class);
        when(auth.getName()).thenReturn("testuser");
        SecurityContextHolder.getContext().setAuthentication(auth);

        users testUser = new users();
        testUser.setUser_id(1L);
        when(userRepository.findUserByUsername("testuser")).thenReturn(testUser);

        // Mock offer and event lookup
        offers testOffer = new offers();
        testOffer.setOffer_id(1L);
        when(offerRepository.findById(1L)).thenReturn(java.util.Optional.of(testOffer));

        events testEvent = new events();
        testEvent.setEvent_id(1L);
        when(eventRepository.findById(1L)).thenReturn(java.util.Optional.of(testEvent));

        when(cartRepository.findByUsers(testUser)).thenReturn(new ArrayList<>());

        // Mock userselection creation with ID setting
        doAnswer(invocation -> {
            userselections usersel = invocation.getArgument(0);
            usersel.setUsersel_id(1L); // Set the ID after "saving"
            return null;
        }).when(userSelectionService).createUserSelection(any(userselections.class));

        // Mock cart creation with ID setting
        doAnswer(invocation -> {
            carts cart = invocation.getArgument(0);
            cart.setCart_id(1L); // Set the ID after "saving"
            return null;
        }).when(cartService).createCart(any(carts.class));

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("offer_id", 1L);
        requestBody.put("event_id", 1L);
        requestBody.put("nb_persons", 2);
        requestBody.put("seat_class", "price2");
        requestBody.put("amount", 150.0);

        mockMvc.perform(post("/api/user/carts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(status().isOk());
    }

    @Test
    void testCreateCartForUser_DatabaseException() throws Exception {
        // Setup authentication
        Authentication auth = mock(Authentication.class);
        when(auth.getName()).thenReturn("testuser");
        SecurityContextHolder.getContext().setAuthentication(auth);

        // Mock database exception
        when(userRepository.findUserByUsername("testuser")).thenThrow(new RuntimeException("Database error"));

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("offer_id", 1L);

        mockMvc.perform(post("/api/user/carts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.error").value(containsString("Error finding user")));
    }

    @Test
    void testCreateCartForUser_AnonymousUser() throws Exception {
        // Setup authentication with anonymous user
        Authentication auth = mock(Authentication.class);
        when(auth.getName()).thenReturn("anonymousUser");
        SecurityContextHolder.getContext().setAuthentication(auth);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("offer_id", 1L);

        mockMvc.perform(post("/api/user/carts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.error").value("User not authenticated"));
    }

    @Test
    void testCreateCartForUser_EmptyUsername() throws Exception {
        // Setup authentication with empty username
        Authentication auth = mock(Authentication.class);
        when(auth.getName()).thenReturn("");
        SecurityContextHolder.getContext().setAuthentication(auth);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("offer_id", 1L);

        mockMvc.perform(post("/api/user/carts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.error").value("User not authenticated"));
    }

    @Test
    void testCreateCartForUser_NullUsername() throws Exception {
        // Setup authentication with null username
        Authentication auth = mock(Authentication.class);
        when(auth.getName()).thenReturn(null);
        SecurityContextHolder.getContext().setAuthentication(auth);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("offer_id", 1L);

        mockMvc.perform(post("/api/user/carts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.error").value("User not authenticated"));
    }

    @Test
    void testCreateCartForUser_EventNotFoundException() throws Exception {
        // Setup authentication
        Authentication auth = mock(Authentication.class);
        when(auth.getName()).thenReturn("testuser");
        SecurityContextHolder.getContext().setAuthentication(auth);

        users testUser = new users();
        testUser.setUser_id(1L);
        when(userRepository.findUserByUsername("testuser")).thenReturn(testUser);

        offers testOffer = new offers();
        testOffer.setOffer_id(1L);
        when(offerRepository.findById(1L)).thenReturn(java.util.Optional.of(testOffer));

        // Mock event not found - this tests the exception handling path
        when(eventRepository.findById(999L)).thenThrow(new RuntimeException("Event lookup failed"));

        when(cartRepository.findByUsers(testUser)).thenReturn(new ArrayList<>());

        // Mock userselection creation with ID setting
        doAnswer(invocation -> {
            userselections usersel = invocation.getArgument(0);
            usersel.setUsersel_id(1L); // Set the ID after "saving"
            return null;
        }).when(userSelectionService).createUserSelection(any(userselections.class));

        // Mock cart creation with ID setting
        doAnswer(invocation -> {
            carts cart = invocation.getArgument(0);
            cart.setCart_id(1L); // Set the ID after "saving"
            return null;
        }).when(cartService).createCart(any(carts.class));

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("offer_id", 1L);
        requestBody.put("event_id", 999L); // Non-existent event that throws exception

        mockMvc.perform(post("/api/user/carts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(status().isOk()); // Should continue without event for partial cart
    }

    @Test
    void testCreateCartForUser_DifferentSeatClasses() throws Exception {
        // Test all seat class variations
        String[] seatClasses = {"price1", "price2", "price3"};
        
        for (String seatClass : seatClasses) {
            // Setup authentication
            Authentication auth = mock(Authentication.class);
            when(auth.getName()).thenReturn("testuser");
            SecurityContextHolder.getContext().setAuthentication(auth);

            users testUser = new users();
            testUser.setUser_id(1L);
            when(userRepository.findUserByUsername("testuser")).thenReturn(testUser);

            offers testOffer = new offers();
            testOffer.setOffer_id(1L);
            when(offerRepository.findById(1L)).thenReturn(java.util.Optional.of(testOffer));

            when(cartRepository.findByUsers(testUser)).thenReturn(new ArrayList<>());

            // Mock userselection creation with ID setting
            doAnswer(invocation -> {
                userselections usersel = invocation.getArgument(0);
                usersel.setUsersel_id(1L); // Set the ID after "saving"
                return null;
            }).when(userSelectionService).createUserSelection(any(userselections.class));

            // Mock cart creation with ID setting
            doAnswer(invocation -> {
                carts cart = invocation.getArgument(0);
                cart.setCart_id(1L); // Set the ID after "saving"
                return null;
            }).when(cartService).createCart(any(carts.class));

            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("offer_id", 1L);
            requestBody.put("seat_class", seatClass);
            requestBody.put("nb_persons", 1);

            mockMvc.perform(post("/api/user/carts")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(requestBody)))
                    .andExpect(status().isOk());
            
            // Clear mocks for next iteration
            reset(userRepository, offerRepository, cartRepository, userSelectionService, cartService);
        }
    }

    // ===== ADVANCED GETUSERCARTS TESTS =====
    // Targeting 316 missed instructions

    @Test
    void testGetUserCarts_WithCeremonies() throws Exception {
        Authentication auth = mock(Authentication.class);
        when(auth.getName()).thenReturn("testuser");
        SecurityContextHolder.getContext().setAuthentication(auth);

        users testUser = new users();
        testUser.setUser_id(1L);
        testUser.setUsername("testuser");
        when(userRepository.findUserByUsername("testuser")).thenReturn(testUser);

        // Create cart with ceremony event
        carts cart = new carts();
        cart.setCart_id(1L);
        cart.setUsers(testUser);
        cart.setTotalAmount(300.0f);

        userselections userSel = new userselections();
        userSel.setUsersel_id(1L);
        userSel.setNbPersons(2);
        userSel.setSeat_class("price1");

        // Create ceremony event
        ceremonies ceremony = new ceremonies();
        ceremony.setCerem_id(1L);
        ceremony.setName("Opening Ceremony");

        events event = new events();
        event.setEvent_id(1L);
        event.setTitle("Ceremony Event");
        event.setCeremonies(ceremony);
        userSel.setEvents(event);

        offers offer = new offers();
        offer.setOffer_id(1L);
        offer.setTitle("Ceremony Offer");
        userSel.setOffers(offer);

        cart.setUserselections(userSel);

        when(cartService.findByUser(testUser)).thenReturn(List.of(cart));

        mockMvc.perform(get("/api/user/carts/user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].cart_id").value(1));
    }

    @Test
    void testGetUserCarts_WithSportsEvents() throws Exception {
        Authentication auth = mock(Authentication.class);
        when(auth.getName()).thenReturn("testuser");
        SecurityContextHolder.getContext().setAuthentication(auth);

        users testUser = new users();
        testUser.setUser_id(1L);
        when(userRepository.findUserByUsername("testuser")).thenReturn(testUser);

        // Create cart with sports event
        carts cart = new carts();
        cart.setCart_id(1L);
        cart.setUsers(testUser);

        userselections userSel = new userselections();
        userSel.setUsersel_id(1L);

        // Create sports event (no ceremonies)
        events event = new events();
        event.setEvent_id(1L);
        event.setTitle("Swimming Event");
        event.setCeremonies(null); // Sports event
        userSel.setEvents(event);

        offers offer = new offers();
        offer.setOffer_id(1L);
        userSel.setOffers(offer);

        cart.setUserselections(userSel);

        when(cartService.findByUser(testUser)).thenReturn(List.of(cart));

        mockMvc.perform(get("/api/user/carts/user"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetUserCarts_MultipleCarts() throws Exception {
        Authentication auth = mock(Authentication.class);
        when(auth.getName()).thenReturn("testuser");
        SecurityContextHolder.getContext().setAuthentication(auth);

        users testUser = new users();
        testUser.setUser_id(1L);
        when(userRepository.findUserByUsername("testuser")).thenReturn(testUser);

        // Create multiple carts
        List<carts> cartsList = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            carts cart = new carts();
            cart.setCart_id((long) i);
            cart.setUsers(testUser);
            cart.setTotalAmount(100.0f * i);

            userselections userSel = new userselections();
            userSel.setUsersel_id((long) i);
            userSel.setNbPersons(i);

            offers offer = new offers();
            offer.setOffer_id((long) i);
            userSel.setOffers(offer);

            cart.setUserselections(userSel);
            cartsList.add(cart);
        }

        when(cartService.findByUser(testUser)).thenReturn(cartsList);

        mockMvc.perform(get("/api/user/carts/user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(3));
    }

    @Test
    void testGetUserCarts_WithoutUserSelections() throws Exception {
        Authentication auth = mock(Authentication.class);
        when(auth.getName()).thenReturn("testuser");
        SecurityContextHolder.getContext().setAuthentication(auth);

        users testUser = new users();
        testUser.setUser_id(1L);
        when(userRepository.findUserByUsername("testuser")).thenReturn(testUser);

        // Create cart without userselections
        carts cart = new carts();
        cart.setCart_id(1L);
        cart.setUsers(testUser);
        cart.setUserselections(null); // No userselections

        when(cartService.findByUser(testUser)).thenReturn(List.of(cart));

        mockMvc.perform(get("/api/user/carts/user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].cart_id").value(1));
    }

    @Test
    void testGetUserCarts_WithoutOffers() throws Exception {
        Authentication auth = mock(Authentication.class);
        when(auth.getName()).thenReturn("testuser");
        SecurityContextHolder.getContext().setAuthentication(auth);

        users testUser = new users();
        testUser.setUser_id(1L);
        when(userRepository.findUserByUsername("testuser")).thenReturn(testUser);

        carts cart = new carts();
        cart.setCart_id(1L);
        cart.setUsers(testUser);

        userselections userSel = new userselections();
        userSel.setUsersel_id(1L);
        userSel.setOffers(null); // No offers

        cart.setUserselections(userSel);

        when(cartService.findByUser(testUser)).thenReturn(List.of(cart));

        mockMvc.perform(get("/api/user/carts/user"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetUserCarts_WithoutEvents() throws Exception {
        Authentication auth = mock(Authentication.class);
        when(auth.getName()).thenReturn("testuser");
        SecurityContextHolder.getContext().setAuthentication(auth);

        users testUser = new users();
        testUser.setUser_id(1L);
        when(userRepository.findUserByUsername("testuser")).thenReturn(testUser);

        carts cart = new carts();
        cart.setCart_id(1L);
        cart.setUsers(testUser);

        userselections userSel = new userselections();
        userSel.setUsersel_id(1L);

        offers offer = new offers();
        offer.setOffer_id(1L);
        userSel.setOffers(offer);
        userSel.setEvents(null); // No events

        cart.setUserselections(userSel);

        when(cartService.findByUser(testUser)).thenReturn(List.of(cart));

        mockMvc.perform(get("/api/user/carts/user"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetUserCarts_CompletionStatusChecking() throws Exception {
        Authentication auth = mock(Authentication.class);
        when(auth.getName()).thenReturn("testuser");
        SecurityContextHolder.getContext().setAuthentication(auth);

        users testUser = new users();
        testUser.setUser_id(1L);
        when(userRepository.findUserByUsername("testuser")).thenReturn(testUser);

        carts cart = new carts();
        cart.setCart_id(1L);
        cart.setUsers(testUser);

        userselections userSel = new userselections();
        userSel.setUsersel_id(1L);
        userSel.setNbPersons(2); // Has persons
        userSel.setSeat_class("price1"); // Has seat class

        events event = new events();
        event.setEvent_id(1L);
        userSel.setEvents(event); // Has event

        offers offer = new offers();
        offer.setOffer_id(1L);
        userSel.setOffers(offer); // Has offer

        cart.setUserselections(userSel);

        when(cartService.findByUser(testUser)).thenReturn(List.of(cart));

        mockMvc.perform(get("/api/user/carts/user"))
                .andExpect(status().isOk());
    }

    // ===== ADVANCED LOGOUT TESTS =====
    // Targeting 113 missed instructions

    @Test
    void testLogout_WithBearerToken() throws Exception {
        // Mock JWT utils
        when(jwtUtils.extractUsername("validtoken")).thenReturn("testuser");

        Map<String, String> request = new HashMap<>();
        request.put("token", "Bearer validtoken");

        mockMvc.perform(post("/api/user/logout")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    void testLogout_WithTokenNoBearer() throws Exception {
        when(jwtUtils.extractUsername("validtoken")).thenReturn("testuser");

        Map<String, String> request = new HashMap<>();
        request.put("token", "validtoken");

        mockMvc.perform(post("/api/user/logout")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    void testLogout_JwtException() throws Exception {
        when(jwtUtils.extractUsername(anyString())).thenThrow(new RuntimeException("JWT parsing error"));

        Map<String, String> request = new HashMap<>();
        request.put("token", "invalidtoken");

        mockMvc.perform(post("/api/user/logout")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    void testLogout_NullUsernameFromToken() throws Exception {
        when(jwtUtils.extractUsername("tokenwithnulluser")).thenReturn(null);

        Map<String, String> request = new HashMap<>();
        request.put("token", "Bearer tokenwithnulluser");

        mockMvc.perform(post("/api/user/logout")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    void testLogout_EmptyToken() throws Exception {
        Map<String, String> request = new HashMap<>();
        request.put("token", "");

        mockMvc.perform(post("/api/user/logout")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("No token provided, but logout successful"));
    }
}
