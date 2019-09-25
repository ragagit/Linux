/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javareview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

/**
 *
 * @author raga
 */

////// -- Data types --- /////
/*
byte	8 bits	byte x,y,z;
short	2 bytes	short x;
int	4 bytes	int num;
long	8 bytes
float	4 bytes	
double	8 bytes

*/

/////// --- Arrays --- //////
/*
type variable-name[] = new type[size of the array];
int account_numbers[] = new int[10];

int account_numbers[] = {12,34,56,12,78,98,34};
int sample[][] = new int[3][];

**** Arrays to list ****


import java.util.Arrays;
import java.util.List;
 
public class ArraysToList {
 
    public static void main(String a[]){
        String[] strArr = {"JAVA", "C++", "PERL", "STRUTS", "PLAY"};
        List<String> strList = Arrays.asList(strArr);
        System.out.println("Created List Size: "+strList.size());
        System.out.println(strList);
    }
}

**** Binary Search ****

import java.util.Arrays;
 
public class BinarySearchOnCharArray {
 
    public static void main(String a[]){
        char[] chrArr = {'a','c','d','y','e','q','b'};
        int index = Arrays.binarySearch(chrArr, 0, chrArr.length-1, 'q');
        System.out.println("Char 'q' index is: "+index);
    }
}

**** Copy and Increase size ****

import java.util.Arrays;
 
public class MyArrayCopy {
     
    public static void main(String a[]){
         
        int[] myArr = {2,4,2,4,5,6,3};
        System.out.println("Array size before copy: "+myArr.length);
        int[] newArr = Arrays.copyOf(myArr, 10);
        System.out.println("New array size after copying: "+newArr.length);
    }
}

**** Copy Range ****

import java.util.Arrays;
 
public class MyArrayRangeCopy {
     
    public static void main(String a[]){
         
        int[] myArr = {2,4,2,4,5,6,3};
        System.out.println("My array elements:\n");
        for(int num:myArr){
            System.out.print(num+"  ");
        }
        int[] newArr = Arrays.copyOfRange(myArr, 1, 4);
        System.out.println("\nMy new array elements:\n");
        for(int num:newArr){
            System.out.print(num+"  ");
        }
    }
}

**** Compare two arrays
import java.util.Arrays;
 
public class MyArrayDeepEquals {
 
    public static void main(String a[]){
        String[] strArr = {"JAVA", "C++", "PERL", "STRUTS", "PLAY"};
        String[] strArrCopy = {"JAVA", "C++", "PERL", "STRUTS", "PLAY"};
        System.out.println("Are strArr and strArrCopy same? "
                    +Arrays.deepEquals(strArr, strArrCopy));
        String[] strArrMod = {"JAVA", "C++", "PERL", "STRUTS"};
        System.out.println("Are strArr and strArrMod same? "
                    +Arrays.deepEquals(strArr, strArrMod));
    }
}

**** Filling with default values ****

import java.util.Arrays;
 
public class MyArrayFill {
     
    public static void main(String a[]){
         
        String[] myArr = new String[10];
        Arrays.fill(myArr, "Assigned");
        for(String str:myArr){
            System.out.println(str);
        }
    }
}

**** Sorting Array ****

import java.util.Arrays;
 
public class MyArraySort {
 
    public static void main(String a[]){
         
        int[] myArr = {3,2,56,12,98,23,56};
        Arrays.sort(myArr);
        for(int str:myArr){
            System.out.println(str);
        }
    }
}


**** Using comparator ****

import java.util.Arrays;
import java.util.Comparator;
 
public class MyStringArraySorting {
 
    public static void main(String a[]){
         
        String[] strArr = {"JAVA", "C++", "PERL", "STRUTS", "PLAY"};
        Arrays.sort(strArr,new Comparator<String>(){
 
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }});
         
        for(String str:strArr){
            System.out.println(str);
        }
    }
}

*/

///// --- Constructor ---- //////
/*

**** Default Constructor ****

public class MyDefaultConstructor {
     
    public MyDefaultConstructor(){
        System.out.println("I am inside default constructor...");
    }
     
    public static void main(String a[]){
        MyDefaultConstructor mdc = new MyDefaultConstructor();
    }
}

**** Parameterized Constructor ****
public class MyParameterizedConstructor {
     
    private String name;
     
    public MyParameterizedConstructor(String str){
        this.name = str;
        System.out.println("I am inside parameterized constructor.");
        System.out.println("The parameter value is: "+str);
    }
     
    public static void main(String a[]){
        MyParameterizedConstructor mpc = new MyParameterizedConstructor("Madhu Raj");
    }
}

****  Constructor Overloading ****

public class MyOverloading {
     
    public MyOverloading(){
        System.out.println("Inside default constructor");
    }
    public MyOverloading(int i){
        System.out.println("Inside single parameter constructor with int value");
    }
    public MyOverloading(String str){
        System.out.println("Inside single parameter constructor with String object");
    }
    public MyOverloading(int i, int j){
        System.out.println("Inside double parameter constructor");
    }
     
    public static void main(String a[]){
        MyOverloading mco = new MyOverloading();
        MyOverloading spmco = new MyOverloading(10);
        MyOverloading dpmco = new MyOverloading(10,20);
        MyOverloading dpmco = new MyOverloading("java2novice");
    }
}

****  Constructor Chaining ****

public class MyChaining {
     
    public MyChaining(){
        System.out.println("In default constructor...");
    }
    public MyChaining(int i){
        this();
        System.out.println("In single parameter constructor...");
    }
    public MyChaining(int i,int j){
        this(j);
        System.out.println("In double parameter constructor...");
    }
     
    public static void main(String a[]){
        MyChaining ch = new MyChaining(10,20);
    }
}

**** Private Constructor ****

public class MySingleTon {
     
    private static MySingleTon myObj;
    
    // Create private constructor
    
    private MySingleTon(){
         
    }
    
     // Create a static method to get instance.
    
    public static MySingleTon getInstance(){
        if(myObj == null){
            myObj = new MySingleTon();
        }
        return myObj;
    }
     
    public void getSomeThing(){
        // do something here
        System.out.println("I am here....");
    }
     
    public static void main(String a[]){
        MySingleTon st = MySingleTon.getInstance();
        st.getSomeThing();
    }
}

*/


////// ---- Exceptions ---- /////
/*

**** Handling Eceptions ****

public class MyExceptionHandle {
    public static void main(String a[]){
        try{
            for(int i=5;i>=0;i--){
                System.out.println(10/i);
            }
        } catch(Exception ex){
            System.out.println("Exception Message: "+ex.getMessage());
            ex.printStackTrace();
        }
        System.out.println("After for loop...");
    }
}

**** Thows ****

public class MyThrowsClause {
    public static void main(String a[]){
        MyThrowsClause mytc = new MyThrowsClause();
        try{
            for(int i=0; i<5; i++){
                mytc.getJunk();
                System.out.println(i);
            }
        } catch (InterruptedException iex){
            iex.printStackTrace();
        }
    }
     
    public void getJunk() throws InterruptedException {
        Thread.sleep(1000);
    }
}

**** Multiple catch ****

import java.net.MalformedURLException;
import java.net.URL;
 
public class MyMultipleCatchBlocks {
     
    public static void main(String a[]){
        MyMultipleCatchBlocks mmcb = new MyMultipleCatchBlocks();
        mmcb.execute(1);
        mmcb.execute(2);
    }
     
    public void execute(int i){
        try{
            if(i == 1){
                getIntValue("7u");
            } else {
                getUrlObj("www.junksite.com");
            }
        } catch (NumberFormatException nfe){
            System.out.println("Inside NumberFormatException... "+nfe.getMessage());
        } catch (MalformedURLException mue){
            System.out.println("Inside MalformedURLException... "+mue.getMessage());
        } catch (Exception ex){
            System.out.println("Inside Exception... "+ex.getMessage());
        }
    }
    public int getIntValue(String num){
        return Integer.parseInt(num);
    }
     
    public URL getUrlObj(String urlStr) throws MalformedURLException{
        return new URL(urlStr);
    }
}
**** finally ****

public class MyFinallyBlock {
    public static void main(String[] a){
        
        // Exception will occur here, after catch block
        // the contol will goto finally block. 
         
        try{
            int i = 10/0;
        } catch(Exception ex){
            System.out.println("Inside 1st catch Block");
        } finally {
            System.out.println("Inside 1st finally block");
        }
        
        // In this case exception won't, after executing try block
        // the contol will goto finally block. 
         
        try{
            int i = 10/10;
        } catch(Exception ex){
            System.out.println("Inside 2nd catch Block");
        } finally { // finally always executes
            System.out.println("Inside 2nd finally block");
        }
    }
}

**** Handle exception without catch ****

import java.net.MalformedURLException;
import java.net.URL;
 
public class MyTryBlockOnly {
    public static void main(String a[]) throws MalformedURLException{
        try{
            URL url = new URL("http://www.google.com");
        } finally{
            System.out.println("In finally block");
        }
    }
}

**** Creating your own exception ****

public class MyOwnException {
    public static void main(String[] a){
        try{
            MyOwnException.myTest(null);
        } catch(MyAppException mae){
            System.out.println("Inside catch block: "+mae.getMessage());
        }
    }
     
    static void myTest(String str) throws MyAppException{
        if(str == null){
            throw new MyAppException("String val is null");
        }
    }
}
 
class MyAppException extends Exception {
 
    private String message = null;
 
    public MyAppException() {
        super();
    }
 
    public MyAppException(String message) {
        super(message);
        this.message = message;
    }
 
    public MyAppException(Throwable cause) {
        super(cause);
    }
 
    @Override
    public String toString() {
        return message;
    }
 
    @Override
    public String getMessage() {
        return message;
    }
}

*/

