import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef, MatDialogModule } from '@angular/material/dialog';
import { CommonModule } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { DomSanitizer, SafeHtml } from '@angular/platform-browser';

@Component({
  selector: 'app-confidentialitypolicymodal',
  templateUrl: './confidentialitypolicymodal.component.html',
  styleUrls: ['./confidentialitypolicymodal.component.css'],
  standalone: true,
  imports: [CommonModule, MatDialogModule, HttpClientModule]
})
export class ConfidentialitypolicymodalComponent {
htmlContent: SafeHtml | null = null;

  constructor(
    public dialogRef: MatDialogRef<ConfidentialitypolicymodalComponent>,
    @Inject(MAT_DIALOG_DATA) public policy: any,
    private http: HttpClient,
    private sanitizer: DomSanitizer
  ) {
    // Load HTML content if a URL is provided
    if (this.policy?.url) {
      this.http.get(this.policy.url, { responseType: 'text' }).subscribe(html => {
        this.htmlContent = this.sanitizer.bypassSecurityTrustHtml(html);
      });
    }
  }

  closeModal(): void {
    this.dialogRef.close();
  }
}
