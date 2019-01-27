import {Component, Input, OnInit} from '@angular/core';
import {Card} from '../card';

@Component({
  selector: 'app-card-footer',
  templateUrl: './card-footer.component.html',
  styleUrls: ['./card-footer.component.css']
})
export class CardFooterComponent implements OnInit {

  @Input() card: Card;
  url: string = window.location.href;

  constructor() { }

  ngOnInit() {
  }

}
