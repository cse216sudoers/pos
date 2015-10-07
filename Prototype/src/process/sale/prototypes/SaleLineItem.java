/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.sale.prototypes;

import java.util.ArrayList;

/**
 *
 * @author Jeff
 */
public class SaleLineItem {
    private int quantity;
    private ProductDescription product;
    private Coupon coupon;
    
    public SaleLineItem(ProductDescription product){
        quantity = 1;
        this.product = product;
        coupon = null;
    }
    public ProductDescription getProduct(){
        return product;
    }
    public int getQuantity(){
        return quantity;
    }
    public void increaseQuantity(){
        quantity++;
    }
    public void decreaseQuantity(){
        quantity--;
    }
    public void setCoupon(Coupon coupon){
        this.coupon = coupon;
    }
    public Coupon getCoupon(){
        return coupon;
    }
    
    @Override
    public String toString(){
        String output = product.toString() + "\n";
        if(quantity >1)
            output += String.format("%2s %-15.15s\t%7.2f\n", "", "X" + quantity, quantity *product.getPrice());
        if(coupon != null)
            output += coupon.toString() + "\n";
        return output;
    }
}
