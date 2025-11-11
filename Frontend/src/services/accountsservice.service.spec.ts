import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { AccountsServiceService } from './accountsservice.service';
import { AuthStateService } from './auth-state.service';

describe('AccountsServiceService', () => {
  let service: AccountsServiceService;
  let httpMock: HttpTestingController;
  let authStateService: jasmine.SpyObj<AuthStateService>;

  beforeEach(() => {
    const authSpy = jasmine.createSpyObj('AuthStateService', ['setLoggedIn']);

    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [
        AccountsServiceService,
        { provide: AuthStateService, useValue: authSpy }
      ]
    });
    service = TestBed.inject(AccountsServiceService);
    httpMock = TestBed.inject(HttpTestingController);
    authStateService = TestBed.inject(AuthStateService) as jasmine.SpyObj<AuthStateService>;
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should get accounts', () => {
    const mockAccounts = [
      { 
        user_id: 1, 
        firstname: 'John', 
        lastname: 'Doe', 
        username: 'johndoe',
        email: 'john@test.com',
        password: 'hashedPassword',
        phoneNumber: '1234567890',
        address: '123 Test St',
        creationDate: '2024-01-01',
        roles: { role_id: 1, name: 'user', description: 'Regular user' },
        userkeys: { userkey_id: 1, date: '2024-01-01', keysgenerations: { key_id: 1, keyGenerated: 123456 } },
        userselections: { usersel_id: 1, nbPersons: 2, amount: 100, offers: { offer_id: 1, title: 'Test Offer' } }
      } as any
    ];

    service.getAccounts().subscribe(accounts => {
      expect(accounts).toEqual(mockAccounts);
      expect(accounts.length).toBe(1);
    });

    const req = httpMock.expectOne('/api/user');
    expect(req.request.method).toBe('GET');
    req.flush(mockAccounts);
  });

  it('should login user successfully', () => {
    const mockResponse = { token: 'fake-jwt-token', user: { id: 1, username: 'testuser' } };
    
    service.login('testuser', 'password123').subscribe(response => {
      expect(response).toEqual(mockResponse);
      expect(authStateService.setLoggedIn).toHaveBeenCalledWith(true);
    });

    const req = httpMock.expectOne('/api/user/login');
    expect(req.request.method).toBe('POST');
    expect(req.request.body).toEqual({ username: 'testuser', password: 'password123' });
    expect(req.request.headers.get('Cache-Control')).toBe('no-cache, no-store, must-revalidate');
    req.flush(mockResponse);
  });

  it('should handle login failure', () => {
    service.login('wronguser', 'wrongpass').subscribe(
      () => fail('expected an error, not success'),
      error => {
        expect(error.status).toBe(401);
      }
    );

    const req = httpMock.expectOne('/api/user/login');
    req.flush('Unauthorized', { status: 401, statusText: 'Unauthorized' });
  });

  it('should register user', () => {
    const userData = {
      firstname: 'John',
      lastname: 'Doe', 
      email: 'john@test.com',
      password: 'password123',
      policyId: 1
    };
    const mockResponse = { 
      user_id: 1, 
      username: 'johndoe',
      ...userData,
      phoneNumber: '1234567890',
      address: '123 Test St',
      creationDate: '2024-01-01'
    } as any;

    service.registerUser(userData).subscribe(response => {
      expect(response).toEqual(mockResponse);
    });

    const req = httpMock.expectOne('/api/user/register');
    expect(req.request.method).toBe('POST');
    expect(req.request.body).toEqual(userData);
    req.flush(mockResponse);
  });

  it('should create account', () => {
    const accountData = {
      firstName: 'John',
      lastName: 'Doe',
      email: 'john@test.com',
      password: 'password123',
      phoneNumber: '1234567890',
      address: '123 Test St',
      city: 'Test City',
      postalCode: '12345',
      country: 'Test Country'
    };
    const mockResponse = { username: 'johndoe' };

    service.createAccount(accountData).subscribe(response => {
      expect(response).toEqual(mockResponse);
    });

    const req = httpMock.expectOne('/api/user/create');
    expect(req.request.method).toBe('POST');
    expect(req.request.body).toEqual(accountData);
    req.flush(mockResponse);
  });

  it('should get user by username', () => {
    const mockUser = { 
      user_id: 1, 
      username: 'testuser', 
      email: 'test@example.com',
      firstname: 'Test',
      lastname: 'User',
      password: 'hashedPassword',
      phoneNumber: '1234567890',
      address: '123 Test St',
      creationDate: '2024-01-01'
    } as any;

    service.getUserByUsername('testuser').subscribe(user => {
      expect(user).toEqual(mockUser);
    });

    const req = httpMock.expectOne('/api/user/username/testuser');
    expect(req.request.method).toBe('GET');
    req.flush(mockUser);
  });

  it('should get current user', () => {
    const mockUser = { id: 1, username: 'currentuser', email: 'current@example.com' };

    service.getCurrentUser().subscribe(user => {
      expect(user).toEqual(mockUser);
    });

    const req = httpMock.expectOne('/api/user/me');
    expect(req.request.method).toBe('GET');
    req.flush(mockUser);
  });

  it('should update user data', () => {
    const userData = { user_id: 1, firstname: 'UpdatedName', email: 'updated@test.com' };
    const mockResponse = { success: true, user: userData };

    service.updateDataUser(userData).subscribe(response => {
      expect(response).toEqual(mockResponse);
    });

    const req = httpMock.expectOne('/api/user/1');
    expect(req.request.method).toBe('PUT');
    expect(req.request.body).toEqual(userData);
    req.flush(mockResponse);
  });

  it('should create ticket', () => {
    const ticketData = { eventId: 1, quantity: 2 };
    const mockResponse = { ticketId: 123, status: 'created' };

    service.createTicket(ticketData).subscribe(response => {
      expect(response).toEqual(mockResponse);
    });

    const req = httpMock.expectOne('/api/user/tickets');
    expect(req.request.method).toBe('POST');
    expect(req.request.body).toEqual(ticketData);
    req.flush(mockResponse);
  });

  it('should get user tickets', () => {
    const mockTickets = [
      { id: 1, eventName: 'Football Final', quantity: 2 },
      { id: 2, eventName: 'Swimming Semi', quantity: 1 }
    ];

    service.getUserTickets(1).subscribe(tickets => {
      expect(tickets).toEqual(mockTickets);
      expect(tickets.length).toBe(2);
    });

    const req = httpMock.expectOne('/api/tickets/user/1');
    expect(req.request.method).toBe('GET');
    req.flush(mockTickets);
  });

  it('should logout successfully', () => {
    const mockResponse = { message: 'Logged out successfully' };
    spyOn(sessionStorage, 'getItem').and.returnValue('fake-token');

    service.logout().subscribe(response => {
      expect(response).toEqual(mockResponse);
      expect(authStateService.setLoggedIn).toHaveBeenCalledWith(false);
    });

    const req = httpMock.expectOne('/api/user/logout');
    expect(req.request.method).toBe('POST');
    expect(req.request.body).toEqual({ token: 'Bearer fake-token' });
    req.flush(mockResponse);
  });

  it('should logout gracefully when HTTP request fails', () => {
    spyOn(sessionStorage, 'getItem').and.returnValue('fake-token');

    service.logout().subscribe(response => {
      expect(response.message).toBe('Logged out locally');
      expect(authStateService.setLoggedIn).toHaveBeenCalledWith(false);
    });

    const req = httpMock.expectOne('/api/user/logout');
    req.flush('Server Error', { status: 500, statusText: 'Internal Server Error' });
  });
});
