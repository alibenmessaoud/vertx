import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CardBodyL11Component } from './card-body-l11.component';

describe('CardBodyL11Component', () => {
  let component: CardBodyL11Component;
  let fixture: ComponentFixture<CardBodyL11Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CardBodyL11Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CardBodyL11Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
