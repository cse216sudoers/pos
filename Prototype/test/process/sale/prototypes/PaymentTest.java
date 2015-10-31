/*
 * To change this template, choose Tools | Templates
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
 * @author Pikachu
 */
public class PaymentTest {
    Payment instance;
    public PaymentTest() {
    
    }
    
    @BeforeClass
    public static void setUpClass() {
        //none needed;
    }
    
    @AfterClass
    public static void tearDownClass() {
        //none needed;
    }
    
    @Before
    public void setUp() {
        instance = new CashPayment(26, 25.02f);
    }
    
    @After
    public void tearDown() {
        //none needed
    }

    /**
     * Test of getAmount method, of class Payment.
     */
    @Test
    public void testGetAmount() {
        System.out.println("getAmount");
        float expResult = 25.02f;
        float result = instance.getAmount();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setAmount method, of class Payment.
     */
    @Test
    public void testSetAmount() {
        System.out.println("setAmount");
        float amount = 0.0f;
        instance.setAmount(amount);
        assertEquals(amount, instance.getAmount(), 0.0);
    }
}