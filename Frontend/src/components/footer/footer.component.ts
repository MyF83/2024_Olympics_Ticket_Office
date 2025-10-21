import { Component } from '@angular/core';
import { SecuritypolicymodalComponent } from '../securitypolicymodal/securitypolicymodal.component';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { Securitypolicy } from '../../interfaces/securitypolicy';
import { SecuritypolicyService } from '../../services/securitypolicy.service';
import { ConfidentialitypolicymodalComponent } from '../confidentialitypolicymodal/confidentialitypolicymodal.component';
import { ConditionsofusemodalComponent } from '../conditionsofusemodal/conditionsofusemodal.component';
import { ContactusmodalComponent } from '../contactusmodal/contactusmodal.component';

@Component({
  selector: 'app-footer',
  imports: [MatDialogModule],
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent {


policies: Securitypolicy[] = []; //

  constructor(
    private securitypolicyService: SecuritypolicyService, // Inject the service
    private dialog: MatDialog) 
  { } // Inject the MatDialog service

  /*
  openSecuritypolicymodal(policy: Securitypolicy | null): void {
    this.dialog.open(SecuritypolicymodalComponent, {
      width: '1100px',
      panelClass: 'custom-modal',
      data: policy
    });
    console.log('Security Policy modal: ', policy);
  }*/
    openSecuritypolicymodal(_: Securitypolicy | null = null): void {
      this.securitypolicyService.getPolicies().subscribe(policies => {
        const activePolicy = policies.find(p => p.isActive === true);
        if (activePolicy) {
          console.log('Opening Security Policy modal with active policy:', activePolicy);
          this.dialog.open(SecuritypolicymodalComponent, {
            width: '1100px',
            panelClass: 'custom-modal',
            data: activePolicy
          });
        } else {
          console.warn('No active security policy found.');
        }
      });
    }

opencloseSecuritypolicymodal(policy: Securitypolicy): void {
    const dialogRef = this.dialog.open(SecuritypolicymodalComponent, {
      width: '1100px',
      panelClass: 'custom-modal',
      data: policy // Pass the game data to the modal
    });
    dialogRef.close(); // Close the dialog
    console.log('Security Policy modal closed'); // Log the closure of the modal
  }

  logMessage(message: string): void {
    console.log(message);
  }

openConfidentialityModal(): void {
      this.dialog.open(ConfidentialitypolicymodalComponent, {
      width: '800px',
      data: {
        title: 'Confidentiality Policy',
        description: 'Our confidentiality policy...',
        url: './assets/policies/confidentiality.html', // If loading external HTML
        creationDate: '2023-10-01',
        version: '1.0'
      }
    });
  }



  openConditionsModal(): void {
    this.dialog.open(ConditionsofusemodalComponent, {
      width: '800px',
      data: {
        title: 'Conditions of Use',
        description: 'Our terms and conditions...',
        url: './assets/policies/conditions.html',
        creationDate: '2023-10-01',
        version: '1.0'
      }
    });
  }

openContactModal(): void {
    this.dialog.open(ContactusmodalComponent, {
      width: '800px',
      data: {
        title: 'Contact Us',
        description: 'Get in touch with our support team...',
        url: './assets/policies/contact.html',
        creationDate: '2023-10-01',
        version: '1.0'
      }
    });
  }

}
