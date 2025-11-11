import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { OfferserviceService } from './offerservice.service';

describe('OfferserviceService', () => {
  let service: OfferserviceService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [OfferserviceService]
    });
    service = TestBed.inject(OfferserviceService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should get offers', () => {
    const mockOffers = [
      { 
        offer_id: 1, 
        title: 'Premium Package', 
        description: 'Best seats in the house',
        price: 299.99
      },
      { 
        offer_id: 2, 
        title: 'Standard Package', 
        description: 'Good value for money',
        price: 149.99
      }
    ] as any;

    service.getOffers().subscribe(offers => {
      expect(offers).toEqual(mockOffers);
      expect(offers.length).toBe(2);
    });

    const req = httpMock.expectOne('/api/offer');
    expect(req.request.method).toBe('GET');
    req.flush(mockOffers);
  });

  it('should handle error when getting offers fails', () => {
    service.getOffers().subscribe(
      () => fail('expected an error, not offers'),
      error => {
        expect(error.status).toBe(500);
      }
    );

    const req = httpMock.expectOne('/api/offer');
    req.flush('Internal Server Error', { status: 500, statusText: 'Internal Server Error' });
  });

  it('should handle empty offers array', () => {
    const emptyOffers: any[] = [];

    service.getOffers().subscribe(offers => {
      expect(offers).toEqual([]);
      expect(offers.length).toBe(0);
    });

    const req = httpMock.expectOne('/api/offer');
    req.flush(emptyOffers);
  });

  it('should handle network timeout', () => {
    service.getOffers().subscribe(
      () => fail('expected an error, not offers'),
      error => {
        expect(error.status).toBe(0);
      }
    );

    const req = httpMock.expectOne('/api/offer');
    req.error(new ProgressEvent('timeout'), { status: 0, statusText: 'Unknown Error' });
  });
});
