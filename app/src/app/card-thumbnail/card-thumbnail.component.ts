import { Component, OnInit, Input } from '@angular/core';
import { Card } from '../Card';

@Component({
  selector: 'app-card-thumbnail',
  templateUrl: './card-thumbnail.component.html',
  styleUrls: ['./card-thumbnail.component.css']
})
export class CardThumbnailComponent implements OnInit {

  @Input() card: Card;
  backgroundStyle: any;

  constructor() { }

  ngOnInit() {
    this.backgroundStyle = { 'background': `url(${this.card.cover})` };
  }

}
