import {Component, OnInit, OnDestroy} from '@angular/core';
import {Card} from '../card';
import {ActivatedRoute} from '@angular/router';
import {DataService} from '../data/data.service';

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
            this.dataServiceSubscription = this.dataService.get(code).subscribe(response => {
                this.card = response;
                // this.card.cover = '';
                console.log(response);
            });
        });
    }

    ngOnDestroy() {
        this.routeSubscription.unsubscribe();
        this.dataServiceSubscription.unsubscribe()
    }

    hasParent() {
        return this.card.parentCode && this.card.parentCode.length === 4 && this.card.parentCode === "self";
    }

    hasNotParent() {
        return !this.hasParent();
    }

    hasNext() {
        return this.card.links && (this.card.links.length === 1 || this.card.links.length === 2) &&
            ((this.card.links[0].next && this.card.links[0].next.length > 0) || (this.card.links[1].next && this.card.links[1].next.length > 0));
    }

    hasPrev() {
        return this.card.links && (this.card.links.length === 1 || this.card.links.length === 2) &&
            ((this.card.links[0].prev && this.card.links[0].prev.length > 0) || (this.card.links[1].prev && this.card.links[1].prev.length > 0));
    }

    getNext() {
        return this.hasNext() ? this.card.links[0].hasOwnProperty("next") ? this.card.links[0].next: this.card.links[1].next : this.card.code;
    }

    getPrev() {
        return this.hasPrev() ? this.card.links[0].hasOwnProperty("prev") ? this.card.links[0].prev: this.card.links[1].prev : this.card.code;
    }

    getParent() {
        return this.hasParent() ? this.card.parentCode: this.card.code;
    }
}
