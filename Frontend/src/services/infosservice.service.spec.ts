import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { InfosService } from './infosservice.service';

describe('InfosService', () => {
  let service: InfosService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [InfosService]
    });
    service = TestBed.inject(InfosService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should get sports', () => {
    const mockSports = [
      { id: 1, name: 'Football' },
      { id: 2, name: 'Basketball' }
    ];

    service.getSports().subscribe(sports => {
      expect(sports).toEqual(mockSports);
      expect(sports.length).toBe(2);
    });

    const req = httpMock.expectOne('/api/sport');
    expect(req.request.method).toBe('GET');
    req.flush(mockSports);
  });

  it('should get sites', () => {
    const mockSites = [
      { id: 1, name: 'Olympic Stadium' },
      { id: 2, name: 'Swimming Pool' }
    ];

    service.getSites().subscribe(sites => {
      expect(sites).toEqual(mockSites);
      expect(sites.length).toBe(2);
    });

    const req = httpMock.expectOne('/api/site');
    expect(req.request.method).toBe('GET');
    req.flush(mockSites);
  });

  it('should get sports with sites using forkJoin', () => {
    const mockSports = [{ id: 1, name: 'Football' }];
    const mockSites = [{ id: 1, name: 'Olympic Stadium' }];

    service.getSportsWithSites().subscribe(result => {
      expect(result.sport).toEqual(mockSports);
      expect(result.site).toEqual(mockSites);
    });

    const sportReq = httpMock.expectOne('/api/sport');
    const siteReq = httpMock.expectOne('/api/site');
    
    expect(sportReq.request.method).toBe('GET');
    expect(siteReq.request.method).toBe('GET');
    
    sportReq.flush(mockSports);
    siteReq.flush(mockSites);
  });

  it('should handle error when getting sports fails', () => {
    service.getSports().subscribe(
      () => fail('expected an error, not sports'),
      error => {
        expect(error.status).toBe(500);
      }
    );

    const req = httpMock.expectOne('/api/sport');
    req.flush('Error', { status: 500, statusText: 'Internal Server Error' });
  });

  it('should handle error when getting sites fails', () => {
    service.getSites().subscribe(
      () => fail('expected an error, not sites'),
      error => {
        expect(error.status).toBe(404);
      }
    );

    const req = httpMock.expectOne('/api/site');
    req.flush('Not Found', { status: 404, statusText: 'Not Found' });
  });
});
