import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-card-thumbnail',
  templateUrl: './card-thumbnail.component.html',
  styleUrls: ['./card-thumbnail.component.css']
})
export class CardThumbnailComponent implements OnInit {

  card = {
    text: "The Shiba Inu is the smallest of the six original and distinct spitz breeds of dog from Japan. A small, agile dog that copes very well with mountainous terrain, the Shiba Inu was originally bred for hunting."
  };

  constructor() { }

  ngOnInit() {
  }

}
