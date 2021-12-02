import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReimbResolvedEmplComponent } from './reimb-resolved-empl.component';

describe('ReimbResolvedEmplComponent', () => {
  let component: ReimbResolvedEmplComponent;
  let fixture: ComponentFixture<ReimbResolvedEmplComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReimbResolvedEmplComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ReimbResolvedEmplComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
