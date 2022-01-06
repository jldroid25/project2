import { HttpClientTestingModule } from '@angular/common/http/testing';
import { DebugElement } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { By } from '@angular/platform-browser';
import { RouterTestingModule } from '@angular/router/testing';

import { ReimbursmentDashboardComponent } from './reimbursment-dashboard.component';

describe('ReimbursmentDashboardComponent', () => {
  let component: ReimbursmentDashboardComponent;
  let fixture: ComponentFixture<ReimbursmentDashboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReimbursmentDashboardComponent ],
      imports:[HttpClientTestingModule,RouterTestingModule,ReactiveFormsModule]
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

  it('should render all reimb', () => {
    const reimbData=[
      {
        reimbId      : 11,
        reimbDate    : '',
        reimbReason  :'',
        reimbAmount  : 100,
        reimbStatus  : 'Pending',
        reimbRemoved : false,
        userId       :1
      }
    ]
    let tableRows:DebugElement[]=fixture.debugElement.queryAll(By.css('#table-body tr'));
    expect(tableRows.length).toEqual(component.allReimbursements.length);
    component.allReimbursements=reimbData;
    fixture.detectChanges();
     tableRows=fixture.debugElement.queryAll(By.css('#table-body tr'));
    expect(tableRows.length).toEqual(component.allReimbursements.length);
  });

});