////// ---- Threads ---- ///////

/*

In Java threads can be implemented in two ways. One is by 'Extending Thread Class' and the other way is by 
'Implementing Runnable Interface'
Extending Thread Class is required to 'override run()' method. The run method contains the actual logic to be 
executed by thread. join(), sleep(), yield(), noify(), notifyall(), wait().


****  Implementing Runnable  ****

class MyRunnableThread implements Runnable{
 
    public static int myCount = 0;
    public MyRunnableThread(){
         
    }
    public void run() {
        while(MyRunnableThread.myCount <= 10){
            try{
                System.out.println("Expl Thread: "+(++MyRunnableThread.myCount));
                Thread.sleep(100);
            } catch (InterruptedException iex) {
                System.out.println("Exception in thread: "+iex.getMessage());
            }
        }
    } 
}

public class RunMyThread {
    public static void main(String a[]){
        System.out.println("Starting Main Thread...");
        MyRunnableThread mrt = new MyRunnableThread();
        Thread t = new Thread(mrt);
        t.start();
        while(MyRunnableThread.myCount <= 10){
            try{
                System.out.println("Main Thread: "+(++MyRunnableThread.myCount));
                Thread.sleep(100);
            } catch (InterruptedException iex){
                System.out.println("Exception in main thread: "+iex.getMessage());
            }
        }
        System.out.println("End of Main Thread...");
    }
}

**** extending Thread ****

class MySmpThread extends Thread{
    public static int myCount = 0;
    public void run(){
        while(MySmpThread.myCount <= 10){
            try{
                System.out.println("Expl Thread: "+(++MySmpThread.myCount));
                Thread.sleep(100);
            } catch (InterruptedException iex) {
                System.out.println("Exception in thread: "+iex.getMessage());
            }
        }
    }
}
public class RunThread {
    public static void main(String a[]){
        System.out.println("Starting Main Thread...");
        MySmpThread mst = new MySmpThread();
        mst.start();
        while(MySmpThread.myCount <= 10){
            try{
                System.out.println("Main Thread: "+(++MySmpThread.myCount));
                Thread.sleep(100);
            } catch (InterruptedException iex){
                System.out.println("Exception in main thread: "+iex.getMessage());
            }
        }
        System.out.println("End of Main Thread...");
    }
}

**** Daemon ****

public class DaemonThread extends Thread{
     
    public DaemonThread(){
        setDaemon(true);
    }
    public void run(){
        System.out.println("Is this thread Daemon? - "+isDaemon());
    }
    public static void main(String a[]){
        DaemonThread dt = new DaemonThread();
        // even you can set daemon constrain here also
        // it is like dt.setDeamon(true)
        dt.start();
    }
}

**** join thread ****

import java.util.ArrayList;
import java.util.List;
 
public class MyThreadJoin {
 
    public static List<String> names = new ArrayList<String>(); 
     
    public static void main(String a[]){
        List<SampleThread> list = new ArrayList<SampleThread>();
        for(int i=0;i<5;i++){
            SampleThread s = new SampleThread();
            list.add(s);
            s.start();
        }
        for(SampleThread st:list){
            try{
                st.join();
            } catch (Exception ex){}
        }
        System.out.println(names);
    }
}
 
class SampleThread extends Thread{
     
    public void run(){
        for(int i=0; i<10; i++){
            try{
                Thread.sleep(10);
            } catch(Exception ex){}
        }
        MyThreadJoin.names.add(getName());
    }
}

**** yield ****
When a thread executes a thread yield, the executing thread is suspended and the CPU is given to some other runnable 
thread. This thread will wait until the CPU becomes available again.
Technically, in process scheduler's terminology, the executing thread is put back into the ready queue of the 
processor and waits for its next turn.

import java.util.ArrayList;
import java.util.List;
 
public class MyThreadYield {
    public static void main(String a[]){
        List<ExmpThread> list = new ArrayList<ExmpThread>();
        for(int i=0;i<3;i++){
            ExmpThread et = new ExmpThread(i+5);
            list.add(et);
            et.start();
        }
        for(ExmpThread et:list){
            try {
                et.join();
            } catch (InterruptedException ex) { }
        }
    }
}
 
class ExmpThread extends Thread{
     
    private int stopCount;
    public ExmpThread(int count){
        this.stopCount = count;
    }
    public void run(){
        for(int i=0;i<50;i++){
            if(i%stopCount == 0){
                System.out.println("Stoping thread: "+getName());
                yield();
            }
        }
    }
}

*/

///// ---- String ---- /////

