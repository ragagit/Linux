/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.javatestbucket.MyEvenOdd;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author raga
 */
public class MyEvenOddTest {
    
    public List<String> list ;
    
    public MyEvenOddTest() {
    }
    
    @BeforeClass //Executed Test class based.
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before //Execuated before the test
    public void setUp() {
        list = new ArrayList<>();
    }
    
    @After //Executed after the test
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    //@Test(expected=IOException.class) 
    //@Test(timeout=100)
    
    public void testEvenOddNumber(){
        MyEvenOdd meo = new MyEvenOdd();
        assertEquals("10 is a even number", true, meo.isEvenNumber(10));
    }
    
    @Test
    public void testTrue(){
        assertTrue( list.isEmpty());
        //assertFalse
        //assertNotNull
        //assertNull
        //assertSame
        //AssertNoSame
    }
    
    @Test
    public void myTestMethod(){
        /**
         * we are demonstrating the usage of assertArrayEquals()
         * method here, so we are preparing input data here itself.
         * In real scenario, we will consider the methods returned 
         * value which suppose to be test, as a input. 
         */
        //assume that the below array represents expected result
        String[] expectedOutput = {"apple", "mango", "grape"};
        //assuem that the below array is returned from the method 
        //to be tested.
        String[] methodOutput = {"apple", "mango", "grape"};
        assertArrayEquals(expectedOutput, methodOutput);
    }
    
}
