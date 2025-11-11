import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

import { ConditionsofusemodalComponent } from './conditionsofusemodal.component';

describe('ConditionsofusemodalComponent', () => {
  let component: ConditionsofusemodalComponent;
  let fixture: ComponentFixture<ConditionsofusemodalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ConditionsofusemodalComponent],
      providers: [
        { provide: MatDialogRef, useValue: { close: jasmine.createSpy('close') } },
        { provide: MAT_DIALOG_DATA, useValue: {} }
      ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ConditionsofusemodalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
