import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { AuthCredService } from 'src/app/user-credentials/auth-cred.service';

import { ReimbPendingEmplComponent } from './reimb-pending-empl.component';

describe('ReimbPendingEmplComponent', () => {
  let component: ReimbPendingEmplComponent;
  let fixture: ComponentFixture<ReimbPendingEmplComponent>;
  let authCredService: AuthCredService;
  const pendigStatus: string = 'pending';

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ReimbPendingEmplComponent],
      imports: [ReactiveFormsModule, HttpClientTestingModule, RouterTestingModule]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ReimbPendingEmplComponent);
    authCredService = TestBed.inject(AuthCredService)
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should see only self and pending status reimburse data', () => {
    expect(component.allReimbursements.length).toBeDefined();
    if (component.allReimbursements.length) {
      const userIdCount: number = new Set(component.allReimbursements.map(reimb => reimb.userId)).size;
      expect(userIdCount).toEqual(1);
      const statusCount: number = new Set(component.allReimbursements.map(reimb => reimb.reimbStatus)).size;
      expect(statusCount).toEqual(1);
      expect(component.allReimbursements[0].userId).toEqual(authCredService.retrieveUserId())
      expect(component.allReimbursements[0].reimbStatus).toEqual(pendigStatus)
    }
  });
});
