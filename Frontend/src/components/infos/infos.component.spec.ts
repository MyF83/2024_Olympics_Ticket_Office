import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of, throwError } from 'rxjs';

import { InfosComponent } from './infos.component';
import { InfosService } from '../../services/infosservice.service';

describe('InfosComponent', () => {
  let component: InfosComponent;
  let fixture: ComponentFixture<InfosComponent>;
  let infosService: jasmine.SpyObj<InfosService>;

  const mockSportsData = [
    {
      sport_id: 1,
      name: 'Swimming',
      description: 'Aquatic sport with various strokes and distances',
      site_id: 1,
      sites: {
        site_id: 1,
        name: 'Aquatic Centre',
        city: 'Paris',
        address: '123 Pool Street',
        description: 'Modern swimming facility',
        postalCode: '75001',
        countries: { country_id: 1, name: 'France' },
        nbseatsc1: 5000,
        nbseatsc2: 3000,
        nbseatsc3: 2000
      }
    },
    {
      sport_id: 2,
      name: 'Athletics',
      description: 'Track and field events including running, jumping, and throwing',
      site_id: 2,
      sites: {
        site_id: 2,
        name: 'Olympic Stadium',
        city: 'Paris',
        address: '456 Athletic Avenue',
        description: 'Main stadium for athletics events',
        postalCode: '75002',
        countries: { country_id: 1, name: 'France' },
        nbseatsc1: 15000,
        nbseatsc2: 10000,
        nbseatsc3: 8000
      }
    },
    {
      sport_id: 3,
      name: 'Basketball',
      description: 'Team sport played on a court with hoops',
      site_id: 3,
      sites: {
        site_id: 3,
        name: 'Basketball Arena',
        city: 'Lyon',
        address: '789 Court Road',
        description: 'Indoor basketball venue',
        postalCode: '69001',
        countries: { country_id: 1, name: 'France' },
        nbseatsc1: 8000,
        nbseatsc2: 5000,
        nbseatsc3: 3000
      }
    }
  ];

  beforeEach(async () => {
    const infosServiceSpy = jasmine.createSpyObj('InfosService', ['getSports']);

    await TestBed.configureTestingModule({
      imports: [InfosComponent, HttpClientTestingModule],
      providers: [
        { provide: InfosService, useValue: infosServiceSpy }
      ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(InfosComponent);
    component = fixture.componentInstance;
    
    infosService = TestBed.inject(InfosService) as jasmine.SpyObj<InfosService>;
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  describe('ngOnInit', () => {
    it('should load sports data successfully', () => {
      infosService.getSports.and.returnValue(of(mockSportsData));

      component.ngOnInit();

      expect(infosService.getSports).toHaveBeenCalled();
      expect(component.sportsWithSites).toEqual(mockSportsData);
      expect(component.filteredSports).toEqual(mockSportsData);
    });

    it('should handle error when loading sports data fails', () => {
      const errorResponse = { error: 'Failed to load sports data' };
      infosService.getSports.and.returnValue(throwError(errorResponse));
      spyOn(console, 'error');

      component.ngOnInit();

      expect(infosService.getSports).toHaveBeenCalled();
      expect(console.error).toHaveBeenCalledWith('Error fetching data:', errorResponse);
      expect(component.sportsWithSites).toEqual([]);
      expect(component.filteredSports).toEqual([]);
    });
  });

  describe('filterSports', () => {
    beforeEach(() => {
      component.sportsWithSites = mockSportsData;
      component.filteredSports = [...mockSportsData];
    });

    it('should show all sports when search term is empty', () => {
      component.searchTerm = '';
      component.filterSports();

      expect(component.filteredSports).toEqual(mockSportsData);
    });

    it('should show all sports when search term is only whitespace', () => {
      component.searchTerm = '   ';
      component.filterSports();

      expect(component.filteredSports).toEqual(mockSportsData);
    });

    it('should filter sports by name (case insensitive)', () => {
      component.searchTerm = 'swim';
      component.filterSports();

      expect(component.filteredSports.length).toBe(1);
      expect(component.filteredSports[0].name).toBe('Swimming');
    });

    it('should filter sports by description', () => {
      component.searchTerm = 'track';
      component.filterSports();

      expect(component.filteredSports.length).toBe(1);
      expect(component.filteredSports[0].name).toBe('Athletics');
    });

    it('should filter sports by site name', () => {
      component.searchTerm = 'aquatic';
      component.filterSports();

      expect(component.filteredSports.length).toBe(1);
      expect(component.filteredSports[0].name).toBe('Swimming');
    });

    it('should filter sports by site city', () => {
      component.searchTerm = 'lyon';
      component.filterSports();

      expect(component.filteredSports.length).toBe(1);
      expect(component.filteredSports[0].name).toBe('Basketball');
    });

    it('should filter sports by site address', () => {
      component.searchTerm = 'court road';
      component.filterSports();

      expect(component.filteredSports.length).toBe(1);
      expect(component.filteredSports[0].name).toBe('Basketball');
    });

    it('should return empty array when no sports match the search term', () => {
      component.searchTerm = 'nonexistent';
      component.filterSports();

      expect(component.filteredSports.length).toBe(0);
    });

    it('should handle case where sport has no sites', () => {
      const sportWithoutSite = {
        sport_id: 4,
        name: 'Test Sport',
        description: 'Test description',
        site_id: null,
        sites: null
      };
      component.sportsWithSites = [sportWithoutSite];
      component.searchTerm = 'test';

      component.filterSports();

      expect(component.filteredSports.length).toBe(1);
      expect(component.filteredSports[0].name).toBe('Test Sport');
    });
  });

  describe('getSportIcon', () => {
    it('should return correct icon for known sports', () => {
      expect(component.getSportIcon('Swimming')).toBe('bi-water');
      expect(component.getSportIcon('Archery')).toBe('bi-bullseye');
      expect(component.getSportIcon('Basketball')).toBe('bi-basketball');
      expect(component.getSportIcon('Football')).toBe('bi-football');
      expect(component.getSportIcon('Tennis')).toBe('bi-circle-fill');
    });

    it('should return default icon for unknown sports', () => {
      expect(component.getSportIcon('Unknown Sport')).toBe('bi-trophy');
      expect(component.getSportIcon('')).toBe('bi-trophy');
      expect(component.getSportIcon('Cricket')).toBe('bi-trophy');
    });

    it('should be case sensitive for sport names', () => {
      expect(component.getSportIcon('swimming')).toBe('bi-trophy'); // lowercase should return default
      expect(component.getSportIcon('SWIMMING')).toBe('bi-trophy'); // uppercase should return default
      expect(component.getSportIcon('Swimming')).toBe('bi-water'); // exact case should work
    });
  });

  describe('component initialization', () => {
    it('should initialize with empty arrays and search term', () => {
      expect(component.sportsWithSites).toEqual([]);
      expect(component.filteredSports).toEqual([]);
      expect(component.searchTerm).toBe('');
    });
  });
});
