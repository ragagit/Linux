/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyWildcardEx;

import java.util.HashMap;

public class Generics2 {
 
    public static void main(String a[]){
       /*  
        MyEmployeeUtil<CompAEmp> empA
                    = new MyEmployeeUtil<CompAEmp>(new CompAEmp("Ram", 20000));
        MyEmployeeUtil<CompBEmp> empB
                    = new MyEmployeeUtil<CompBEmp>(new CompBEmp("Krish", 30000));
        MyEmployeeUtil<CompAEmp> empC
                    = new MyEmployeeUtil<CompAEmp>(new CompAEmp("Nagesh", 20000));
        System.out.println("Is salary same? "+empA.isSalaryEqual(empB));
        System.out.println("Is salary same? "+empA.isSalaryEqual(empC));*/
        HashMap<String, String> hm = new HashMap<String, String>();
        HashMap<String, String> myHash = new HashMap<String, String>();

        myHash.put("First", "First Value");
        myHash.put("Second", "Second Value");
        
        if( myHash.containsKey("First") ){
                System.out.println("It has it.");
        }
        System.out.println(myHash.get("First"));
        
    }
}
 
class MyEmployeeUtil<T extends Emp>{
     
    private T emp;
     
    public MyEmployeeUtil(T obj){
        emp = obj;
    }
     
    public int getSalary(){
        return emp.getSalary();
    }
     
    public boolean isSalaryEqual(MyEmployeeUtil<?> otherEmp){
         
        if(emp.getSalary() == otherEmp.getSalary()){
            return true;
        }
        return false;
    }
     
    //// create some utility methods to do employee functionalities
    //
    //
    //
}
 
class Emp{
     
    private String name;
    private int salary;
     
    public Emp(String name, int sal){
        this.name = name;
        this.salary = sal;
    }
     
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getSalary() {
        return salary;
    }
    public void setSalary(int salary) {
        this.salary = salary;
    }
}
 
class CompAEmp extends Emp{
     
    public CompAEmp(String nm, int sal){
        super(nm, sal);
    }
}
 
class CompBEmp extends Emp{
     
    public CompBEmp(String nm, int sal){
        super(nm, sal);
    }
}
