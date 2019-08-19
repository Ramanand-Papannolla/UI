import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PlotOwnerConnectComponent } from './plot-owner-connect.component';

describe('PlotOwnerConnectComponent', () => {
  let component: PlotOwnerConnectComponent;
  let fixture: ComponentFixture<PlotOwnerConnectComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PlotOwnerConnectComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PlotOwnerConnectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
