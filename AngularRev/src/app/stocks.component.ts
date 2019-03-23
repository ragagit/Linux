import { Component } from '@angular/core';
import { StockService } from './stock.service';

@Component({
    selector : 'app-stocks',
    template : `<h1> Stocks </h1>
    {{ title }}
    <ul [ngStyle]="{'color': myColor, 'font-size': mySize}">
        <li *ngFor="let stock of stocks">
        {{ stock.name }}
        </li>
    </ul>

    <br>

    <ul *ngIf="stockMarkets.length" [ngClass]="{customClass:isColorBrown, centerClass:isCentered}">
        <li *ngFor="let stock of stocks">
            {{stock.stockCode}}
        </li>
    </ul>
    <div [ngSwitch]="market" >
        <div *ngSwitchCase="'NYSE'">New York Stock Exchang</div>
        <div *ngSwitchCase="'LSE'">London Stock Exchange</div>
       <div *ngSwitchDefault>Could not fin da match</div>
    </div>
    `, styles: [`

    .customClass{
        color: brown;
    }

    .centerClass{
        text-align: center;
    }

    `]
})

export class StocksComponent {

    market = 'LSE';
    myColor = 'blue';
    mySize = '100%';
    isCentered = false;
    isColorBrown = true;
    title = 'List of Stocks';
    stocks;

    stockMarkets = ['NYSE', 'NASDAQ', 'EURONEXT', 'HKSE', 'LSE'];
    showStockMarket = true;
    // constructor( stockService: StockService ) {
    //     // this.stocks = stockService.getStocks();
    // }
    constructor(private stockService: StockService) { this.getAllStocks(); }
    getAllStocks() {
        this.stockService.getStocksAPI()
        .subscribe(
          // data => console.log(JSON.stringify(data)),
          data => this.stocks = data,
          error => console.log('Server Error')
        );
      }
}


