import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CardBodyL21Component } from './card-body-l21.component';

describe('CardBodyL21Component', () => {
  let component: CardBodyL21Component;
  let fixture: ComponentFixture<CardBodyL21Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CardBodyL21Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CardBodyL21Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
