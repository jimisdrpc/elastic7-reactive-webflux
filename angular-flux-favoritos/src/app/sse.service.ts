import { Injectable, NgZone } from '@angular/core';
import { Observable } from 'rxjs';
import { Sugestao } from './sugestao';


@Injectable({
  providedIn: "root"
})
export class SseService {
  sugestoes: Sugestao[] = [];
  constructor(private _zone: NgZone) { }

  getServerSentEvent(url: string): Observable<any> {
    this.sugestoes = [];
    return Observable.create(observer => {
      const eventSource = this.getEventSource(url);
      eventSource.onmessage = event => {
        this._zone.run(() => {
          let json = JSON.parse(event.data);
          this.sugestoes.push(new Sugestao(json['id'], json['name'], json['phone'], json['account']));
          observer.next(this.sugestoes);
        });
      };
      eventSource.onerror = (error) => {
        if (eventSource.readyState === 0) {
          console.log('The stream has been closed by the server.');
          eventSource.close();
          observer.complete();
        } else {
          observer.error('EventSource error: ' + error);
        }
      }

    });
  }
  private getEventSource(url: string): EventSource {
    return new EventSource(url);
  }

  
}

