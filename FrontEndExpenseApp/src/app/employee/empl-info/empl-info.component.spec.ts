import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { By } from '@angular/platform-browser';

import { EmplInfoComponent } from './empl-info.component';

describe('EmplInfoComponent', () => {
  let component: EmplInfoComponent;
  let fixture: ComponentFixture<EmplInfoComponent>;
  const userData: any = {
    userId: 1, firstname: 'Vinay', lastname: 'Verma', username: '',
    password: '', email: 'vinay@test.com', accessLevel: 'employee'
  };

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [EmplInfoComponent],
      imports: [ReactiveFormsModule, HttpClientTestingModule]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EmplInfoComponent);
    component = fixture.componentInstance;
    sessionStorage.setItem('userData', JSON.stringify(userData));
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should retrieve userData', () => {
    const componentUserData: any = component.employeeModelObj;
    for (const key in componentUserData) {
      expect(componentUserData[key]).toEqual(userData[key]);
    }
  });

  it('should render emp info', () => {
    expect(component).toBeTruthy();
    const tableBody=fixture.debugElement.query(By.css('#emp-data tr'));
    if(tableBody){
      expect(tableBody.nativeElement.childNodes.length).toEqual(6);
      const colsToValidate:string[]=['userId','firstname','lastname','email','accessLevel'];
      colsToValidate.forEach((col,index)=>{
        expect(userData[col].toString()).toEqual(tableBody.nativeElement.childNodes[index].innerHTML);
      })
    }
  });

});
