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
     * Test of getProduct method, of class LineItem.
     */
    @Test
    public void testGetProduct() {
        System.out.println("getProduct");
        LineItem instance = null;
        ProductDescription expResult = null;
        ProductDescription result = instance.getProduct();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPriceWithCoupons method, of class LineItem.
     */
    @Test
    public void testGetPriceWithCoupons() {
        System.out.println("getPriceWithCoupons");
        LineItem instance = null;
        float expResult = 0.0F;
        float result = instance.getPriceWithCoupons();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getQuantity method, of class LineItem.
     */
    @Test
    public void testGetQuantity() {
        System.out.println("getQuantity");
        LineItem instance = null;
        int expResult = 0;
        int result = instance.getQuantity();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of increaseQuantity method, of class LineItem.
     */
    @Test
    public void testIncreaseQuantity() {
        System.out.println("increaseQuantity");
        LineItem instance = null;
        instance.increaseQuantity();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of decreaseQuantity method, of class LineItem.
     */
    @Test
    public void testDecreaseQuantity() {
        System.out.println("decreaseQuantity");
        LineItem instance = null;
        instance.decreaseQuantity();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCoupon method, of class LineItem.
     */
    @Test
    public void testSetCoupon() {
        System.out.println("setCoupon");
        Coupon coupon = null;
        LineItem instance = null;
        instance.setCoupon(coupon);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCoupon method, of class LineItem.
     */
    @Test
    public void testGetCoupon() {
        System.out.println("getCoupon");
        LineItem instance = null;
        Coupon expResult = null;
        Coupon result = instance.getCoupon();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class LineItem.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        LineItem instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
