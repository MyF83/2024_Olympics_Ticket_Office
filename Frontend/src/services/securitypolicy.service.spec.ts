import { TestBed } from '@angular/core/testing';

import { SecuritypolicyService } from './securitypolicy.service';

describe('SecuritypolicyService', () => {
  let service: SecuritypolicyService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SecuritypolicyService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
