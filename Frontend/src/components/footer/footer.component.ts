import { Component } from '@angular/core';
import { SecuritypolicymodalComponent } from '../securitypolicymodal/securitypolicymodal.component';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { Securitypolicy } from '../../interfaces/securitypolicy';
import { SecuritypolicyService } from '../../services/securitypolicy.service';

@Component({
  selector: 'app-footer',
  imports: [MatDialogModule, SecuritypolicymodalComponent],
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

}
