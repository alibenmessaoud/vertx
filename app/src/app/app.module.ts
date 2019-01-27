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
import {CardBodyL11Component} from './card-body/card-body-l11/card-body-l11.component';
import {CardBodyL12Component} from './card-body/card-body-l12/card-body-l12.component';
import {CardBodyL21Component} from './card-body/card-body-l21/card-body-l21.component';
import {CardBodyL22Component} from './card-body/card-body-l22/card-body-l22.component';
import {MarkdownDirective} from './markdown.directive';
import {CardFooterComponent} from './card-footer/card-footer.component';
import {HttpClientModule} from '@angular/common/http';

@NgModule({
    declarations: [
        AppComponent,
        WelcomeComponent,
        FeedComponent,
        PostDialogComponent,
        CardThumbnailComponent,
        TruncatePipe,
        MarkdownDirective,
        CardComponent,
        ReadMoreComponent,
        CardHeaderComponent,
        CardBodyL11Component,
        CardBodyL12Component,
        CardBodyL21Component,
        CardBodyL22Component,
        CardFooterComponent
    ],
    imports: [
        BrowserModule,
        BrowserAnimationsModule,
        HttpClientModule,
        MaterialModule,
        FlexLayoutModule,
        AppRouters,
        FormsModule,
        QRCodeModule
    ],
    providers: [
        DataService,
        AuthService,
        HttpClientModule
    ],
    bootstrap: [AppComponent],
    entryComponents: [
        PostDialogComponent
    ]
})
export class AppModule {
}
