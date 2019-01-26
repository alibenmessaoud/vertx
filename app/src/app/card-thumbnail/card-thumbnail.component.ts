import {Component, OnInit, Input} from '@angular/core';
import {Card} from '../card';
import {Commons} from '../commons';

@Component({
    selector: 'app-card-thumbnail',
    templateUrl: './card-thumbnail.component.html',
    styleUrls: ['./card-thumbnail.component.css']
})
export class CardThumbnailComponent implements OnInit {

    @Input() card: Card;
    backgroundStyle: any;

    constructor() {
    }

    ngOnInit() {
        this.backgroundStyle = this.card.cover ? {background: `url(${this.card.cover})`} : {background: Commons.getRandomColor()};
    }

}
