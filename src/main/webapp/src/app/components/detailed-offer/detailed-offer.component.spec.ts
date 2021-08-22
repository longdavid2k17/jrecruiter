import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailedOfferComponent } from './detailed-offer.component';

describe('DetailedOfferComponent', () => {
  let component: DetailedOfferComponent;
  let fixture: ComponentFixture<DetailedOfferComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DetailedOfferComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailedOfferComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
