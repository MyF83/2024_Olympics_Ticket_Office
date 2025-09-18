import { Component } from '@angular/core';
import { CommonModule } from '@angular/common'; // Import CommonModule
import { OfferserviceService } from '../../services/offerservice.service';
import { Offersinterface } from '../../interfaces/offersinterface';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-offers',
  imports: [CommonModule, FormsModule], // Add CommonModule here
  standalone: true,
  templateUrl: './offers.component.html',
  styleUrls: ['./offers.component.css']
})
export class OffersComponent {


  offers: Offersinterface[] = [];

  constructor(
    private offerService: OfferserviceService) 
    { } // Inject the service

    ngOnInit() {
      this.offerService.getOffers().subscribe(data => {
        this.offers = data; // Assigning data after subscribing to the observable
      });
    }

    /*
    getImageForOffer(title: string): string {
      if (title.toLowerCase().includes('solo')) return 'png/Solo.png';
      if (title.toLowerCase().includes('duo')) return 'png/Duo.png';
      if (title.toLowerCase().includes('family')) return 'png/Family.png';
      return 'png/empty.png'; 
    }*/

    addToCart(offer: Offersinterface) {
      // TODO: Implement cart logic (e.g., call a CartService or emit an event)
      alert(`Offer '${offer.title}' added to cart!`);
    }

}
