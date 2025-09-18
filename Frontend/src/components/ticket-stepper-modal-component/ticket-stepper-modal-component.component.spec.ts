import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TicketStepperModalComponentComponent } from './ticket-stepper-modal-component.component';

describe('TicketStepperModalComponentComponent', () => {
  let component: TicketStepperModalComponentComponent;
  let fixture: ComponentFixture<TicketStepperModalComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TicketStepperModalComponentComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TicketStepperModalComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
