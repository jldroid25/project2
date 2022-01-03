import { TestBed } from '@angular/core/testing';

import { AuthEmployeeServiceService } from './auth-employee-service.service';

describe('AuthEmployeeServiceService', () => {
  let service: AuthEmployeeServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AuthEmployeeServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
