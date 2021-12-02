import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReimbPendingEmplComponent } from './reimb-pending-empl.component';

describe('ReimbPendingEmplComponent', () => {
  let component: ReimbPendingEmplComponent;
  let fixture: ComponentFixture<ReimbPendingEmplComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReimbPendingEmplComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ReimbPendingEmplComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
