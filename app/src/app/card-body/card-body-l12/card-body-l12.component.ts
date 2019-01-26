import {AfterContentInit, Component, Input, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {MatGridList} from '@angular/material';
import {MediaChange, ObservableMedia} from '@angular/flex-layout';
import {Commons} from '../../commons';
import {Card} from '../../card';
import * as marked from 'marked';

@Component({
    selector: 'app-card-body-l12',
    templateUrl: './card-body-l12.component.html',
    styleUrls: ['./card-body-l12.component.css']
})
export class CardBodyL12Component implements OnInit, OnDestroy, AfterContentInit {

    @Input() card: Card;
    @ViewChild('grid') grid: MatGridList;
    gridConfig;
    observableMediaSubscription;

    mdContent01;
    mdContent02;

    constructor(private observableMedia: ObservableMedia) {
    }

    ngAfterContentInit() {
        this.observableMediaSubscription = this.observableMedia.asObservable().subscribe(async (mediaChange: MediaChange) => {
            this.grid.cols = this.gridConfig.config[mediaChange.mqAlias];
        });
    }

    ngOnInit() {
        this.gridConfig = Commons.getGridConfig('12');
        this.mdContent01 = marked(this.card.mediaPlaceholder[0].content);
        this.mdContent02 = marked(this.card.mediaPlaceholder[1].content);
    }

    ngOnDestroy(): void {
        this.observableMediaSubscription.unsubscribe();
    }

}
