import {Component, ElementRef, HostListener, Input, OnChanges, ViewChild} from '@angular/core';

@Component({
    selector: 'app-card-header',
    templateUrl: './card-header.component.html',
    styleUrls: ['./card-header.component.css']
})

export class CardHeaderComponent {

    public isCollapsed = false;
    @Input() card;

    toggleView() {
        this.card.cover = ''
        this.isCollapsed = !this.isCollapsed;
    }

    hideView() {
        this.isCollapsed = false;
    }

    constructor(private eRef: ElementRef) {
    }

    // @HostListener('document:click', ['$event'])
    // clickOut(event) {
    //     if (!this.eRef.nativeElement.contains(event.target)) {
    //         this.isCollapsed = !this.isCollapsed;
    //     }
    // }
}
