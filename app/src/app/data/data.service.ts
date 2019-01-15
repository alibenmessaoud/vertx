import {Injectable} from '@angular/core';
import {Post} from '../Post';
import {Observable, of} from 'rxjs';

@Injectable()
export class DataService {

  ELEMENT_DATA: Post[] = [
    {position: 0, title: 'Post One', category: 'Game 1', date_posted: new Date(), body: 'Body 1'},
    {position: 1, title: 'Post Two', category: 'Game 3', date_posted: new Date(), body: 'Body 2'},
    {position: 2, title: 'Post Three', category: 'Game 2', date_posted: new Date(), body: 'Body 3'},
    {position: 3, title: 'Post Four', category: 'Game 2', date_posted: new Date(), body: 'Body 4'},
    {position: 4, title: 'Post Five', category: 'Game 4', date_posted: new Date(), body: 'Body 5'},
    {position: 5, title: 'Post Six', category: 'Game 2', date_posted: new Date(), body: 'Body 6'},
  ];
  categories = [
    {value: 'Game 1', viewValue: 'Game 1'},
    {value: 'Game 2', viewValue: 'Game 2'},
    {value: 'Game 3', viewValue: 'Game 3'}
  ];

  constructor() {
  }

  getData(): Observable<Post[]> {
    return of<Post[]>(this.ELEMENT_DATA);
  }

  getCategories() {
    return this.categories;
  }

  addPost(data) {
    this.ELEMENT_DATA.push(data);
  }

  deletePost(index) {
    this.ELEMENT_DATA = [...this.ELEMENT_DATA.slice(0, index), ...this.ELEMENT_DATA.slice(index + 1)];
  }

  dataLength() {
    return this.ELEMENT_DATA.length;
  }
}