/*

**** Initialization ****
public class MyStringInitialization {
    public static void main(String a[]){
        String abc = "This is a string object";
        String bcd = new String("this is also string object"); 
        char[] c = {'a','b','c','d'};
        String cdf = new String(c);
        String junk = abc+" This is another String object";
    }
}

**** Copy from Array ****

public class MyArrayCopy {
    public static void main(String a[]){
        char ch[] = {'M','y',' ','J','a','v','a',' ','e','x','a','m','p','l','e'};
        
        // We can copy a char array to a string by using 
        // copyValueOf() method.
         
        String chStr = String.copyValueOf(ch);
        System.out.println(chStr);
       
        // We can also copy only range of charactors in a 
        // char array by copyValueOf() method.
        
        String subStr = String.copyValueOf(ch,3,4);
        System.out.println(subStr);
    }
}

**** Concatenate ****
public class MyStringConcat {
    public static void main(String a[]){
        String b = "jump ";
        String c = "No jump";

        String d = b+c;
        System.out.println(d);

        d = b.concat(c);
        System.out.println(d);
    }
}

**** Compare ****
public class MyStringEquals {
    public static void main(String a[]){
        String x = "JUNK";
        String y = "junk";
    
        if(x.equals(y)){
            System.out.println("Both strings are equal.");
        } else {
            System.out.println("Both strings are not equal.");
        }
       
        if(x.equalsIgnoreCase(y)){
            System.out.println("Both strings are equal.");
        } else {
            System.out.println("Both strings are not equal.");
        }
    }
}

**** Getting bytes ****

public class MyStringBytes {
 
    public static void main(String a[]){
     
        String str = "core java api";
        byte[] b = str.getBytes();
        System.out.println("String length: "+str.length());
        System.out.println("Byte array length: "+b.length);
    }
}

**** IndexOf, last Index ****
public class MyStringIndexOf {
 
    public static void main(String[] a){
     
        String str = "Use this string for testing this";
        System.out.println("Basic indexOf() example");
        System.out.println("Char 's' at first occurance: "+str.indexOf('s'));
        System.out.println("String \"this\" at first occurance: "+str.indexOf("this"));
        
        // Returns the first occurance from specified start index
         
        System.out.println("First occurance of char 's' from 4th index onwards : "
                            +str.indexOf('s',4));
        System.out.println("First occurance of String \"this\" from 6th index onwards: "
                            +str.indexOf("this",6));
         
    }
}

public class MyStrLastIndexOf {
 
    public static void main(String a[]){
     
        String str = "Use this string for testing this";
        System.out.println("Basic lastIndexOf() example");
        System.out.println("Char 's' at last occurance: "+str.lastIndexOf('s'));
        System.out.println("String \"this\" at last occurance: "+str.lastIndexOf("this"));
        
        //Returns the last occurance from specified start index, 
        //searching backward starting at the specified index.
         
        System.out.println("first occurance of char 's' from 24th index backwards: "
                            +str.lastIndexOf('s',24));
        System.out.println("First occurance of String \"this\" from 26th index backwards: "
                            +str.lastIndexOf("this",26));
    }
}

**** StartWith EndWith ****

public class MyStrStartsWith {
 
    public static void main(String a[]){
     
        String str = "This is an example string.";
        System.out.println("Is this string starts with \"This\"? "
                        +str.startsWith("This"));
        System.out.println("Is this string starts with \"is\"? "
                        +str.startsWith("is"));
        System.out.println("Is this string starts with \"is\" after index 5? "
                        +str.startsWith("is", 5));
    }
}
public class MyStringEnd {
 
    public static void main(String a[]){
     
        String str = "This is a java string example";
        if(str.endsWith("example")){
            System.out.println("This String ends with example");
        } else {
            System.out.println("This String is not ending with example");
        }
        if(str.endsWith("java")){
            System.out.println("This String ends with java");
        } else {
            System.out.println("This String is not ending with java");
        }
    }
}

**** split ****
public class MyStrSplit {
 
    public static void main(String a[]){
     
        String str = "This program splits a string based on space";
        String[] tokens = str.split(" ");
        for(String s:tokens){
            System.out.println(s);
        }
        str = "This     program  splits a string based on space";
        tokens = str.split("\\s+");
    }
}

**** char array from String ****
public class MyCharArrayCopy {
 
    public static void main(String a[]){
     
        String str = "Copy chars from this string";
        char[] ch = new char[5];
        
         * The getChars() method accepts 4 parameters
         * first one is the start index from string
         * second one is the end index from string
         * third one is the destination char array
         * forth one is the start index to append in the 
         * char array.
        
        str.getChars(5, 10, ch, 0);
        System.out.println(ch);
    }
}

**** Replace characters ****

public class MyStringReplace {
     
    public static void main(String a[]){
     
        String str = "This is an example string";
        System.out.println("Replace char 's' with 'o':"
                    +str.replace('s', 'o'));
                     
        System.out.println("Replace first occurance of string\"is\" with \"ui\":"
                    +str.replaceFirst("is", "ui"));
                     
        System.out.println("Replacing \"is\" every where with \"no\":"
                    +str.replaceAll("is", "no"));
    }
}

**** Upper, Lower ****

public class MyStringCase {
    public static void main(String a[]){
        String str = "Change My Case";
        System.out.println("Upper Case: "+str.toUpperCase());
        System.out.println("Lower Case: "+str.toLowerCase());
    }
}

**** Trim spaces ****

public class MyStringTrim {
    public static void main(String a[]){
        String str = "  Junk   ";
        System.out.println(str.trim());
    }
}

**** String format ****

public class MyStringFormatter {
 
    public static void main(String a[]){
     
        String str = "This is %s format example";
        System.out.println(String.format(str, "string"));
        String str1 = "We are displaying no %d";
        System.out.println(String.format(str1, 10));
        
        * String format by specifying Locale details
        
        System.out.println("String format with Locale info:");
        System.out.println(String.format(Locale.US, str1, 10));
    }
}

**** Reg Expressions ****

public class MyStrMatches {
 
    public static void main(String a[]){
     
        String[] str = {"www.java2novice.com", "http://java2novice.com"};
        for(int i=0;i < str.length;i++){
            if(str[i].matches("^www\\.(.+)")){
                System.out.println(str[i]+" Starts with 'www'");
            } else {
                System.out.println(str[i]+" is not starts with 'www'");
            }
        }
    }
}

**** Delete spaces ****
import java.util.StringTokenizer;
 
public class MyStrRemoveMultSpaces {
    public static void main(String a[]){
        String str = "String    With Multiple      Spaces";
        StringTokenizer st = new StringTokenizer(str, " ");
        StringBuffer sb = new StringBuffer();
        while(st.hasMoreElements()){
            sb.append(st.nextElement()).append(" ");
        }
        System.out.println(sb.toString().trim());
    }
}

**** Removing non-ascii ****

public class MyNonAsciiString {
 
    public static void main(String a[]){
        String str = "Bj��rk����oacute�";
        System.out.println(str);
        str = str.replaceAll("[^\\p{ASCII}]", "");
        System.out.println("After removing non ASCII chars:");
        System.out.println(str);
    }
}

**** Removing HTML tags ****

public class MyHtmlTagRemover {
 
    public static void main(String a[]){
        String text = "<B>I don't want this to be bold<\\B>";
        System.out.println(text);
        text = text.replaceAll("\\<.*?\\>", "");
        System.out.println(text);
    }
}

**** Getting line count ****

public class MyStringLineCounter {
 
    public static int getLineCount(String text){
         
        return text.split("[\n|\r]").length;
    }
     
    public static void main(String a[]){
     
        String str = "line1\nline2\nline3\rline4";
        System.out.println(str);
        int count = getLineCount(str);
        System.out.println("line count: "+count);
    }
}

*/

////// ---- Generics ---- /////

/*

**** Simple Generics ****

public class MySimpleGenerics {
 
    public static void main(String a[]){
         
        //we are going to create SimpleGeneric object with String as type parameter
        SimpleGeneric<String> sgs = new SimpleGeneric<String>("JAVA2NOVICE");
        sgs.printType();
        //we are going to create SimpleGeneric object with Boolean as type parameter
        SimpleGeneric<Boolean> sgb = new SimpleGeneric<Boolean>(Boolean.TRUE);
        sgb.printType();
    }
}
 
 * Here T is a type parameter, and it will be replaced with 
 * actual type when the object got created. 
 
class SimpleGeneric<T>{
     
    //declaration of object type T
    private T objReff = null;
     
    //constructor to accept type parameter T
    public SimpleGeneric(T param){
        this.objReff = param;
    }
     
    public T getObjReff(){
        return this.objReff;
    }
     
    //this method prints the holding parameter type
    public void printType(){
        System.out.println("Type: "+objReff.getClass().getName());
    }
}

**** Generics bounded types ****
You can restrict the generics type parameter to a certain group of objects which extends same super class.

public class MyBoundedClassEx {
 
    public static void main(String a[]){
        //Creating object of sub class C and 
        //passing it to BoundEx as a type parameter.
        BoundEx<C> bec = new BoundEx<C>(new C());
        bec.doRunTest();
        //Creating object of sub class B and 
        //passing it to BoundEx as a type parameter.
        BoundEx<B> beb = new BoundEx<B>(new B());
        beb.doRunTest();
        //similarly passing super class A
        BoundEx<A> bea = new BoundEx<A>(new A());
        bea.doRunTest();
        //If you uncomment below code it will throw compiler error
        //becasue we restricted to only of type A and its sub classes.
        //BoundEx<String> bes = new BoundEx<String>(new String());
        //bea.doRunTest();
    }
}

 * This class only accepts type parametes as any class
 * which extends class A or class A itself.
 * Passing any other type will cause compiler time error
 
class BoundEx<T extends A>{
     
    private T objRef;
     
    public BoundEx(T obj){
        this.objRef = obj;
    }
     
    public void doRunTest(){
        this.objRef.printClass();
    }
}
 
class A{
    public void printClass(){
        System.out.println("I am in super class A");
    }
}
 
class B extends A{
    public void printClass(){
        System.out.println("I am in sub class B");
    }
}
 
class C extends A{
    public void printClass(){
        System.out.println("I am in sub class C");
    }
}

**** Bounds to an Interface ****

public class MyBoundedInterface {
 
    public static void main(String a[]){
         
        //Creating object of implementation class X called Y and 
        //passing it to BoundExmp as a type parameter.
        BoundExmp<Y> bey = new BoundExmp<Y>(new Y());
        bey.doRunTest();
        //Creating object of implementation class X called Z and 
        //passing it to BoundExmp as a type parameter.
        BoundExmp<Z> bez = new BoundExmp<Z>(new Z());
        bez.doRunTest();
        //If you uncomment below code it will throw compiler error
        //becasue we restricted to only of type X  implementation classes.
        //BoundExmp<String> bes = new BoundExmp<String>(new String());
        //bea.doRunTest();
    }
}
 
class BoundExmp<T extends X>{
     
    private T objRef;
     
    public BoundExmp(T obj){
        this.objRef = obj;
    }
     
    public void doRunTest(){
        this.objRef.printClass();
    }
}
 
interface X{
    public void printClass();
}
 
class Y implements X{
    public void printClass(){
        System.out.println("I am in class Y");
    }
}
 
class Z implements X{
    public void printClass(){
        System.out.println("I am in class Z");
    }
}


**** Generics Wild ? ****

//The next code won't work. Because once you create an object of MyEmployeeUtil class, the type argument will be s
pecific to an instance type. So you can compare between only same object types, ie, you can comapare either 
objects of CompAEmp or CompBEmp, but not between CompAEmp and CompBEmp. To solve this problem, wildcard argument 
will helps you.

public boolean isSalaryEqual(MyEmployeeUtil<T> otherEmp){
     
    if(emp.getSalary() == otherEmp.getSalary()){
        return true;
    }
    return false;
} 

public boolean isSalaryEqual(MyEmployeeUtil<?> otherEmp){
     
    if(emp.getSalary() == otherEmp.getSalary()){
        return true;
    }
    return false;
}       

public class MyWildcardEx {
 
    public static void main(String a[]){
         
        MyEmployeeUtil<CompAEmp> empA
                    = new MyEmployeeUtil<CompAEmp>(new CompAEmp("Ram", 20000));
        MyEmployeeUtil<CompBEmp> empB
                    = new MyEmployeeUtil<CompBEmp>(new CompBEmp("Krish", 30000));
        MyEmployeeUtil<CompAEmp> empC
                    = new MyEmployeeUtil<CompAEmp>(new CompAEmp("Nagesh", 20000));
        System.out.println("Is salary same? "+empA.isSalaryEqual(empB));
        System.out.println("Is salary same? "+empA.isSalaryEqual(empC));
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
*/

