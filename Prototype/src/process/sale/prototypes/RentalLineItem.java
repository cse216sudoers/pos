/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package process.sale.prototypes;

/**
 *
 * @author Pikachu
 */
public class RentalLineItem extends LineItem{
    private int daysRented;
    
    public RentalLineItem(ProductDescription product, int daysRented){
        super(product);
        this.daysRented = daysRented;
    }
    
    public float getRentalPrice(){
        return product.getRentalPrice() * daysRented * quantity;
    }
    
    //daily late fee
    public float getLatePayment(){
        return product.getRentalPrice() * 2;
    }
    
    public int getDaysRented(){
        return daysRented;
    }
    
    public void setDaysRented(int days){
            daysRented = days;
    }
    
    @Override
    public String toString(){
        String output =  String.format("%2s %-15.15sdays rented: %5d\t$%7.2f\n", product.getCode(), product.getDescription(), daysRented, getRentalPrice());
        if(quantity >1)
            output += String.format("%2s\t%7.2f\n", "", "X" + quantity, quantity * getRentalPrice());
        return output;
    }
}
