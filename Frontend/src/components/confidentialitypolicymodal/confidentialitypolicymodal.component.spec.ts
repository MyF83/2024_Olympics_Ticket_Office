import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfidentialitypolicymodalComponent } from './confidentialitypolicymodal.component';

describe('ConfidentialitypolicymodalComponent', () => {
  let component: ConfidentialitypolicymodalComponent;
  let fixture: ComponentFixture<ConfidentialitypolicymodalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ConfidentialitypolicymodalComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ConfidentialitypolicymodalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
