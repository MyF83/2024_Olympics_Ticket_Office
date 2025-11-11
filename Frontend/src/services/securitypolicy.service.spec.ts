import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { SecuritypolicyService } from './securitypolicy.service';

describe('SecuritypolicyService', () => {
  let service: SecuritypolicyService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [SecuritypolicyService]
    });
    service = TestBed.inject(SecuritypolicyService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should get policies', () => {
    const mockPolicies = [
      { 
        policy_id: 1, 
        title: 'Privacy Policy', 
        content: 'We protect your privacy...',
        createdDate: '2024-01-01'
      },
      { 
        policy_id: 2, 
        title: 'Terms of Service', 
        content: 'By using our service...',
        createdDate: '2024-01-02'
      }
    ] as any;

    service.getPolicies().subscribe(policies => {
      expect(policies).toEqual(mockPolicies);
      expect(policies.length).toBe(2);
    });

    const req = httpMock.expectOne('/api/policy');
    expect(req.request.method).toBe('GET');
    req.flush(mockPolicies);
  });

  it('should handle error when getting policies fails', () => {
    service.getPolicies().subscribe(
      () => fail('expected an error, not policies'),
      error => {
        expect(error.status).toBe(403);
      }
    );

    const req = httpMock.expectOne('/api/policy');
    req.flush('Forbidden', { status: 403, statusText: 'Forbidden' });
  });

  it('should handle empty policies array', () => {
    const emptyPolicies: any[] = [];

    service.getPolicies().subscribe(policies => {
      expect(policies).toEqual([]);
      expect(policies.length).toBe(0);
    });

    const req = httpMock.expectOne('/api/policy');
    req.flush(emptyPolicies);
  });

  it('should handle malformed response', () => {
    service.getPolicies().subscribe(
      () => fail('expected an error, not policies'),
      error => {
        expect(error.status).toBe(400);
      }
    );

    const req = httpMock.expectOne('/api/policy');
    req.flush('Bad Request', { status: 400, statusText: 'Bad Request' });
  });
});
