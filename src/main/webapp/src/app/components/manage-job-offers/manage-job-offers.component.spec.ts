import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageJobOffersComponent } from './manage-job-offers.component';

describe('ManageJobOffersComponent', () => {
  let component: ManageJobOffersComponent;
  let fixture: ComponentFixture<ManageJobOffersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ManageJobOffersComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ManageJobOffersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
