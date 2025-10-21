import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef, MatDialogModule } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';

@Component({
  standalone: true,
  selector: 'app-login-register-modal',
  imports: [
    CommonModule,
    MatDialogModule,
    MatButtonModule
  ],
  template: `
    <h2 mat-dialog-title>Authentication Required</h2>
    <mat-dialog-content>
      <p>Please log in or register to add this offer to your cart</p>
    </mat-dialog-content>
    <mat-dialog-actions align="end">
      <button mat-button (click)="goToRegister()">Register</button>
      <button mat-raised-button color="primary" (click)="goToLogin()">Log In</button>
    </mat-dialog-actions>
  `,
  styles: [`
    mat-dialog-content {
      padding: 20px 0;
      min-width: 300px;
    }
    
    mat-dialog-actions {
      margin-top: 10px;
    }
  `]
})
export class LoginRegisterModalComponent {
  constructor(
    public dialogRef: MatDialogRef<LoginRegisterModalComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private router: Router
  ) {}

  goToLogin(): void {
    this.dialogRef.close();
    // Store offer data for later use
    if (this.data?.offerData) {
      localStorage.setItem('pending_offer', JSON.stringify(this.data.offerData));
    }
    this.router.navigate(['/account'], { queryParams: { view: 'login' } });
  }

  goToRegister(): void {
    this.dialogRef.close();
    // Store offer data for later use
    if (this.data?.offerData) {
      localStorage.setItem('pending_offer', JSON.stringify(this.data.offerData));
    }
    this.router.navigate(['/account'], { queryParams: { view: 'register' } });
  }
}