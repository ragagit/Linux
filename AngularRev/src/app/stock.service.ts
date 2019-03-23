import { Injectable } from '@angular/core';

import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';

import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';

@Injectable()
export class StockService {

    constructor(private http: Http) {

    }

    getStocksAPI(): Observable<any> {
        return this.http.get('http://localhost:3000/stocks')
            .map((res: Response) => res.json())
            .catch((error: any) => Observable.throw(error.jason || ' Server Error'));
    }
    createStock(newStockCode: string, newName: string): Observable<any> {
        return this.http.post('http://localhost:3000/stocks', { name: newName, stockCode: newStockCode });
    }
    getStocks(): string[] {
        return ['AAPL', 'IBM', 'GOOG', 'UBER', 'ABC'];
    }
    updateStock(stockId: string, newStockCode: string, newName: string): Observable<any> {
        return this.http.put('http://localhost:3000/stocks/' + stockId, { name: newName, stockCode: newStockCode });
    }

    deleteStock(stockId: string): Observable<any> {
        return this.http.delete('http://localhost:3000/stocks/' + stockId);
    }
















}



















