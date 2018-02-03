/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myannotdefaultvalues;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;
 
 
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnot{
     
    String key() default "site";
    String value() default "java2novice.com";
}
 
public class MyAnnotDefaultValues {
 
    @MyAnnot()
    public void myAnnotationTestMethod(){
         
        try {
            Class<? extends MyAnnotDefaultValues> cls = this.getClass();
            Method mth = cls.getMethod("myAnnotationTestMethod");
            MyAnnot myAnno = mth.getAnnotation(MyAnnot.class);
            System.out.println("key: "+myAnno.key());
            System.out.println("value: "+myAnno.value());
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
     
    public static void main(String a[]){
         
        MyAnnotDefaultValues mat = new MyAnnotDefaultValues();
        mat.myAnnotationTestMethod();
    }
}