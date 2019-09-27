/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 In IntelliJ go to Project Structure -> Modules -> Dependencies -> + then locate the library in Applications/IntelliJ/lib
 Or right click on the class Create test, in the dlg click fix to download dependencies.

 To makr it as a test folder go to File -> Project Structure -> Modules and mark the test directory as test

 For NetBeans just go right click and select Test File it will donload the dependencies but you need to vreate the project
 as maven then add JUnit dependency.
 Then you need to right click on the project and choose Other then select Unit Tests and select JUnit
 */
import com.mycompany.javajunit.MyEvenOdd;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

/**
 *
 * @author raga
 */
@RunWith(MockitoJUnitRunner.class)
public class MyEvenOddTest {

    public List<String> list;

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
        //MockitoAnnotations.initMocks(this);
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

    public void testEvenOddNumber() {
        MyEvenOdd meo = new MyEvenOdd();
        assertEquals("10 is a even number", true, meo.isEvenNumber(10));
    }

    @Test
    public void testTrue() {
        assertTrue(list.isEmpty());
        //assertFalse
        //assertNotNull
        //assertNull
        //assertSame
        //AssertNoSame
    }

    @Test
    public void myTestMethod() {
        /**
         * we are demonstrating the usage of assertArrayEquals() method here, so
         * we are preparing input data here itself. In real scenario, we will
         * consider the methods returned value which suppose to be test, as a
         * input.
         */
        //assume that the below array represents expected result
        String[] expectedOutput = {"apple", "mango", "grape"};
        //assuem that the below array is returned from the method 
        //to be tested.
        String[] methodOutput = {"apple", "mango", "grape"};
        assertArrayEquals(expectedOutput, methodOutput);
    }
    @Mock
    List<String> mockedList;

    @Test
    public void whenUseMockAnnotation_thenMockIsInjected() {
        mockedList.add("one");
        Mockito.verify(mockedList).add("one");
        assertEquals(0, mockedList.size());

        Mockito.when(mockedList.size()).thenReturn(100);
        assertEquals(100, mockedList.size());
    }

    @Test
    public void whenNotUseSpyAnnotation_thenCorrect() {
        List<String> spyList = Mockito.spy(new ArrayList<String>());

        spyList.add("one");
        spyList.add("two");

        Mockito.verify(spyList).add("one");
        Mockito.verify(spyList).add("two");

        assertEquals(2, spyList.size());

        Mockito.doReturn(100).when(spyList).size();
        assertEquals(100, spyList.size());
    }

    @Test
    public void whenNotUseCaptorAnnotation_thenCorrect() {
        List mockList = Mockito.mock(List.class);
        ArgumentCaptor<String> arg = ArgumentCaptor.forClass(String.class);

        mockList.add("one");
        Mockito.verify(mockList).add(arg.capture());

        assertEquals("one", arg.getValue());
    }

}
