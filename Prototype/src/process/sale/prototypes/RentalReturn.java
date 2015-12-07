/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package process.sale.prototypes;

import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *Return for a SINGLE rental
 * @author Pikachu
 */
public class RentalReturn extends Transaction{
    Rental rental;
    
    /**
     *
     * @param rental
     */
    public RentalReturn(Rental rental){
        this.rental = rental;
        total = 0;
        subTotal = 0;
        id = RentalManager.getInstance().getNextId();
        payments = new ArrayList<>();
        lines = new ArrayList<>();
    }
    
    /**
     *
     * @return
     */
    public Rental getRental(){
       return rental;
    }
    
    /**
     *
     * @param code
     * @param daysRented
     * @return
     */
    public LineItem getLineItemByCodeAndDaysRented(int code, int daysRented){
        for(int i = 0; i< lines.size(); i++){
            if(lines.get(i).getProduct().getCode() == code)
                return lines.get(i);
        }
        return null;
    }
    
    /**
     *
     * @param product
     * @return
     */
    public void addItem(ProductDescription product, int daysRented, boolean affectQuantity){
        LineItem lineItem = getLineItemByCodeAndDaysRented(product.getCode(), daysRented);
        if(lineItem!=null){
            lineItem.increaseQuantity();
            subTotal += ((RentalLineItem)lineItem).getRentalPrice();
            product.decreaseQuantity();
        } 
        else{
            lines.add(new RentalLineItem(product, daysRented));
            subTotal += ((RentalReturnLineItem)lineItem).getLateFee();
        }
    }
    
    /**
     *
     * @param product
     */
    public boolean removeItem(ProductDescription product, int days, boolean affectQuantity){
        LineItem lineItem = getLineItemByCodeAndDaysRented(product.getCode(), days);
        if(lineItem != null){
            if(lineItem.getQuantity() == 1){
                lines.remove(lineItem);
            }
            else{
                lineItem.decreaseQuantity();
            }
            subTotal -= ((RentalReturnLineItem)lineItem).getLateFee();
        }else{//item not in Sale
            JOptionPane.showMessageDialog (null, "Item not found", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            System.out.println("item not found");
            return false;
        }
        return true;
    }
    
    /**
     *
     * @param coupon
     */
    public void addCoupon(Coupon coupon){
        boolean found = false;
        for(int i = 0; i < lines.size(); i++){
            if(lines.get(i).getProduct().getCode() == coupon.getProductCode()){
                lines.get(i).setCoupon(coupon);
                subTotal-=coupon.getAmount();
                found = true;
                break;
            }
        }
        if(!found){
            System.out.println("Invalid coupon. Item not scanned: " + coupon.getCode());
        }
    }
    
    @Override
    public String toString(){
        String output = "******Rental******* \nRental ID: " + id + "\n";
        for(int i = 0; i < lines.size(); i++){
            output += lines.get(i).toString();
        }
        return output;
    }
}