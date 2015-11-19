/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package process.sale.prototypes;

/**
 * Line Item child for Rentals because we needed additional fields
 * @author Pikachu
 */
public class RentalLineItem extends LineItem{
    private int daysRented;
    
    /**
     * 
     * @param product
     * @param daysRented
     */
    public RentalLineItem(ProductDescription product, int daysRented){
        super(product);
        this.daysRented = daysRented;
    }
    
    /**
     *
     * @return total price of rental
     */
    public float getRentalPrice(){
        return product.getRentalPrice() * daysRented;
    }
    
    /**
     *
     * @return daily late fee
     */
    public float getLatePayment(){
        return product.getRentalPrice();
    }
    
    /**
     * 
     * @return days rented
     */
    public int getDaysRented(){
        return daysRented;
    }
    
    /**
     * set days rented
     * @param days days rented
     */
    public void setDaysRented(int days){
            daysRented = days;
    }
    
    @Override
    public String toString(){
        String output =  String.format("%2d %-15.15s \tdays rented: %5d\t$%7.2f\n", product.getCode(), product.getDescription(), daysRented, getRentalPrice());
        if(quantity >1)
            output += String.format("\tX%4d \t%7.2f\n", quantity, getRentalPrice()*quantity);
        return output;
    }
}