////// **** Collections **** /////

/*

**** Iterate to collections ****

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
 
public class MyCollectionIterator {
 
    public static void main(String a[]){
         
        List<String> myList = new ArrayList<String>();
        myList.add("Java");
        myList.add("Unix");
        myList.add("Oracle");
        myList.add("C++");
        myList.add("Perl");
        Iterator<String> itr = myList.iterator();
        while(itr.hasNext()){
            System.out.println(itr.next());
        }
    }
}

**** Remove items from collections ****

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
 
public class MyItrRemoveElement {
 
    public static void main(String a[]){
         
        String removeElem = "Perl";
        List<String> myList = new ArrayList<String>();
        myList.add("Java");
        myList.add("Unix");
        myList.add("Oracle");
        myList.add("C++");
        myList.add("Perl");
        System.out.println("Before remove:");
        System.out.println(myList);
        Iterator<String> itr = myList.iterator();
        while(itr.hasNext()){
            if(removeElem.equals(itr.next())){
                itr.remove();
            }
        }
        System.out.println("After remove:");
        System.out.println(myList);
    }
}

**** List ****

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
 
public class MyListIterator {
    public static void main(String a[]){
        List<Integer> li = new ArrayList<Integer>();
        ListIterator<Integer> litr = null;
        li.add(23);
        li.add(98);
        li.add(29);
        li.add(71);
        li.add(5);
        litr=li.listIterator();
        System.out.println("Elements in forward directiton");
        while(litr.hasNext()){
            System.out.println(litr.next());
        }
        System.out.println("Elements in backward directiton");
        while(litr.hasPrevious()){
            System.out.println(litr.previous());
        }
    }
}

**** Enumerator ****

import java.util.Enumeration;
import java.util.Vector;
 
public class MyEnumeration {
    public static void main(String a[]){
        Vector<String> lang = new Vector<String>();
        Enumeration<String> en = null;
        lang.add("JAVA");
        lang.add("JSP");
        lang.add("SERVLET");
        lang.add("EJB");
        lang.add("PHP");
        lang.add("PERL");
        en = lang.elements();
        while(en.hasMoreElements()){
            System.out.println(en.nextElement());
        }
    }
}

**** Vector ****
package com.java2novice.vector;
 
import java.util.Vector;
 
public class BasicVectorOperations {
 
    public static void main(String a[]){
        Vector<String> vct = new Vector<String>();
        //adding elements to the end
        vct.add("First");
        vct.add("Second");
        vct.add("Third");
        System.out.println(vct);
        //adding element at specified index
        vct.add(2,"Random");
        System.out.println(vct);
        //getting elements by index
        System.out.println("Element at index 3 is: "+vct.get(3));
        //getting first element
        System.out.println("The first element of this vector is: "+vct.firstElement());
        //getting last element
        System.out.println("The last element of this vector is: "+vct.lastElement());
        //how to check vector is empty or not
        System.out.println("Is this vector empty? "+vct.isEmpty());
    }
}
import java.util.Iterator;
import java.util.Vector;
 
public class VectorIterator {
 
    public static void main(String a[]){
        Vector<String> vct = new Vector<String>();
        //adding elements to the end
        vct.add("First");
        vct.add("Second");
        vct.add("Third");
        vct.add("Random");
        Iterator<String> itr = vct.iterator();
        while(itr.hasNext()){
            System.out.println(itr.next());
        }
    }
}

- copy a vector
import java.util.Vector;
 
public class MyVectorClone {
    public static void main(String a[]){
        Vector<String> vct = new Vector<String>();
        //adding elements to the end
        vct.add("First");
        vct.add("Second");
        vct.add("Third");
        vct.add("Random");
        System.out.println("Actual vector:"+vct);
        Vector<String> copy = (Vector<String>) vct.clone();
        System.out.println("Cloned vector:"+copy);
    }
}

- add from a list
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
 
public class MyVectorNewCollection {
 
    public static void main(String a[]){
        Vector<String> vct = new Vector<String>();
        //adding elements to the end
        vct.add("First");
        vct.add("Second");
        vct.add("Third");
        vct.add("Random");
        System.out.println("Actual vector:"+vct);
        List<String> list = new ArrayList<String>();
        list.add("one");
        list.add("two");
        vct.addAll(list);
        System.out.println("After Copy: "+vct);
    }
}

- delete all
import java.util.Vector;
 
public class ClearMyVector {
 
    public static void main(String a[]){
        Vector<String> vct = new Vector<String>();
        //adding elements to the end
        vct.add("First");
        vct.add("Second");
        vct.add("Third");
        vct.add("Random");
        System.out.println("Actual vector:"+vct);
        vct.clear();
        System.out.println("After clear vector:"+vct);
    }
}

- contains all
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
 
public class MyElementCheck {
 
    public static void main(String a[]){
        Vector<String> vct = new Vector<String>();
        vct.add("First");
        vct.add("Second");
        vct.add("Third");
        vct.add("Random");
        List<String> list = new ArrayList<String>();
        list.add("Second");
        list.add("Random");
        System.out.println("Does vector contains all list elements?: "+vct.containsAll(list));
        list.add("one");
        System.out.println("Does vector contains all list elements?: "+vct.containsAll(list));
    }
}

- copy vector to an array

import java.util.Vector;
 
public class MyVectorArrayCopy {
 
    public static void main(String a[]){
        Vector<String> vct = new Vector<String>();
        vct.add("First");
        vct.add("Second");
        vct.add("Third");
        vct.add("Random");
        System.out.println("Actual vector:"+vct);
        String[] copyArr = new String[vct.size()];
        vct.copyInto(copyArr);
        System.out.println("Copied Array content:");
        for(String str:copyArr){
            System.out.println(str);
        }
    }
}

- get sublist
import java.util.List;
import java.util.Vector;
 
public class MyVectorSubRange {
 
    public static void main(String a[]){
        Vector<String> vct = new Vector<String>();
        //adding elements to the end
        vct.add("First");
        vct.add("Second");
        vct.add("Third");
        vct.add("Random");
        vct.add("Click");
        System.out.println("Actual vector:"+vct);
        List<String> list = vct.subList(2, 4);
        System.out.println("Sub List: "+list);
    }
}

**** Array List ****
import java.util.ArrayList;
 
public class MyBasicArrayList {
 
    public static void main(String[] a){
         
        ArrayList<String> al = new ArrayList<String>();
        //add elements to the ArrayList
        al.add("JAVA");
        al.add("C++");
        al.add("PERL");
        al.add("PHP");
        System.out.println(al);
        //get elements by index
        System.out.println("Element at index 1: "+al.get(1));
        System.out.println("Does list contains JAVA? "+al.contains("JAVA"));
        //add elements at a specific index
        al.add(2,"PLAY");
        System.out.println(al);
        System.out.println("Is arraylist empty? "+al.isEmpty());
        System.out.println("Index of PERL is "+al.indexOf("PERL"));
        System.out.println("Size of the arraylist is: "+al.size());
    }
}

import java.util.ArrayList;
import java.util.Iterator;
 
public class ArrayListIterator {
 
    public static void main(String a[]){
        ArrayList<String> arrl = new ArrayList<String>();
        //adding elements to the end
        arrl.add("First");
        arrl.add("Second");
        arrl.add("Third");
        arrl.add("Random");
        Iterator<String> itr = arrl.iterator();
        while(itr.hasNext()){
            System.out.println(itr.next());
        }
    }
}

**** Linked List ****

import java.util.LinkedList;
 
public class MyBasicOperations {
 
    public static void main(String a[]){
         
        LinkedList<String> ll = new LinkedList<String>();
        ll.add("Orange");
        ll.add("Apple");
        ll.add("Grape");
        ll.add("Banana");
        System.out.println(ll);
        System.out.println("Size of the linked list: "+ll.size());
        System.out.println("Is LinkedList empty? "+ll.isEmpty());
        System.out.println("Does LinkedList contains 'Grape'? "+ll.contains("Grape"));
    }
}

import java.util.Iterator;
import java.util.LinkedList;
 
public class MyLinkedListIterate {
 
    public static void main(String a[]){
        LinkedList<String> arrl = new LinkedList<String>();
        //adding elements to the end
        arrl.add("First");
        arrl.add("Second");
        arrl.add("Third");
        arrl.add("Random");
        Iterator<String> itr = arrl.iterator();
        while(itr.hasNext()){
            System.out.println(itr.next());
        }
    }
}

- sorting 
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
 
public class MyLinkedListSort {
 
    public static void main(String a[]){
         
        LinkedList<Empl> list = new LinkedList<Empl>();
        list.add(new Empl("Ram",3000));
        list.add(new Empl("John",6000));
        list.add(new Empl("Crish",2000));
        list.add(new Empl("Tom",2400));
        Collections.sort(list,new MySalaryComp());
        System.out.println("Sorted list entries: ");
        for(Empl e:list){
            System.out.println(e);
        }
    }
}
 
class MySalaryComp implements Comparator<Empl>{
 
    @Override
    public int compare(Empl e1, Empl e2) {
        if(e1.getSalary() < e2.getSalary()){
            return 1;
        } else {
            return -1;
        }
    }
}
 
class Empl{
     
    private String name;
    private int salary;
     
    public Empl(String n, int s){
        this.name = n;
        this.salary = s;
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
    public String toString(){
        return "Name: "+this.name+"-- Salary: "+this.salary;
    }
}

**** Hash Table ****
import java.util.Hashtable;
 
public class MyHashtableOperations {
 
    public static void main(String a[]){
        //Create hashtable instance
        Hashtable<String,String> ht = new Hashtable<String,String>();
        //add key-value pair to hashtable
        ht.put("first", "FIRST INSERTED");
        ht.put("second", "SECOND INSERTED");
        ht.put("third","THIRD INSERTED");
        System.out.println(ht);
        //getting value for the given key from hashtable
        System.out.println("Value of key 'second': "+ht.get("second"));
        System.out.println("Is Hashtable empty? "+ht.isEmpty());
        ht.remove("third");
        System.out.println(ht);
        System.out.println("Size of the Hashtable: "+ht.size());
    }
}

- Iterate 
import java.util.Hashtable;
import java.util.Set;
 
public class MyHashtableRead {
 
    public static void main(String a[]){
         
        Hashtable<String, String> hm = new Hashtable<String, String>();
        //add key-value pair to Hashtable
        hm.put("first", "FIRST INSERTED");
        hm.put("second", "SECOND INSERTED");
        hm.put("third","THIRD INSERTED");
        System.out.println(hm);
        Set<String> keys = hm.keySet();
        for(String key: keys){
            System.out.println("Value of "+key+" is: "+hm.get(key));
        }
    }
}

- Find
import java.util.Hashtable;
 
public class MyHashtableKeySearch {
 
    public static void main(String a[]){
        Hashtable<String, String> hm = new Hashtable<String, String>();
        //add key-value pair to Hashtable
        hm.put("first", "FIRST INSERTED");
        hm.put("second", "SECOND INSERTED");
        hm.put("third","THIRD INSERTED");
        System.out.println(hm);
        if(hm.containsKey("first")){
            System.out.println("The Hashtable contains key first");
        } else {
            System.out.println("The Hashtable does not contains key first");
        }
        if(hm.containsKey("fifth")){
            System.out.println("The Hashtable contains key fifth");
        } else {
            System.out.println("The Hashtable does not contains key fifth");
        }
    }
}

**** Hash Set ****
import java.util.HashSet;
 
public class MyBasicHashSet {
 
    public static void main(String a[]){
        HashSet<String> hs = new HashSet<String>();
        //add elements to HashSet
        hs.add("first");
        hs.add("second");
        hs.add("third");
        System.out.println(hs);
        System.out.println("Is HashSet empty? "+hs.isEmpty());
        hs.remove("third");
        System.out.println(hs);
        System.out.println("Size of the HashSet: "+hs.size());
        System.out.println("Does HashSet contains first element? "+hs.contains("first"));
    }
}

**** Linked HashSet ****

import java.util.LinkedHashSet;
 
public class MyLkdHashSetOperations {
 
    public static void main(String a[]){
         
        LinkedHashSet<String> lhs = new LinkedHashSet<String>();
        //add elements to HashSet
        lhs.add("first");
        lhs.add("second");
        lhs.add("third");
        System.out.println(lhs);
        System.out.println("LinkedHashSet size: "+lhs.size());
        System.out.println("Is LinkedHashSet emplty? : "+lhs.isEmpty());
    }
}

- Remove element
import java.util.LinkedHashSet;
 
public class MyLhsDeleteEx {
 
    public static void main(String a[]){
         
        LinkedHashSet<String> lhs = new LinkedHashSet<String>();
        //add elements to HashSet
        lhs.add("first");
        lhs.add("second");
        lhs.add("third");
        System.out.println(lhs);
        lhs.remove("second");
        System.out.println("Elements after deleting an element:");
        System.out.println(lhs);
    }
}

**** TreeSet ****
import java.util.TreeSet;
 
public class MyBasicTreeset {
 
    public static void main(String a[]){
         
        TreeSet<String> ts = new TreeSet<String>();
        ts.add("one");
        ts.add("two");
        ts.add("three");
        System.out.println("Elements: "+ts);
        //check is set empty?
        System.out.println("Is set empty: "+ts.isEmpty());
        //delete all elements from set
        ts.clear();
        System.out.println("Is set empty: "+ts.isEmpty());
        ts.add("one");
        ts.add("two");
        ts.add("three");
        System.out.println("Size of the set: "+ts.size());
        //remove one string
        ts.remove("two");
        System.out.println("Elements: "+ts);
    }
}

**** HashMap ****
import java.util.HashMap;
 
public class MyBasicHashMap {
 
    public static void main(String a[]){
        HashMap<String, String> hm = new HashMap<String, String>();
        //add key-value pair to hashmap
        hm.put("first", "FIRST INSERTED");
        hm.put("second", "SECOND INSERTED");
        hm.put("third","THIRD INSERTED");
        System.out.println(hm);
        //getting value for the given key from hashmap
        System.out.println("Value of second: "+hm.get("second"));
        System.out.println("Is HashMap empty? "+hm.isEmpty());
        hm.remove("third");
        System.out.println(hm);
        System.out.println("Size of the HashMap: "+hm.size());
    }
}

- search
import java.util.HashMap;
 
public class MyHashMapKeySearch {
     
    public static void main(String a[]){
        HashMap<String, String> hm = new HashMap<String, String>();
        //add key-value pair to hashmap
        hm.put("first", "FIRST INSERTED");
        hm.put("second", "SECOND INSERTED");
        hm.put("third","THIRD INSERTED");
        System.out.println(hm);
        if(hm.containsKey("first")){
            System.out.println("The hashmap contains key first");
        } else {
            System.out.println("The hashmap does not contains key first");
        }
        if(hm.containsKey("fifth")){
            System.out.println("The hashmap contains key fifth");
        } else {
            System.out.println("The hashmap does not contains key fifth");
        }
    }
}

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;
 
public class MyHashMapEntrySet {
 
    public static void main(String a[]){
        HashMap<String, String> hm = new HashMap<String, String>();
        //add key-value pair to hashmap
        hm.put("first", "FIRST INSERTED");
        hm.put("second", "SECOND INSERTED");
        hm.put("third","THIRD INSERTED");
        System.out.println(hm);
        //getting value for the given key from hashmap
        Set<Entry<String, String>> entires = hm.entrySet();
        for(Entry<String,String> ent:entires){
            System.out.println(ent.getKey()+" ==> "+ent.getValue());
        }
    }
}

**** Tree Maps ****

import java.util.TreeMap;
 
public class MyBasicOperations {
     
    public static void main(String a[]){
        TreeMap<String, String> hm = new TreeMap<String, String>();
        //add key-value pair to TreeMap
        hm.put("first", "FIRST INSERTED");
        hm.put("second", "SECOND INSERTED");
        hm.put("third","THIRD INSERTED");
        System.out.println(hm);
        //getting value for the given key from TreeMap
        System.out.println("Value of second: "+hm.get("second"));
        System.out.println("Is TreeMap empty? "+hm.isEmpty());
        hm.remove("third");
        System.out.println(hm);
        System.out.println("Size of the TreeMap: "+hm.size());
    }
}

**** Linked HashMaps
import java.util.LinkedHashMap;
 
public class BasicLinkedHashMap {
 
    public static void main(String a[]){
         
        LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
        lhm.put("one", "This is first element");
        lhm.put("two", "This is second element");
        lhm.put("four", "this element inserted at 3rd position");
        System.out.println(lhm);
        System.out.println("Getting value for key 'one': "+lhm.get("one"));
        System.out.println("Size of the map: "+lhm.size());
        System.out.println("Is map empty? "+lhm.isEmpty());
        System.out.println("Contains key 'two'? "+lhm.containsKey("two"));
        System.out.println("Contains value 'This is first element'? "
                            +lhm.containsValue("This is first element"));
        System.out.println("delete element 'one': "+lhm.remove("one"));
        System.out.println(lhm);
    }
}

**** Collections ****
The Collections class consists of static methods that exclusively operates on collections and returns collections. 
It contains polymorphic algorithms that operate on collections, "wrappers", which return a new collection backed 
by a specified collection, and a few other odds and ends. The methods of Collections class all throw a 
NullPointerException if the collections or class objects provided to them are null.

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
 
public class MyMaxNumber {
 
    public static void main(String a[]){
         
        List<Integer> li = new ArrayList<Integer>();
        li.add(23);
        li.add(44);
        li.add(12);
        li.add(45);
        li.add(2);
        li.add(16);
        System.out.println("Maximum element from the list: "+Collections.max(li));
    }
}


*/

