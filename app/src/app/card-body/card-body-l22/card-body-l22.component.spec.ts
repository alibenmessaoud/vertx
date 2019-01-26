import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CardBodyL22Component } from './card-body-l22.component';

describe('CardBodyL22Component', () => {
  let component: CardBodyL22Component;
  let fixture: ComponentFixture<CardBodyL22Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CardBodyL22Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CardBodyL22Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
