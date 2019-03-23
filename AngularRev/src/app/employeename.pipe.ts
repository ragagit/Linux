import { Pipe, PipeTransform } from '@angular/core';
import { Employee } from './employee';

@Pipe({
  name: 'employeename'
})
export class EmployeenamePipe implements PipeTransform {

  transform(value: Employee, args?: any): any {
    return value.firstName + ' ' + value.lastName + ' ' + value.salary;
  }

}