/////// **** Nested Classes **** //////
/*

- static
public class MyBasicStaticMemberClass {
 
    public static class MyStaticMemberExampleClass {
 
        public void printStatus() {
            System.out.println("Hey I am inside static member class");
        }
    }
 
    public static void main(String a[]) {
        StaticMemberTestClass smt = new StaticMemberTestClass();
        smt.testMemberClass();
    }
}
 
class StaticMemberTestClass {
 
    public void testMemberClass() {
        MyBasicStaticMemberClass.MyStaticMemberExampleClass msme 
                    = new MyBasicStaticMemberClass.MyStaticMemberExampleClass();
        msme.printStatus();
    }
}

-static interface
public class MyStaticMemberInterfaceTest 
            implements MyStaticMemberInterface.MyStaticInterface{
 
    public void implementMe(){
        System.out.println("Hey I have implemented successfully!!!");
    }
     
    public static void main(String a[]){
        MyStaticMemberInterfaceTest msi = new MyStaticMemberInterfaceTest();
        msi.implementMe();
    }
}
 
class MyStaticMemberInterface{
     
    public static interface MyStaticInterface{
        public void implementMe();
    }
}


*/

////// ---- URL ---- //////

/*

import java.net.MalformedURLException;
import java.net.URL;
 
public class MySimpleUrl {
    public static void main(String a[]){
        try {
            String myUrl = "http://www.java2novice.com";
            URL url = new URL(myUrl);
            System.out.println(url.toString());
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
    }
}

import java.net.MalformedURLException;
import java.net.URL;
 
public class MyUrlConstruct {
 
    public static void main(String a[]){
     
        try {
            String protocol = "http";
            String host = "www.java2novice.com";
            int port = 80;
            String path = "/java_thread_examples/";
            URL url = new URL(protocol, host, port, path);
            System.out.println(url.toString());
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
    }
}

-parsing

import java.net.MalformedURLException;
import java.net.URL;
 
public class MyUrlProperties {
 
    public static void main(String a[]){
     
        try {
            String url =
        "http://www.java2novice.com:80/java_constructor_examples/?query=ok#header";
            URL myUrl = new URL(url);
            System.out.println("Protocol: "+myUrl.getProtocol());
            System.out.println("Host: "+myUrl.getHost());
            System.out.println("Port: "+myUrl.getPort());
            System.out.println("Athority of the URL: "+myUrl.getAuthority());
            System.out.println("Query: "+myUrl.getQuery());
            System.out.println("Reference: "+myUrl.getRef());
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
    }
}

-open and read 

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
 
public class MyUrlContentRead {
 
    public static void main(String a[]){
     
        URL url = null;
        InputStream is = null;
        try {
            url = new URL("http://www.java2novice.com");
            is = url.openStream();
            byte[] b = new byte[8];
            while(is.read(b) != -1){
                System.out.print(new String(b));
            }
        }  catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

- encoder

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
 
public class MyUrlEncode {
 
    public static void main(String a[]){
     
        try {
            System.out.println(URLEncoder.encode("String with spaces", "UTF-8"));
            System.out.println(URLEncoder.encode("special chars: &%*", "UTF-8"));
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
    }
}

- decoding

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
 
public class MyURLDecode {
    public static void main(String a[]){
        try {
            System.out.println(URLDecoder.decode("special+chars%3A+%26%25*+", "UTF-8"));
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
    }
}

- IP address

import java.net.InetAddress;
import java.net.UnknownHostException;
 
public class MyIpAddress {
 
    public static void main(String a[]){
     
        try {
            InetAddress ipAddr = InetAddress.getLocalHost();
            System.out.println(ipAddr.getHostAddress());
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        }
    }
}

import java.net.InetAddress;
import java.net.UnknownHostException;
 
public class MyIpByHost {
 
    public static void main(String a[]){
     
        try {
            InetAddress host = InetAddress.getByName("www.java2novice.com");
            System.out.println(host.getHostAddress());
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        }
    }
}

import java.net.InetAddress;
import java.net.UnknownHostException;
 
public class MyHostName {
 
    public static void main(String a[]){
     
        try {
            InetAddress host = InetAddress.getByName("72.167.232.155");
            System.out.println(host.getHostName());
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        }
    }
}


*/

