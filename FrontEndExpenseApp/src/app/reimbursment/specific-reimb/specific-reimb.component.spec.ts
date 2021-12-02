import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SpecificReimbComponent } from './specific-reimb.component';

describe('SpecificReimbComponent', () => {
  let component: SpecificReimbComponent;
  let fixture: ComponentFixture<SpecificReimbComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SpecificReimbComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SpecificReimbComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
