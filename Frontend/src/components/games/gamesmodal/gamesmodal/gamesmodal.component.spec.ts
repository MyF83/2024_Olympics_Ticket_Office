import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GamesmodalComponent } from './gamesmodal.component';

describe('GamesmodalComponent', () => {
  let component: GamesmodalComponent;
  let fixture: ComponentFixture<GamesmodalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GamesmodalComponent]
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
