/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generics;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;

/**
 *
 * @author Administrator
 */
public class Generics {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SampleGeneric<String> sg = new SampleGeneric<String>("I am a string");
        sg.printMessage();
        SampleGeneric<Boolean> sg1 = new SampleGeneric<Boolean>(Boolean.TRUE);
        sg1.printMessage();
        SampleGeneric<Integer> sg2 = new SampleGeneric<Integer>(7);
        sg2.printMessage();
        
        MyGeneric<C> cc = new MyGeneric<C>(new C());
        cc.runTest();
        
    }
    
}

class MyGeneric< T extends MyMessage >{
    private T Obj;
    
    MyGeneric( T param){
        this.Obj = param;
    }
    
    public void runTest(){
        this.Obj.sendMessage();
    }
    
}
interface MyMessage{
    public void sendMessage();
}

class A implements MyMessage{
    public void sendMessage(){
        System.out.println("I am class A");
    }
}

class B extends A{
    public void sendMessage(){
       System.out.println("I am class B"); 
    }
}

class C extends A{
    public void sendMessage(){
        System.out.println("I am class C");
    }
}
class SampleGeneric<T>{
    
    private T Obj;
    
    SampleGeneric( T param){
        this.Obj = param;
    }
    
    public void printMessage(){
        System.out.println(Obj.getClass().getName());
    }
}