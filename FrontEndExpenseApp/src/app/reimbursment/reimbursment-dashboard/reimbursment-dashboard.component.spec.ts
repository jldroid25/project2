import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReimbursmentDashboardComponent } from './reimbursment-dashboard.component';

describe('ReimbursmentDashboardComponent', () => {
  let component: ReimbursmentDashboardComponent;
  let fixture: ComponentFixture<ReimbursmentDashboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReimbursmentDashboardComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ReimbursmentDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
