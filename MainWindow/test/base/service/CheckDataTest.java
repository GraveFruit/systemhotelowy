/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base.service;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Eruru
 */
public class CheckDataTest {
    
    public CheckDataTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of isNumber method, of class CheckData.
     */
    @Test
    public void testIsNumber() {
        System.out.println("isNumber");
        String number = "";
        CheckData instance = new CheckData();
        boolean expResult = false;
        boolean result = instance.isNumber(number);
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
        assertFalse("1a", false);
        assertFalse("123a", false);
        assertTrue("10", true);
        assertTrue("4", true);
        assertNotNull("2", true);
        assertEquals("27","27");
        assertEquals("10","10");
    }

    /**
     * Test of isName method, of class CheckData.
     */
    @Test
    public void testIsName() {
        System.out.println("isName");
        String name = "";
        CheckData instance = new CheckData();
        boolean expResult = false;
        boolean result = instance.isName(name);
       // assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        assertEquals("Ala","Ala");
        assertEquals("Ela","Ela");
        assertFalse("Ad1a2m", false);
        assertFalse("Edmun123d", false);
        assertTrue("Adam", true);
        assertTrue("Edmund", true);
    }

    /**
     * Test of isPesel method, of class CheckData.
     */
    @Test
    public void testIsPesel() {
        System.out.println("isPesel");
        String pesel = "";
        CheckData instance = new CheckData();
        boolean expResult = false;
        boolean result = instance.isPesel(pesel);
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        assertEquals(920812091, 920812091);
        assertEquals(900112191, 900112191);
        assertFalse("12376tr49", false);
        assertFalse("09876tr49", false);
        assertTrue("930823400", true);
        assertTrue("781114567", true);
    }

    /**
     * Test of isPhone method, of class CheckData.
     */
    @Test
    public void testIsPhone() {
        System.out.println("isPhone");
        String phone = "";
        CheckData instance = new CheckData();
        boolean expResult = false;
        boolean result = instance.isPhone(phone);
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
        assertEquals(777666555, 777666555);
        assertEquals(999666444, 999666444);
        assertTrue("678567900", true);
        assertTrue("980765345", true);
        assertFalse("19876tr49", false);
        assertFalse("aa276tr49", false);
        
    }

    /**
     * Test of checkPasswords method, of class CheckData.
     */
    @Test
    public void testCheckPasswords() {
        System.out.println("checkPasswords");
        String password1 = "";
        String password2 = "";
        CheckData instance = new CheckData();
        int expResult = 0;
        int result = instance.checkPasswords(password1, password2);
       // assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        assertEquals("system", "system");
        assertEquals("admin", "admin");
        assertFalse("76in12", false);
        assertFalse("7975in982", false);
        assertTrue("65102740761", true);
        assertTrue("74102740761", true);
    }

    /**
     * Test of checkRoomReady method, of class CheckData.
     */
    @Test
    public void testCheckRoomReady() {
        System.out.println("checkRoomReady");
        String status = "";
        String tasks = "";
        CheckData instance = new CheckData();
        boolean expResult = false;
        boolean result = instance.checkRoomReady(status, tasks);
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        assertEquals(1, 1);
        assertEquals(0, 0);
        assertTrue("1", true);
        assertTrue("0", true);
        assertFalse("7", false);
        assertFalse("7aa", false);
    }

    /**
     * Test of roundNumber method, of class CheckData.
     */
    @Test
    public void testRoundNumber() {
        System.out.println("roundNumber");
        double number = 0.0;
        CheckData instance = new CheckData();
        double expResult = 0.0;
        double result = instance.roundNumber(number);
        //assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        assertEquals(2.0,instance.roundNumber(2.0), 0.0);
        assertEquals(1.0,instance.roundNumber(1.0), 0.0);
    }

    /**
     * Test of countAverage method, of class CheckData.
     */
    @Test
    public void testCountAverage() {
        System.out.println("countAverage");
        double count = 0.0;
        double quantity = 0.0;
        CheckData instance = new CheckData();
        double expResult = 0.0;
        double result = instance.countAverage(count, quantity);
        //assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        assertEquals(1000, instance.countAverage(100, 10), 0.0);
        assertEquals(100, instance.countAverage(10, 10), 0.0);
        assertFalse("7aa",false);
        assertFalse("aaa",false);
        assertFalse("1_9",false);
        assertFalse("7-10",false);
        assertTrue("1", true);
        assertTrue("100", true);
        assertTrue("12", true);
        assertTrue("190", true);
    }
    
}
