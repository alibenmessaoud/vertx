import {AfterViewInit, Component, ElementRef, ViewEncapsulation} from '@angular/core';
import {AuthService} from './auth.service';

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css'],
    encapsulation: ViewEncapsulation.None
})
export class AppComponent implements AfterViewInit {

    constructor(private elementRef: ElementRef, public auth: AuthService) {
        auth.handleAuthentication();
    }

    ngAfterViewInit() {
    }

}
