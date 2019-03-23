import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';


import { AppComponent } from './app.component';
import { StocksComponent } from './stocks.component';
import { MutualfundsComponent } from './mutualfunds/mutualfunds.component';

import { HighlightDirective } from './highlight.directive';
import { StockService } from './stock.service';

import { CapitalizePipePipe } from './capitalize-pipe.pipe';
import { EmployeenamePipe } from './employeename.pipe';
import { routing } from './app.routing';

import {NgbModule, NgbModalModule} from '@ng-bootstrap/ng-bootstrap';
import { DashboardComponent } from './dashboard/dashboard.component';

import { HttpModule } from '@angular/http';
import { StockFormComponent } from './stock-form/stock-form.component';




@NgModule({
  declarations: [
    AppComponent, StocksComponent, MutualfundsComponent,
    HighlightDirective, CapitalizePipePipe, EmployeenamePipe, DashboardComponent, StockFormComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    NgbModule.forRoot(),
    routing,
    HttpModule
  ],
  providers: [StockService],
  bootstrap: [AppComponent]
})
export class AppModule { }
