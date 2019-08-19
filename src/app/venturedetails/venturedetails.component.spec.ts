import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VenturedetailsComponent } from './venturedetails.component';

describe('VenturedetailsComponent', () => {
  let component: VenturedetailsComponent;
  let fixture: ComponentFixture<VenturedetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VenturedetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VenturedetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
