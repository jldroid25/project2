import { TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';

import { AdminCredGuard } from './admin-cred.guard';

describe('AdminCredGuard', () => {
  let guard: AdminCredGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({imports:[RouterTestingModule]});
    guard = TestBed.inject(AdminCredGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
