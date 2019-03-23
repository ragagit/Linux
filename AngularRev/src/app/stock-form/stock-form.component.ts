import { Component, OnInit } from '@angular/core';
import {Stock} from '../stock';

@Component({
  selector: 'app-stock-form',
  templateUrl: './stock-form.component.html',
  styleUrls: ['./stock-form.component.css']
})
export class StockFormComponent implements OnInit {

  constructor() { }

  submitted = false;
  newStock = new Stock(0, ' ', ' ');

  ngOnInit() {
  }

  onSubmit() {
    this.submitted = true;
  }

  cancel() {
    this.submitted = false;
    this.newStock = new Stock(0, ' ', ' ');
  }
}
