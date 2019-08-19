import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PlotOwnerListComponent } from './plot-owner-list.component';

describe('PlotOwnerListComponent', () => {
  let component: PlotOwnerListComponent;
  let fixture: ComponentFixture<PlotOwnerListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PlotOwnerListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PlotOwnerListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
