import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { GameserviceService } from './gameservice.service';

describe('GameserviceService', () => {
  let service: GameserviceService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [GameserviceService]
    });
    service = TestBed.inject(GameserviceService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should get games', () => {
    const mockGames = [
      { id: 1, name: 'Football Final', venue: 'Stadium A', date: '2024-07-15' },
      { id: 2, name: 'Swimming Semi', venue: 'Pool B', date: '2024-07-16' }
    ] as any;

    service.getGames().subscribe(games => {
      expect(games).toEqual(mockGames);
      expect(games.length).toBe(2);
    });

    const req = httpMock.expectOne('/api/event');
    expect(req.request.method).toBe('GET');
    req.flush(mockGames);
  });

  it('should get game by id', () => {
    const mockGame = { 
      id: 1, 
      name: 'Football Final', 
      venue: 'Stadium A', 
      date: '2024-07-15',
      category: 'Football'
    } as any;

    service.getGameById(1).subscribe(game => {
      expect(game).toEqual(mockGame);
    });

    const req = httpMock.expectOne('/api/event/1');
    expect(req.request.method).toBe('GET');
    req.flush(mockGame);
  });

  it('should get all events', () => {
    const mockEvents = [
      { id: 1, title: 'Opening Ceremony', date: '2024-07-01' },
      { id: 2, title: 'Closing Ceremony', date: '2024-07-30' }
    ] as any;

    service.getEvents().subscribe(events => {
      expect(events).toEqual(mockEvents);
      expect(events.length).toBe(2);
    });

    const req = httpMock.expectOne('/api/event/all');
    expect(req.request.method).toBe('GET');
    req.flush(mockEvents);
  });

  it('should handle error when getting games fails', () => {
    service.getGames().subscribe(
      () => fail('expected an error, not games'),
      error => {
        expect(error.status).toBe(500);
      }
    );

    const req = httpMock.expectOne('/api/event');
    req.flush('Server Error', { status: 500, statusText: 'Internal Server Error' });
  });

  it('should handle error when getting game by id fails', () => {
    service.getGameById(999).subscribe(
      () => fail('expected an error, not a game'),
      error => {
        expect(error.status).toBe(404);
      }
    );

    const req = httpMock.expectOne('/api/event/999');
    req.flush('Not Found', { status: 404, statusText: 'Not Found' });
  });

  it('should handle error when getting all events fails', () => {
    service.getEvents().subscribe(
      () => fail('expected an error, not events'),
      error => {
        expect(error.status).toBe(503);
      }
    );

    const req = httpMock.expectOne('/api/event/all');
    req.flush('Service Unavailable', { status: 503, statusText: 'Service Unavailable' });
  });
});
