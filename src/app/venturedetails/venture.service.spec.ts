import { TestBed } from '@angular/core/testing';

import { VentureService } from './service/ventureservice.service';

describe('VentureserviceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: VentureService = TestBed.get(VentureService);
    expect(service).toBeTruthy();
  });
});
