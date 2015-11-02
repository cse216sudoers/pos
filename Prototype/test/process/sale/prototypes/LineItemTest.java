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
 * @author peterschaedler
 */
public class LineItemTest {
    
    public LineItemTest() {
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
     * Test of getProduct method, of class SaleLineItem.
     */
    @Test
    public void testGetProduct() {
        System.out.println("getProduct");
        ProductDescription expResult = new ProductDescription("Item", 12.95F, 1000, 10, false, 0);
        LineItem instance = new LineItem(expResult);
        ProductDescription result = instance.getProduct();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of getQuantity method, of class SaleLineItem.
     */
    @Test
    public void testGetQuantity() {
        System.out.println("getQuantity");
        LineItem instance = new LineItem(new ProductDescription("Item", 12.95F, 1000, 10, false, 0));
        int expResult = 1;
        int result = instance.getQuantity();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of increaseQuantity method, of class SaleLineItem.
     */
    @Test
    public void testIncreaseQuantity() {
        System.out.println("increaseQuantity");
        LineItem instance = new LineItem(new ProductDescription("Item", 12.95F, 1000,10, false, 0));
        instance.increaseQuantity();
        int expResult = 2;
        int result = instance.getQuantity();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of decreaseQuantity method, of class SaleLineItem.
     */
    @Test
    public void testDecreaseQuantity() {
        System.out.println("decreaseQuantity");
        LineItem instance = new LineItem(new ProductDescription("Item", 12.95F, 1000, 10, false, 0));
        instance.decreaseQuantity();
        int expResult = 0;
        int result = instance.getQuantity();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }
}
