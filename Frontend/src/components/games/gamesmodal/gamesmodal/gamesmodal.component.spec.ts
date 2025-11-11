import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

import { GamesmodalComponent } from './gamesmodal.component';

describe('GamesmodalComponent', () => {
  let component: GamesmodalComponent;
  let fixture: ComponentFixture<GamesmodalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GamesmodalComponent],
      providers: [
        { provide: MatDialogRef, useValue: { close: jasmine.createSpy('close') } },
        { provide: MAT_DIALOG_DATA, useValue: {} }
      ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GamesmodalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
