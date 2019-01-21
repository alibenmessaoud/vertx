import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CardThumbnail } from './card-thumbnail.component';

describe('CardThumbnailComponent', () => {
  let component: CardThumbnail;
  let fixture: ComponentFixture<CardThumbnail>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CardThumbnail ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CardThumbnail);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
