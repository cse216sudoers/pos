/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.sale.prototypes;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jeff
 */
public class CreditPaymentTest {
    CreditPayment test;
    
    public CreditPaymentTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        test=new CreditPayment("123456789012", "123", (float) 12.35);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getCardNum method, of class CreditPayment.
     */
    @Test
    public void testGetCardNum() {
        System.out.println("getCardNum");
        CreditPayment instance = test;
        String expResult = "123456789012";
        String result = instance.getCardNum();
        assertEquals(expResult, result);
    }


    /**
     * Test of getSecurityCode method, of class CreditPayment.
     */
    @Test
    public void testGetSecurityCode() {
        System.out.println("getSecurityCode");
        CreditPayment instance = test;
        String expResult = "123";
        String result = instance.getSecurityCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class CreditPayment.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        CreditPayment instance = test;
        String expResult = "Credit     123456789012	$12.35  ";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
