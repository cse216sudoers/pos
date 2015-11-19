/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package process.sale.prototypes;

import java.util.ArrayList;

/**
 * Rental Domain object 
 * @author Pikachu
 */
public class Rental extends Transaction{
    ArrayList<RentalReturn> returns;
    
    /**
     *Initialize rental
     */
    public Rental(){
        total = 0;
        id = RentalManager.getInstance().getNextId();
        payments = new ArrayList<>();
        lines = new ArrayList<>();
        returns = new ArrayList<>();
    }
    
    /**
     * Add a RentalReturn
     * @param ret 
     */
    public void addRentalreturn(RentalReturn ret){
        returns.add(ret);
    }
    
    /**
     * Add item to rental
     * @param product product rented
     * @param daysRented days rented for
     */
    public void addItem(ProductDescription product, int daysRented){
        boolean found = false;
        for(int i = 0; i < lines.size(); i++){
            if(lines.get(i).getProduct().getCode() == product.getCode() && ((RentalLineItem)lines.get(i)).getDaysRented() == daysRented){
                lines.get(i).increaseQuantity();
                found = true;
                subTotal += ((RentalLineItem)lines.get(i)).getRentalPrice();
                break;
            }
        }
        if(!found){
            lines.add(new RentalLineItem(product, daysRented));
            subTotal += ((RentalLineItem)lines.get(lines.size()-1)).getRentalPrice();
        }
        
    }
    
    /**
     * add a coupon to the rental
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
    
    /**
     * Print totals
     */
    @Override
    public void printTotals() {
        // Calculate tax and total
        float tax = TaxCalculator.getTax(subTotal);
        total = subTotal + tax;
        
        // Set up ability to format print statements right so everything aligns
        int digits = ((Float) total).toString().length();
        String format = "%" + digits + ".2f";
        
        System.out.println("\n" + toString());
        System.out.printf("Subtotal: $" + format + "\n", subTotal);
        System.out.printf("Tax:      $" + format + "\n", tax);
        System.out.printf("Total:    $" + format + "\n", total);
    }
    
    @Override
    public String toString(){
        String output = "******Rental******* \nRental ID: " + id + "\n";
        for(int i = 0; i < lines.size(); i++){
            output += lines.get(i).toString();
        }
        return output;
    }

    /**
     * Remove item from rental
     * @param product item to remove
     */
    @Override
    public void removeItem(ProductDescription product){
        boolean found = false;
        for(int i = 0; i < lines.size(); i++){
            if(lines.get(i).getProduct().getCode() == product.getCode()){
                if(lines.get(i).getQuantity() == 1){
                    lines.remove(i);
                }
                else{
                    lines.get(i).decreaseQuantity();
                }
                total-=product.getRentalPrice();
                found = true;
                break;
            }
        }
        if(!found){//item not in Sale
            System.out.println("item not found");
        }
    }
}