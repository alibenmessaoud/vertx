import {Component} from '@angular/core';
import {DataService} from '../data/data.service';
import {Card} from '../Card';
import {DataSource} from '@angular/cdk/table';
import {Observable} from 'rxjs/Observable';
import {AuthService} from '../auth.service';
import {PostDialogComponent} from '../post-dialog/post-dialog.component';
import {MatDialog} from '@angular/material';

@Component({
  selector: 'app-feed',
  templateUrl: './feed.component.html',
  styleUrls: ['./feed.component.css']
})
export class FeedComponent {
  constructor(public auth: AuthService, public dialog: MatDialog, private dataService: DataService) {
  }

  displayedColumns = ['date_posted', 'title', 'category', 'delete'];
  dataSource = new CardDataSource(this.dataService);

  delete(id) {
    if (this.auth.isAuthenticated()) {
      this.dataService.delete(id);
      this.dataSource = new CardDataSource(this.dataService);
    } else {
      alert('Login in Before');
    }
  }

  openDialog(): void {
    let dialogRef = this.dialog.open(PostDialogComponent, {
      width: '600px',
      data: 'Add Card'
    });
    dialogRef.componentInstance.event.subscribe((result) => {
      this.dataService.add(result.data);
      this.dataSource = new CardDataSource(this.dataService);
    });
  }
}

export class CardDataSource extends DataSource<any> {
  constructor(private dataService: DataService) {
    super();
  }

  connect(): Observable<Card[]> {
    return this.dataService.getData();
  }

  disconnect() {
  }
}
