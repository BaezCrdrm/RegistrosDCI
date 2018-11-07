import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistrosDCIComponent } from './registros-dci.component';

describe('RegistrosDCIComponent', () => {
  let component: RegistrosDCIComponent;
  let fixture: ComponentFixture<RegistrosDCIComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegistrosDCIComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegistrosDCIComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
