/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package process.sale.prototypes;

import java.util.ArrayList;

/**
 *
 * @author Pikachu
 */
public class Rental {
    private float total;
    private float rentalTotal;
    private ArrayList<LineItem> lines;
    private ArrayList<Payment> payments; //for when we need to do returns
    private int id;
    
    public Rental(){
        total = 0;
        id = RentalManager.getInstance().getNextId();
        payments = new ArrayList<Payment>();
        lines = new ArrayList<LineItem>();
    }
    
    public float getTotal(){
        return total;
    }
    public float getRentalTotal(){
        return rentalTotal;
    }
    public void addPayment(Payment payment){
        payments.add(payment);
    }
    public ArrayList<LineItem> getLines(){
        return lines;
    }
    
    public void addItem(ProductDescription product){
        boolean found = false;
        for(int i = 0; i < lines.size(); i++){
            if(lines.get(i).getProduct().getCode() == product.getCode()){
                lines.get(i).increaseQuantity();
                found = true;
                break;
            }
        }
        if(!found){
            lines.add(new LineItem(product));
        }
        total += product.getPrice();
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
                total-=product.getPrice();
                found = true;
                break;
            }
        }
        if(!found){//item not in Sale
            System.out.println("item not found");
        }
    }
    
    public ArrayList<Payment> getPayments(){
        return payments;
    }
    
    public int getId(){
        return id;
    }
    
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
