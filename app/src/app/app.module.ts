import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {MaterialModule} from './material.module';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {FlexLayoutModule} from '@angular/flex-layout';
import {WelcomeComponent} from './welcome/welcome.component';
import {FeedComponent} from './feed/feed.component';
import {AppRouters} from './app.routes';
import {DataService} from './data/data.service';
import {AuthService} from './auth.service';
import {PostDialogComponent} from './post-dialog/post-dialog.component';
import {FormsModule} from '@angular/forms';
import {CardThumbnailComponent} from './card-thumbnail/card-thumbnail.component';
import {TruncatePipe} from './truncate-pipe';
import {CardComponent} from './card/card.component';
import {QRCodeModule} from 'angularx-qrcode';
import {ReadMoreComponent} from './read-more.component';
import {CardHeaderComponent} from './card-header/card-header.component';

@NgModule({
    declarations: [
        AppComponent,
        WelcomeComponent,
        FeedComponent,
        PostDialogComponent,
        CardThumbnailComponent,
        TruncatePipe,
        CardComponent,
        ReadMoreComponent,
        CardHeaderComponent
    ],
    imports: [
        BrowserModule,
        BrowserAnimationsModule,
        MaterialModule,
        FlexLayoutModule,
        AppRouters,
        FormsModule,
        QRCodeModule
    ],
    providers: [
        DataService,
        AuthService
    ],
    bootstrap: [AppComponent],
    entryComponents: [
        PostDialogComponent
    ]
})
export class AppModule {
}
