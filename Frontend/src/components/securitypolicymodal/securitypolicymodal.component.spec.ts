import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

import { SecuritypolicymodalComponent } from './securitypolicymodal.component';

describe('SecuritypolicymodalComponent', () => {
  let component: SecuritypolicymodalComponent;
  let fixture: ComponentFixture<SecuritypolicymodalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SecuritypolicymodalComponent],
      providers: [
        { provide: MatDialogRef, useValue: { close: jasmine.createSpy('close') } },
        { provide: MAT_DIALOG_DATA, useValue: {} }
      ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SecuritypolicymodalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
