import { Component, OnInit } from '@angular/core';
import { InfosService } from '../../services/infosservice.service';
import { SafeUrlPipe } from '../../safe-url.pipe';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-infos',
  templateUrl: './infos.component.html',
  styleUrls: ['./infos.component.css'],
  standalone: true,
  imports: [CommonModule,SafeUrlPipe, FormsModule]
})
export class InfosComponent implements OnInit {
  sportsWithSites: any[] = [];
  filteredSports: any[] = [];  
  searchTerm: string = ''; 

  constructor(private infosService: InfosService) {}

  /*ngOnInit() {
    this.infosService.getSportsWithSites().subscribe(({ sport, site }) => {
      // Map each sport to its site
      this.sportsWithSites = sport.map((s: { site_id: any; }) => ({
        ...s,
        site: site.find((si: { site_id: any; }) => si.site_id === s.site_id)
      }));
    });
  }
}*/
/*ngOnInit() {
    this.infosService.getSportsWithSites().subscribe({
      next: ({ sport, site }) => {
        // Map each sport to its corresponding site
        this.sportsWithSites = sport.map(s => ({
          ...s,
          site: site.find((si: { site_id: any; }) => si.site_id === s.site_id)
        }));
        console.log('Sports with sites:', this.sportsWithSites);
      },
      error: (err) => console.error('Error fetching data:', err)
    });
  }
}*/
ngOnInit() {
    // Get sports data - the backend already returns sports with sites
    this.infosService.getSports().subscribe({
      next: (data) => {
        this.sportsWithSites = data;
        this.filteredSports = [...data];
        console.log('Sports with sites:', this.sportsWithSites);
      },
      error: (err) => console.error('Error fetching data:', err)
    });
  }

filterSports(): void {
    const term = this.searchTerm.trim().toLowerCase();
    if (!term) {
      this.filteredSports = [...this.sportsWithSites] // this.sportsWithSites;
      return;
    }
    
    this.filteredSports = this.sportsWithSites.filter(sport =>
      sport.name.toLowerCase().includes(term) ||
      sport.description.toLowerCase().includes(term) ||
      (sport.sites?.name && sport.sites.name.toLowerCase().includes(term)) ||
      (sport.sites?.city && sport.sites.city.toLowerCase().includes(term)) ||
      (sport.sites?.address && sport.sites.address.toLowerCase().includes(term))
    );
  }

getSportIcon(sportName: string): string {
    const iconMap: {[key: string]: string} = {
      'Swimming': 'bi-water',
      'Archery': 'bi-bullseye',
      'Table-tennis': 'bi-circle',
      'Volleyball': 'bi-dribbble',
      'Handball': 'bi-hand',
      'Taekwondo': 'bi-person-standing',
      'Fencing': 'bi-slash-lg',
      'Boxing': 'bi-hand-fist',
      'Football': 'bi-football',
      'BMX freestyle': 'bi-bicycle',
      'Athletics': 'bi-stopwatch',
      'Basketball': 'bi-basketball',
      'Cycling': 'bi-bicycle',
      'Gymnastics': 'bi-person-arms-up',
      'Weightlifting': 'bi-bag-fill',
      'Wrestling': 'bi-people',
      'Judo': 'bi-person-bounding-box',
      'Tennis': 'bi-circle-fill',
      'Golf': 'bi-flag',
      'Rowing': 'bi-water'
    };

    return iconMap[sportName] || 'bi-trophy'; // Default icon if no match
  }
}