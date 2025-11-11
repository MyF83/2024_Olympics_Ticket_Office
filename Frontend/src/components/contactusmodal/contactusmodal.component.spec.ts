import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

import { ContactusmodalComponent } from './contactusmodal.component';

describe('ContactusmodalComponent', () => {
  let component: ContactusmodalComponent;
  let fixture: ComponentFixture<ContactusmodalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ContactusmodalComponent],
      providers: [
        { provide: MatDialogRef, useValue: { close: jasmine.createSpy('close') } },
        { provide: MAT_DIALOG_DATA, useValue: {} }
      ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ContactusmodalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
