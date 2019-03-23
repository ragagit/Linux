import { TestBed, inject } from '@angular/core/testing';

import { Stockservice2Service } from './stockservice2.service';

describe('Stockservice2Service', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [Stockservice2Service]
    });
  });

  it('should be created', inject([Stockservice2Service], (service: Stockservice2Service) => {
    expect(service).toBeTruthy();
  }));
});
