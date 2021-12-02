import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PendingReimbComponent } from './pending-reimb.component';

describe('PendingReimbComponent', () => {
  let component: PendingReimbComponent;
  let fixture: ComponentFixture<PendingReimbComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PendingReimbComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PendingReimbComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
