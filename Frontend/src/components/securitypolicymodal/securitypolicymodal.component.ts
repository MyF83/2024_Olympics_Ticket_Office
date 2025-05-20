import { Component, Inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { Securitypolicy } from '../../interfaces/securitypolicy';
import { CommonModule } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http'; // <-- Add this
import { DomSanitizer, SafeHtml } from '@angular/platform-browser'; // <-- Add this


@Component({
  selector: 'app-securitypolicymodal',
  standalone : true,
  imports: [FormsModule, CommonModule, MatDialogModule,HttpClientModule ],
  templateUrl: './securitypolicymodal.component.html',
  styleUrls: ['./securitypolicymodal.component.css']
})
export class SecuritypolicymodalComponent {
 htmlContent: SafeHtml | null = null;

  constructor(
    public dialogRef: MatDialogRef<SecuritypolicymodalComponent>,
    @Inject(MAT_DIALOG_DATA) public policy: Securitypolicy,
    private http: HttpClient, // <-- Inject HttpClient
    private sanitizer: DomSanitizer // <-- Inject DomSanitizer
  ) {
      // Provide default values if policy is null
      this.policy = this.policy || {
        title: 'Default Title',
        description: 'Default Description',
        url: 'https://example.com',
        creationDate: 'N/A',
        version: 'N/A'
      };
       if (this.policy.url) {
      this.http.get(this.policy.url, { responseType: 'text' }).subscribe(html => {
        this.htmlContent = this.sanitizer.bypassSecurityTrustHtml(html);
      });
    }
    console.log('Modal received policy: ', policy);
    console.log('Title: ', policy.title, typeof policy.title);
    console.log('Description: ', policy.description, typeof policy.description);
    console.log('URL: ', policy.url, typeof policy.url);
    console.log('Date of creation: ', policy.creationDate, typeof policy.creationDate);
    console.log('Version number: ', policy.version, typeof policy.version);}

  closeSecuritypolicymodal(): void {
    this.dialogRef.close();
  }
  

}
