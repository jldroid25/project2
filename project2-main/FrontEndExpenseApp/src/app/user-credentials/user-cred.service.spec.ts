import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';

import { UserCredService } from './user-cred.service';

describe('UserCredService', () => {
  let service: UserCredService;

  beforeEach(() => {
    TestBed.configureTestingModule({imports:[HttpClientTestingModule]});
    service = TestBed.inject(UserCredService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
