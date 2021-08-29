import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddJobOfferComponent } from './add-job-offer.component';

describe('AddJobOfferComponent', () => {
  let component: AddJobOfferComponent;
  let fixture: ComponentFixture<AddJobOfferComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddJobOfferComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddJobOfferComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
