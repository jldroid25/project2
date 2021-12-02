import { TestBed } from '@angular/core/testing';

import { ReimbursService } from './reimburs.service';

describe('ReimbursService', () => {
  let service: ReimbursService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ReimbursService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
