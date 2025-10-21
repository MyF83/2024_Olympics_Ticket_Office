import { TestBed } from '@angular/core/testing';

import { InfosserviceService } from './infosservice.service';

describe('InfosserviceService', () => {
  let service: InfosserviceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(InfosserviceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
