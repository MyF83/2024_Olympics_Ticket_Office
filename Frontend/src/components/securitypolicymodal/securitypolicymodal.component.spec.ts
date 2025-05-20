import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SecuritypolicymodalComponent } from './securitypolicymodal.component';

describe('SecuritypolicymodalComponent', () => {
  let component: SecuritypolicymodalComponent;
  let fixture: ComponentFixture<SecuritypolicymodalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SecuritypolicymodalComponent]
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
