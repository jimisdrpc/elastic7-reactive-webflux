import { Injectable, NgZone } from '@angular/core';
import { Observable } from 'rxjs';
import { Sugestao } from './sugestao';
import { HttpClient } from '@angular/common/http';
import { map, tap } from 'rxjs/operators';

@Injectable({
    providedIn: "root"
})
export class NosseService {

    constructor(private http: HttpClient) { }

    getWithoutServerSentEvent(url: string): Observable<any> {

        return this.http
            .get<Sugestao[]>(url)
            .pipe()
            ;
    }
}