///// ---- I/O Operations ---- /////

/*
- File 
import java.io.File;
 
public class MyFileOperations {
 
    public static void main(String[] a){
         
        try{
            File file = new File("fileName");
            //Tests whether the application can read the file 
            System.out.println(file.canRead());
            //Tests whether the application can modify the file
            System.out.println(file.canWrite());
            //Tests whether the application can modify the file 
            System.out.println(file.createNewFile());
            //Deletes the file or directory
            System.out.println(file.delete());
            //Tests whether the file or directory exists.
            System.out.println(file.exists());
            //Returns the absolute pathname string.
            System.out.println(file.getAbsolutePath());
            //Tests whether the file is a directory or not.
            System.out.println(file.isDirectory());
            //Tests whether the file is a hidden file or not.
            System.out.println(file.isHidden());
            //Returns an array of strings naming the 
            //files and directories in the directory.
            System.out.println(file.list());
        } catch(Exception ex){
             
        }
    }
}

- dir
import java.io.File;
 
public class FileListFromFolder {
     
    public static void main(String a[]){
        File file = new File("C:/MyFolder/");
        String[] fileList = file.list();
        for(String name:fileList){
            System.out.println(name);
        }
    }
}

import java.io.File;
 
public class FilesListFromFolder {
     
    public static void main(String a[]){
        File file = new File("C:/MyFolder/");
        File[] files = file.listFiles();
        for(File f: files){
            System.out.println(f.getName());
        }
    }
}

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
 
- Read a file
public class ReadLinesFromFile {
 
    public static void main(String a[]){
        BufferedReader br = null;
        String strLine = "";
        try {
            br = new BufferedReader( new FileReader("fileName"));
            while( (strLine = br.readLine()) != null){
                System.out.println(strLine);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Unable to find the file: fileName");
        } catch (IOException e) {
            System.err.println("Unable to read the file: fileName");
        }
    }
}

- Read from console

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
 
public class ReadFromConsole {
 
    public static void main(String a[]){
        BufferedReader br = null;
        Reader r = new InputStreamReader(System.in);
        br = new BufferedReader(r);
        String str = null;
        try {
            do{
                System.out.println("Enter Input, exit to quit.");
                str = br.readLine();
                System.out.println(str);
            } while (!str.equalsIgnoreCase("exit"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try{
                if(br != null) br.close();
            }catch(Exception ex){}
        }
    }
}

- Writing to a file

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
 
public class MyTempFileWrite {
 
    public static void main(String a[]){
 
        File tempFile = null;
        BufferedWriter writer = null;
        try {
            tempFile = File.createTempFile("MyTempFile", ".tmp");
            writer = new BufferedWriter(new FileWriter(tempFile));
            writer.write("Writing data into temp file!!!");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try{
                if(writer != null) writer.close();
            }catch(Exception ex){}
        }
        System.out.println("Stored data in temporary file.");
    }
}



- File URI reference

import java.io.File;
 
public class MyFileUrl {
     
    public static void main(String a[]){
        File f = new File("C:/TestForm.txt");
        System.out.println(f.toURI());
    }
}

- Properties file

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
 
public class MyPropertyFileStore {
 
    public static void main(String a[]) throws IOException{
         
        OutputStream os = null;
        Properties prop = new Properties();
        prop.setProperty("name", "java2novice");
        prop.setProperty("domain", "www.java2novice.com");
        prop.setProperty("email", "java2novice@gmail.com");
        try {
            os = new FileOutputStream("MyProp.properties");
            prop.store(os, "Dynamic Property File");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
         
    }
}

- Properties file to xml

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
 
public class MyFileXmlStore {
     
    public static void main(String a[]) throws IOException{
         
        OutputStream os = null;
        Properties prop = new Properties();
        prop.setProperty("name", "java2novice");
        prop.setProperty("domain", "www.java2novice.com");
        prop.setProperty("email", "java2novice@gmail.com");
        try {
            os = new FileOutputStream("MyProp.xml");
            prop.storeToXML(os, "Dynamic Property File");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
         
    }
}


- Writing/Reading objects

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
 
public class MyObjectFileStore {
 
    public void storeObject(Employee emp){
         
        OutputStream ops = null;
        ObjectOutputStream objOps = null;
        try {
            ops = new FileOutputStream("MyEmpFile.txt");
            objOps = new ObjectOutputStream(ops);
            objOps.writeObject(emp);
            objOps.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try{
                if(objOps != null) objOps.close();
            } catch (Exception ex){
                 
            }
        }
         
    }
     
    public void displayObjects(){
         
        InputStream fileIs = null;
        ObjectInputStream objIs = null;
        try {
            fileIs = new FileInputStream("MyEmpFile.txt");
            objIs = new ObjectInputStream(fileIs);
            Employee emp = (Employee) objIs.readObject();
            System.out.println(emp);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if(objIs != null) objIs.close();
            } catch (Exception ex){
                 
            }
        }
         
    }
     
    public static void main(String a[]){
        MyObjectFileStore mof = new MyObjectFileStore();
        Employee e1 = new Employee("Tony",1,"1000");
        mof.storeObject(e1);
        mof.displayObjects();
    }
}
 
class Employee implements Serializable{
     
    private String name;
    private int id;
    private String salary;
     
    public Employee(String name, int id, String salary){
        this.name = name;
        this.id = id;
        this.salary = salary;
    }
     
    public String toString(){
        return name +"=="+id+"=="+salary;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }
 
    public String getSalary() {
        return salary;
    }
 
    public void setSalary(String salary) {
        this.salary = salary;
    }
}

*/

