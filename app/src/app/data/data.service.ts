import {Injectable} from '@angular/core';
import {Card} from '../Card';
import {Observable, of} from 'rxjs';

@Injectable()
export class DataService {

    ELEMENT_DATA: Card[] = [
        {
            "code": "1000",
            "idParent": "self",
            "author": "Pascal Pezard",
            "description": "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWAAA",
            "image": "data:image/jpeg;base64,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",
            "date": "01/01/2019",
            "version": "1.0",
            "title": "Game plan",
            "subtitle": "David Sibbet",
            "template": "T11",
            "type": "agile",
            "mediaPlaceholder": "",
            "links": [
                {
                    "next": "1001"
                },
                {
                    "prev": "1003"
                }
            ],
            "tags": ["lol", "mdr"],
            "licence": "GPLv3"
        }

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

    get(code: string): Observable<Card> {
        const card = this.ELEMENT_DATA.find(card => {
            return card.code === code;
        });
        return of<Card>(card);
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
