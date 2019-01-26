import {AfterContentInit, Component, Input, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {MatGridList} from '@angular/material';
import {MediaChange, ObservableMedia} from '@angular/flex-layout';
import {Commons} from '../../commons';
import {Card} from '../../card';
import * as marked from 'marked';

@Component({
  selector: 'app-card-body-l11',
  templateUrl: './card-body-l11.component.html',
  styleUrls: ['./card-body-l11.component.css']
})
export class CardBodyL11Component implements OnInit, OnDestroy, AfterContentInit {

  @Input() card: Card;
  @ViewChild('grid') grid: MatGridList;
  gridConfig;
  observableMediaSubscription;
  mdContent;

  constructor(private observableMedia: ObservableMedia) {
  }

  ngAfterContentInit() {
    this.observableMediaSubscription = this.observableMedia.asObservable().subscribe(async (mediaChange: MediaChange) => {
      this.grid.cols = this.gridConfig.config[mediaChange.mqAlias];
    });
  }

  ngOnInit() {
    this.gridConfig = Commons.getGridConfig('11');
    this.mdContent = marked(this.card.mediaPlaceholder[0].content);
  }

  ngOnDestroy(): void {
    this.observableMediaSubscription.unsubscribe();
  }

}