////// ---- Lambda Expressions ---- //////

/*

(arguments) -> {body}
(int i, int j) ->  return i+j
(int i, int j) -> {
            int i = i+j;
            System.out.println(i);
}

public class FirstLambdaExp {
 
    public static void main(String a[]){
         
        // create a thread with old way of java programming.
        // CASE - 1
        new Thread(new Runnable(){
            public void run(){
                System.out.println("In run method, without lambda expression");
            }
        }).start();
         
        // now create the above code using lambda expression.
        // CASE - 2
        new Thread(() -> System.out.println("In run method, lambda expression")).start();
    }
 
}

public interface BinaryOperator<T> {
    T apply(T left, T right);
}

import java.util.function.BinaryOperator;
 
public class LambdaExpressEx {
 
    public static void main(String a[]){
         
        int i = 10;
        int j = 5;
         
        int sum = calculate(i, j, (i,j) -> {return (i+j);} );
        System.out.println("Sum:"+sum);
        int sub = calculate(i, j, (i,j) -> {return (i-j);} );
        System.out.println("Subtraction:"+sub);
    }
     
    public static int calculate(int i, int j, BinaryOperator<Integer> bo){
         
        return bo.apply(i, j);
    }
}

*/

/////// ---- Streams ---- /////
/*

Stream operations are divided into intermediate and terminal operations, and are combined to form stream pipelines. 
A stream pipeline consists of a source (such as a Collection, an array, a generator function, or an I/O channel); 
followed by zero or more intermediate operations such as Stream.filter or Stream.map; and a terminal operation such 
as Stream.forEach or Stream.reduce.

Intermediate operations return a new stream.
Terminal operations, such as Stream.forEach or IntStream.sum, may traverse the stream to produce a result or a 
side-effect.

public class StreamExample {
 
    public static void main(String a[]) {
 
        List<String> vechicles = Arrays.asList("bus", "car", "bicycle", "flight", "train");
 
        vechicles.stream().filter(str->str.length() > 3).map(String::toUpperCase).sorted().forEach(System.out::println);;
    }
}

- serial or parallel
public class ParallelStreamIntroEx {
 
    public static void main(String a[]) {
 
        List<String> vehicles = Arrays.asList("bus", "car", "bicycle", "flight", "train");
 
        vehicles.parallelStream().filter(str->str.length() > 3).map(String::toUpperCase).sorted().forEach(System.out::println);;
    }
}

- another way to create streams

import java.util.stream.Stream;
 
public class CreateStreamOfEx {
 
    public static void main(String[] args) {
 
        // create Stream using Stream.of(comma seperated values)
        Stream<Integer> intStream = Stream.of(1,2,3,4,5);
        intStream.forEach(System.out::println);
 
        System.out.println("--------------");
        // create stream using array of elements
        Stream<Integer> intStream1 = Stream.of(new Integer[]{1,2,3,4,5});
        intStream1.forEach(System.out::println);
    }
}
import java.util.UUID;
import java.util.stream.Stream;
 
public class StreamGenerateEx {
 
    public static void main(String a[]) {
 
        Stream<UUID> uuidStream = Stream.generate(UUID::randomUUID);
        uuidStream.limit(10).forEach(System.out::println);
    }
}

- map filter
We can use Stream.map() function for transforming the objects based on the input method.

import java.util.Arrays;
import java.util.List;
 
public class StreamMapEx {
 
    public static void main(String a[]) {
 
        List<String> vehicles = Arrays.asList("bus", "car", "bicycle", "flight", "train");
        vehicles.stream().map(String::toUpperCase).forEach(System.out::println);
    }
}

- In a very simple note, Stream.flatMap() is used to convert a Stream of Stream into a list of values.

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
 
public class StreamFlatMapEx {
 
    public static void main(String a[]) {
 
        try {
            long noOfWords = Files.lines(Paths.get("C:\testfile.txt"))
                                    .flatMap(l->Arrays.stream(l.split(" +")))
                                    .distinct()
                                    .count();
            System.out.println("noOfWords: "+noOfWords);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

- peek
The Stream.peek() method is mainly to support debugging, where you want to see the elements as they flow past 
a certain point in a pipeline.

import java.util.stream.Collectors;
import java.util.stream.Stream;
 
public class StreamPeekEx {
 
    public static void main(String a[]) {
 
        Stream.of("bus", "car", "bycle", "flight", "train")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());
    }
}

- distinct

The Stream.distinct() method returns a stream consisting of the distinct elements of this stream. 
It uses Object.equals() method.

import java.util.stream.Stream;
 
public class StreamDistinctEx {
 
    public static void main(String a[]) {
 
        Stream.of("bus", "car", "bycle", "bus", "car", "car", "bike")
                .distinct()
                .forEach(System.out::println);
    }
}

- sorted
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
 
import com.java2novice.lambda.Employee;
 
public class StreamSortedEx {
 
    public static void main(String a[]) {
 
        Stream.of("bus", "car", "bicycle", "flight", "train")
                .sorted()
                .forEach(System.out::println);
 
        System.out.println("-------------------------");
 
        List<Employee> empList = new ArrayList<>();
        empList.add(new Employee("Nataraja G", "Accounts", 8000));
        empList.add(new Employee("Nagesh Y", "Admin", 15000));
        empList.add(new Employee("Vasu V", "Security", 2500));
        empList.add(new Employee("Amar", "Entertainment", 12500));
 
        empList.stream()
                .sorted((emp1, emp2)->emp1.getSalary().compareTo(emp2.getSalary()))
                .forEach(System.out::println);
    }
}

-limit
The Stream.limit() method returns a Stream with elements of the source stream, 
truncated to be no longer than the specified size.

import java.util.UUID;
import java.util.stream.Stream;
 
public class StreamLimitEx {
 
    public static void main(String a[]) {
 
        Stream.of("bus", "car", "bicycle", "flight", "train").limit(3).forEach(System.out::println);
        System.out.println("--------------------------------");
        Stream.generate(UUID::randomUUID).limit(10).forEach(System.out::println);
    }
}

- toArray

import java.util.stream.Stream;
 
public class StreamToArrayEx {
 
public static void main(String a[]) {
 
        String[] strArr = (String[]) Stream.of("bus", "car", "bicycle", "flight", "train").toArray();
        String[] strArr1 = Stream.of("bus", "car", "bicycle", "flight", "train").toArray(String[]::new);
    }
}

- Reduce
The Stream.reduce() method is a reduction operation.

import java.util.stream.Stream;
 
a) Accumulator
public class StreamReduceAccEx {
 
    public static void main(String[] args) {
 
        Stream.of(10,20,22,12,14).reduce((x,y)->x+y).ifPresent(System.out::println);
 
        Stream.of(10,20,22,12,14).reduce(Integer::sum).ifPresent(System.out::println);
 
        Stream.of("java", "c", "c#", "python").reduce((x,y)->x+" | "+y).ifPresent(System.out::println);
    }
}
b) Accumulator and Identity
import java.util.stream.Stream;
 
public class StreamReduceAccEx {
 
    public static void main(String[] args) {
 
        Integer arrSum = Stream.of(10,20,22,12,14).reduce(1000, Integer::sum);
        System.out.println(arrSum);
 
        arrSum = Stream.of(10,20,22,12,14).reduce(1000, (x,y)->x+y);
        System.out.println(arrSum);
 
        String result = Stream.of("java", "c", "c#", "python").reduce("Languages:", (x,y)->x+" | "+y);
        System.out.println(result);
    }
}

c0 Accumulator, Identity and combiner
import java.util.stream.Stream;
 
public class StreamReduceAccEx {
 
    public static void main(String[] args) {
 
        Integer arrSum = Stream.of(10,20,22,12,14).parallel().reduce(1000, (x,y)->x+y, (p,q)->{
            System.out.println("combiner called");
            return p+q;
        });
        System.out.println(arrSum);
    }
}

- collect
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
 
import com.java2novice.lambda.Employee;
 
public class StreamCollectEx {
 
    public static void main(String a[]) {
 
        List<Employee> empList = new ArrayList<>();
        empList.add(new Employee("Nataraja G", "Accounts", 8000));
        empList.add(new Employee("Nagesh Y", "Admin", 15000));
        empList.add(new Employee("Vasu V", "Security", 2500));
        empList.add(new Employee("Amar", "Entertainment", 12500));
 
        // find employees whose salaries are above 10000
        List<Employee> filteredList = empList.stream()
                                            .filter(emp->emp.getSalary() > 10000)
                                            .collect(Collectors.toList());
 
        filteredList.forEach(System.out::println);
    }
}

- concat
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
 
import com.java2novice.lambda.Employee;
 
public class StreamConcatEx {
 
    public static void main(String a[]) {
 
        List<Employee> accEmpList = new ArrayList<>();
        accEmpList.add(new Employee("Nataraja G", "Accounts", 8000));
        accEmpList.add(new Employee("Nagesh Y", "Accounts", 15000));
        List<Employee> adminEmpList = new ArrayList<>();
        adminEmpList.add(new Employee("Vasu V", "Admin", 2500));
        adminEmpList.add(new Employee("Amar", "Admin", 12500));
 
        Stream.concat(accEmpList.stream(), adminEmpList.stream()).forEach(System.out::println);
    }
}

- match

import java.util.ArrayList;
import java.util.List;
 
import com.java2novice.lambda.Employee;
 
public class StreamMatchEx {
 
    public static void main(String a[]) {
 
        List<Employee> empList = new ArrayList<>();
        empList.add(new Employee("Nataraja G", "Accounts", 8000));
        empList.add(new Employee("Nagesh Y", "Admin", 15000));
        empList.add(new Employee("Vasu V", "Security", 2500));
        empList.add(new Employee("Amar", "Admin", 12500));
 
        boolean result = empList.stream().anyMatch(emp->emp.getAccount().matches("Admin"));
        System.out.println(result);
 
        result = empList.stream().allMatch(emp->emp.getAccount().matches("Admin"));
        System.out.println(result);
 
        result = empList.stream().noneMatch(emp->emp.getAccount().matches("Admin"));
        System.out.println(result);
    }
}

- find

import java.util.ArrayList;
import java.util.List;
 
import com.java2novice.lambda.Employee;
 
public class StreamFindEx {
 
    public static void main(String a[]) {
 
        List<Employee> empList = new ArrayList<>();
        empList.add(new Employee("Nataraja G", "Accounts", 8000));
        empList.add(new Employee("Nagesh Y", "Admin", 15000));
        empList.add(new Employee("Vasu V", "Security", 2500));
        empList.add(new Employee("Amar", "Admin", 12500));
 
        empList.stream().filter(emp->emp.getAccount().matches("Admin"))
                        .findFirst()
                        .ifPresent(System.out::println);
 
        empList.stream().filter(emp->emp.getAccount().matches("Admin"))
                        .findAny()
                        .ifPresent(System.out::println);
    }
}

- primitives
import java.util.Arrays;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
 
public class PrimitiveStreamEx {
 
    public static void main(String a[]) {
 
        IntStream inStrm = IntStream.of(10, 20, 30, 40);
        System.out.println("----- IntStream -----");
        inStrm.forEach(System.out::println);
 
        double[] results = {98.12, 72.17, 75.20, 68.6};
        DoubleStream resultStream = Arrays.stream(results);
        System.out.println("----- DoubleStream -----");
        resultStream.forEach(System.out::println);
    }
}



*/

