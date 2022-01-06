import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';

import { EmplInfoComponent } from './empl-info.component';

describe('EmplInfoComponent', () => {
  let component: EmplInfoComponent;
  let fixture: ComponentFixture<EmplInfoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EmplInfoComponent ],
      imports:[ReactiveFormsModule,HttpClientTestingModule]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EmplInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
