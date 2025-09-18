import { TestBed } from '@angular/core/testing';

import { CartsServiceService } from './cartsservice.service';

describe('CartsServiceService', () => {
  let service: CartsServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CartsServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
