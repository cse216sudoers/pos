/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package process.sale.prototypes;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *Return for a SINGLE rental
 * @author Pikachu
 */
public class RentalReturn extends Transaction{
    Rental rental;
    private float rentalTotal;
    
    public RentalReturn(Rental rental){
        this.rental = rental;
        total = 0;
        id = RentalManager.getInstance().getNextId();
        payments = new ArrayList<>();
        lines = new ArrayList<>();
    }
    
    public float getRentalTotal(){
        return rentalTotal;
    }
    
    public Rental getRental(){
            return rental;
    }
    
    public LineItem getLineItemByCode(int code, int daysRented){
        for(int i = 0; i< lines.size(); i++){
            if(lines.get(i).getProduct().getCode() == code)
                return lines.get(i);
        }
        return null;
    }
    
    public boolean addItem(ProductDescription product){        
        Scanner scan = new Scanner(System.in);
        int daysLate = scan.nextInt();
        LineItem item = new RentalReturnLineItem(product, daysLate);
        lines.add(item);
        total += ((RentalReturnLineItem)item).getLateFee();
        return true;
    }
    
    @Override
    public void removeItem(ProductDescription product){
        boolean found = false;
        for(int i = 0; i < lines.size(); i++){
            if(lines.get(i).getProduct().getCode() == product.getCode()){
                LineItem item = null;
                
                if(lines.get(i).getQuantity() == 1){
                    item = lines.remove(i);
                }
                else{
                    lines.get(i).decreaseQuantity();
                    item = lines.get(i);
                }
                
                total -= ((RentalReturnLineItem)item).getLateFee();
                found = true;
                break;
            }
        }
        if(!found){//item not in Sale
            System.out.println("item not found");
        }
    }
    
    public void addCoupon(Coupon coupon){
        boolean found = false;
        for(int i = 0; i < lines.size(); i++){
            if(lines.get(i).getProduct().getCode() == coupon.getProductCode()){
                lines.get(i).setCoupon(coupon);
                total-=coupon.getAmount();
                found = true;
                break;
            }
        }
        if(!found){
            System.out.println("Invalid coupon. Item not scanned: " + coupon.getCode());
        }
    }
    
    @Override
    public void printTotals() {
        // Calculate tax and total
        float tax = TaxCalculator.getTax(getTotal());
        rentalTotal = getTotal() + tax;
        
        // Set up ability to format print statements right so everything aligns
        int digits = ((Float) rentalTotal).toString().length();
        String format = "%" + digits + ".2f";
        
        System.out.println("\n" + toString());
        System.out.printf("Subtotal: $" + format + "\n", getTotal());
        System.out.printf("Tax:      $" + format + "\n", tax);
        System.out.printf("Total:    $" + format + "\n", rentalTotal);
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