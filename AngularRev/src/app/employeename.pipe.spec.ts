import { EmployeenamePipe } from './employeename.pipe';

describe('EmployeenamePipe', () => {
  it('create an instance', () => {
    const pipe = new EmployeenamePipe();
    expect(pipe).toBeTruthy();
  });
});
