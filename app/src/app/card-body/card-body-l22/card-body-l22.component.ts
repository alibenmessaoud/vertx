import {AfterContentInit, Component, Input, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {MatGridList} from '@angular/material';
import {MediaChange, ObservableMedia} from '@angular/flex-layout';
import {Commons} from '../../commons';
import {Card} from '../../card';
import * as marked from 'marked';

@Component({
  selector: 'app-card-body-l22',
  templateUrl: './card-body-l22.component.html',
  styleUrls: ['./card-body-l22.component.css']
})
export class CardBodyL22Component implements OnInit, OnDestroy, AfterContentInit {

  @Input() card: Card;
  @ViewChild('grid') grid: MatGridList;
  gridConfig;
  observableMediaSubscription;

  mdContent01;
  mdContent02;
  mdContent03;
  mdContent04;

  constructor(private observableMedia: ObservableMedia) {
  }

  ngAfterContentInit() {
    this.observableMediaSubscription = this.observableMedia.asObservable().subscribe(async (mediaChange: MediaChange) => {
      this.grid.cols = this.gridConfig.config[mediaChange.mqAlias];
    });
  }

  ngOnInit() {
    this.gridConfig = Commons.getGridConfig('22');
    this.mdContent01 = marked(this.card.mediaPlaceholder[0].content);
    this.mdContent02 = marked(this.card.mediaPlaceholder[1].content);
    this.mdContent03 = marked(this.card.mediaPlaceholder[2].content);
    this.mdContent04 = marked(this.card.mediaPlaceholder[3].content);
  }

  ngOnDestroy(): void {
    this.observableMediaSubscription.unsubscribe();
  }

}
