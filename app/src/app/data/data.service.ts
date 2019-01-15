import {Injectable} from '@angular/core';
import {Card} from '../Card';
import {Observable, of} from 'rxjs';

@Injectable()
export class DataService {

  ELEMENT_DATA: Card[] = [
    {position: 0, title: 'Card One', category: 'Game 1', date_posted: new Date(), body: 'Body 1'},
    {position: 1, title: 'Card Two', category: 'Game 3', date_posted: new Date(), body: 'Body 2'},
    {position: 2, title: 'Card Three', category: 'Game 2', date_posted: new Date(), body: 'Body 3'},
    {position: 3, title: 'Card Four', category: 'Game 2', date_posted: new Date(), body: 'Body 4'},
    {position: 4, title: 'Card Five', category: 'Game 4', date_posted: new Date(), body: 'Body 5'},
    {position: 5, title: 'Card Six', category: 'Game 2', date_posted: new Date(), body: 'Body 6'},
  ];
  categories = [
    {value: 'Game 1', viewValue: 'Game 1'},
    {value: 'Game 2', viewValue: 'Game 2'},
    {value: 'Game 3', viewValue: 'Game 3'}
  ];

  constructor() {
  }

  getData(): Observable<Card[]> {
    return of<Card[]>(this.ELEMENT_DATA);
  }

  getCategories() {
    return this.categories;
  }

  add(data) {
    this.ELEMENT_DATA.push(data);
  }

  delete(index) {
    this.ELEMENT_DATA = [...this.ELEMENT_DATA.slice(0, index), ...this.ELEMENT_DATA.slice(index + 1)];
  }

  dataLength() {
    return this.ELEMENT_DATA.length;
  }
}
