import { TestBed } from '@angular/core/testing';

import { AuthCredService } from './auth-cred.service';

describe('AuthCredService', () => {
  let service: AuthCredService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AuthCredService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
