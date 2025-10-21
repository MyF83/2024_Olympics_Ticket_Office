import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConditionsofusemodalComponent } from './conditionsofusemodal.component';

describe('ConditionsofusemodalComponent', () => {
  let component: ConditionsofusemodalComponent;
  let fixture: ComponentFixture<ConditionsofusemodalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ConditionsofusemodalComponent]
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
