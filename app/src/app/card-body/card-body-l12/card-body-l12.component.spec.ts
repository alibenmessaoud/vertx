import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CardBodyL12Component } from './card-body-l12.component';

describe('CardBodyL12Component', () => {
  let component: CardBodyL12Component;
  let fixture: ComponentFixture<CardBodyL12Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CardBodyL12Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CardBodyL12Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
