import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { AuthCredService } from 'src/app/user-credentials/auth-cred.service';

import { ReimbResolvedEmplComponent } from './reimb-resolved-empl.component';

describe('ReimbResolvedEmplComponent', () => {
  let component: ReimbResolvedEmplComponent;
  let fixture: ComponentFixture<ReimbResolvedEmplComponent>;
  const resolvedStatus: string = 'resolved';
  let authCredService: AuthCredService;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ReimbResolvedEmplComponent],
      imports:[HttpClientTestingModule,RouterTestingModule]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ReimbResolvedEmplComponent);
    component = fixture.componentInstance;
    authCredService = TestBed.inject(AuthCredService)
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should see only self and resolved status reimburse data', () => {
    expect(component.allReimbursements.length).toBeDefined();
    if (component.allReimbursements.length) {
      const userIdCount: number = new Set(component.allReimbursements.map(reimb => reimb.userId)).size;
      expect(userIdCount).toEqual(1);
      const statusCount: number = new Set(component.allReimbursements.map(reimb => reimb.reimbStatus)).size;
      expect(statusCount).toEqual(1);
      expect(component.allReimbursements[0].userId).toEqual(authCredService.retrieveUserId())
      expect(component.allReimbursements[0].reimbStatus).toEqual(resolvedStatus)
    }
  });
});
