import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { OfferStepperModalComponent } from './offer-stepper-modal.component';

describe('OfferStepperModalComponent', () => {
  let component: OfferStepperModalComponent;
  let fixture: ComponentFixture<OfferStepperModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OfferStepperModalComponent, HttpClientTestingModule],
      providers: [
        { provide: MatDialogRef, useValue: {} },
        { provide: MAT_DIALOG_DATA, useValue: { cartId: 1, existingOffer: {} } }
      ]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(OfferStepperModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});