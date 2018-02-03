/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nestedclass;

/**
 *
 * @author Administrator
 */
public class NestedClass {

    public void messsageUpperClass() {
        System.out.println("Message from top class");
    }

    public static void main(String[] args) {
        NestedClass nc = new NestedClass();
        nc.messsageUpperClass();
        MyNestedClass nc1 = new MyNestedClass();
        MyNestedClass.MyNestedClass1 mnc = nc1.getInstance();
        mnc.messageFromNestedClass1();
        MyNestedClass.MyNestedClass1 mnc2 = nc1.new MyNestedClass1();
        mnc2.messageFromNestedClass1();
        
        MyNestedClass.MyStaticClass var = new MyNestedClass.MyStaticClass();
        var.MessageFromStaticClass();
    }
}

class MyNestedClass {

    public void messageFromNestedClass() {
        System.out.println("Message from MyNestedClass");
    }

    class MyNestedClass1 {

        public void messageFromNestedClass1() {
            System.out.println("From Nested class 1");
        }
    }

    public MyNestedClass1 getInstance() {
        return this.new MyNestedClass1();
    }
    
    public static class MyStaticClass{
    
        public void MessageFromStaticClass(){
            System.out.println("Message from static class");
        }
    }

}
