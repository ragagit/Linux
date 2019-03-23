import { Component } from '@angular/core';
import { Employee } from './employee';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';
  firstName = 'raga';
  today = new Date();
  employee: Employee = new Employee('ra', 'ga', 100000 );
  profile1 = { id: 1007, name: 'James Bond', role: 'Admin'};
}