////// ---- JASON ---- //////
/*

Jackson is a JSON processor. It provides JSON parser/JSON generator as foundational building block; and adds a 
powerful Databinder (JSON<->POJO) and Tree Model as optional add-on blocks. This means that you can read and 
write JSON either as stream of tokens (Streaming API), as Plain Old Java Objects (POJOs, databind) or as 
Trees (Tree Model)

public class Employee {
 
    private int empId = 1016;
    private String name = "Nataraja Gootooru";
    private String designation = "Programmer";
    private String department = "Java2Novice";
    private int salary = 20000;
     
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("************************************");
        sb.append("\nempId: ").append(empId);
        sb.append("\nname: ").append(name);
        sb.append("\ndesignation: ").append(designation);
        sb.append("\ndepartment: ").append(department);
        sb.append("\nsalary: ").append(salary);
        sb.append("\n************************************");
        return sb.toString();
    }
     
    public int getEmpId() {
        return empId;
    }
    public void setEmpId(int empId) {
        this.empId = empId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDesignation() {
        return designation;
    }
    public void setDesignation(String designation) {
        this.designation = designation;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public int getSalary() {
        return salary;
    }
    public void setSalary(int salary) {
        this.salary = salary;
    }   
}

public class ObjectToJsonEx {
 
    public static void main(String[] a){
         
        Employee emp = new Employee();
        ObjectMapper mapperObj = new ObjectMapper();
         
        try {
            // get Employee object as a json string
            String jsonStr = mapperObj.writeValueAsString(emp);
            System.out.println(jsonStr);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}


*/

/*
//Array to list
        String[] strArr = {"JAVA", "C++", "PERL", "STRUTS", "PLAY"};
        List<String> strList = Arrays.asList(strArr);

	//Binary search
        char[] chrArr = {'a','c','d','y','e','q','b'};
        int index = Arrays.binarySearch(chrArr, 0, chrArr.length-1, 'q');

	//Copy
        int[] myArr = {2,4,2,4,5,6,3};
        int[] newArr = Arrays.copyOf(myArr, 10);

	//Copy range
	  int[] myArr = {2,4,2,4,5,6,3};
	  int[] newArr = Arrays.copyOfRange(myArr, 1, 4);

	// Compare arrays
        String[] strArr = {"JAVA", "C++", "PERL", "STRUTS", "PLAY"};
        String[] strArrCopy = {"JAVA", "C++", "PERL", "STRUTS", "PLAY"};
	  Arrays.deepEquals(strArr, strArrCopy)

	//Array filling
        String[] myArr = new String[10];
        Arrays.fill(myArr, "Assigned");

	//Sorting
        int[] myArr = {3,2,56,12,98,23,56};
        Arrays.sort(myArr);

	//Sorting with function
	 Arrays.sort(strArr,new Comparator<String>(){ 
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }});

	//String
	  String bcd = new String("this is also string object"); 
        char[] c = {'a','b','c','d'};
        String cdf = new String(c);

	//Copy value of
	  char ch[] = {'M','y',' ','J','a','v','a',' ','e','x','a','m','p','l','e'};
	  String chStr = String.copyValueOf(ch);
	  String subStr = String.copyValueOf(ch,3,4);

	//Concatenate
	  String b = "jump ";
        String c = "No jump";
	  d = b.concat(c);

	//Compare
        String x = "JUNK";
        String y = "junk";
	  x.equalsIgnoreCase(y)

	//To bytes
        String str = "core java api";
        byte[] b = str.getBytes();

	//IndexOf
	  String str = "Use this string for testing this";
	  str.indexOf(’s’,4)

	//LastIndex
	 str.lastIndexOf('s',24)

	//Start with
	  str.startsWith("This")

	//End with
	  str.endsWith("example”)

	//split
	  String str = "This program splits a string based on space";
        String[] tokens = str.split(" ");
        for(String s:tokens){
            System.out.println(s);
        }

	//Chars from string
        String str = "Copy chars from this string";
        char[] ch = new char[5];
	  str.getChars(5, 10, ch, 0);

	//Replace chars
	  String str = "This is an example string";
	  str.replace('s', 'o')
	 str.replaceAll("is", "no")

	//Upper, Lower
	  String str = "Change My Case";
	  str.toUpperCase();
	  str.toLowerCase()

	//Trims
	  String str = "  Junk   ";
	  str.trim()

	//Format
        String str = "This is %s format example";
        System.out.println(String.format(str, "string"));
        String str1 = "We are displaying no %d";
        System.out.println(String.format(str1, 10));

	//Line counts
	 text.split("[\n|\r]").length;

	//Replace
        String text = "<B>I don't want this to be bold<\\B>";
        text = text.replaceAll("\\<.*?\\>", "");

	//Matches
	  String[] str = {"www.java2novice.com", "http://java2novice.com"};
	  for(int i=0;i < str.length;i++){
            if(str[i].matches("^www\\.(.+)")){
                System.out.println(str[i]+" Starts with 'www'");
            } else {
                System.out.println(str[i]+" is not starts with 'www'");
            }
         }

	//toCharArray
	  String myStr = "Hello Java";
        char[] arr = myStr.toCharArray();

*/

public class JavaReview {

    /**
     * @param args the command line arguments
     */ 
    public static void main(String[] args) {

    }
    
}
