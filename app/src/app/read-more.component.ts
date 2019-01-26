import {Component, ElementRef, HostListener, Input, OnChanges, ViewChild} from '@angular/core';

@Component({
    selector: 'app-read-more',
    template: `
        <div [innerHTML]="currentText" [class.hidden]="hideToggle" (click)="toggleView()" class="broken-words read-more"></div>`
})

export class ReadMoreComponent implements OnChanges {
    @Input() text: string;
    @Input() maxLength = 200;
    currentText: string;
    hideToggle = true;
    public isCollapsed = true;

    constructor(private eRef: ElementRef) {
    }

    toggleView() {
        this.isCollapsed = !this.isCollapsed;
        this.determineView();
    }

    determineView() {
        if (!this.text || this.text.length <= this.maxLength) {
            this.currentText = this.text;
            this.isCollapsed = false;
            this.hideToggle = true;
            return;
        }
        this.hideToggle = false;
        if (this.isCollapsed === true) {
            this.currentText = this.text.substring(0, this.maxLength) + '...';
        } else if (this.isCollapsed === false) {
            this.currentText = this.text;
        }
    }

    ngOnChanges() {
        this.determineView();
    }

    @HostListener('document:click', ['$event'])
    clickOut(event) {
        if (!this.eRef.nativeElement.contains(event.target)) {
            this.isCollapsed = !this.isCollapsed;
            this.determineView();
        }
    }
}
