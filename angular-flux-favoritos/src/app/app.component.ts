import { Component, OnInit } from '@angular/core';

//SERVICES
import { SseService } from './sse.service';
import { NosseService } from './nosse.service';

//MODELS
import { Sugestao } from './sugestao';

//RXJS
import { Observable } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  sugestoes$: Observable<any>;
  restItems: any;

  //### SSE
  // https://developer.mozilla.org/en-US/docs/Web/API/EventSource
  searchSseWithDelay(searchValue: string): void {
    this.sugestoes$ = this.sseService
      .getServerSentEvent("http://localhost:8080/sugestao/search-with-delay/" + searchValue);
  }

  searchSseWithoutDelay(searchValue: string): void {
    this.sugestoes$ = this.sseService
      .getServerSentEvent("http://localhost:8080/sugestao/search/" + searchValue);
  }

  //### Without SSE
  searchWithDelay(searchValue: string): void {
    this.sugestoes$ = this.nosseService.getWithoutServerSentEvent("http://localhost:8080/sugestao/search-with-delay/" + searchValue);
  }

  searchWthoutDelay(searchValue: string): void {
    this.sugestoes$ = this.nosseService.getWithoutServerSentEvent("http://localhost:8080/sugestao/search/" + searchValue);
  }

  constructor(
    private sseService: SseService, private nosseService: NosseService) { }

  ngOnInit() {
  }


}

