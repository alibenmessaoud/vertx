import { Component, OnInit, OnDestroy } from '@angular/core';
import { Card } from '../Card';
import { ActivatedRoute } from '@angular/router';
import { DataService } from '../data/data.service';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit, OnDestroy {

  card: Card;
  url: string;

  routeSubscription: any;
  dataServiceSubscription: any;

  constructor(private route: ActivatedRoute, private dataService: DataService) {
    this.url = window.location.href
  }

  ngOnInit() {
    this.routeSubscription = this.route.params.subscribe(params => {
      const code = params['code']; // (+) converts string 'id' to a number
      this.dataServiceSubscription = this.dataService.get(code).subscribe(response => {this.card = response; console.log(response)});
    });
  }

  ngOnDestroy() {
    this.routeSubscription.unsubscribe();
    this.dataServiceSubscription.unsubscribe()
  }
}
