import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResolvedReimbComponent } from './resolved-reimb.component';

describe('ResolvedReimbComponent', () => {
  let component: ResolvedReimbComponent;
  let fixture: ComponentFixture<ResolvedReimbComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ResolvedReimbComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ResolvedReimbComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
