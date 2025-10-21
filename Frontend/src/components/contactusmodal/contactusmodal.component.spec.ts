import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ContactusmodalComponent } from './contactusmodal.component';

describe('ContactusmodalComponent', () => {
  let component: ContactusmodalComponent;
  let fixture: ComponentFixture<ContactusmodalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ContactusmodalComponent]
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
