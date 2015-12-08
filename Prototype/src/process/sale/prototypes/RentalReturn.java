/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package process.sale.prototypes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
        id=rental.getId();
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
            if(lines.get(i).getProduct().getCode() == code && ((RentalReturnLineItem)lines.get(i)).getDaysRented() == daysRented)
                return lines.get(i);
        }
        return null;
    }
    
    /**
     *
     * @param product
     * @param daysRented
     * @param affectQuantity
     */
    public void addItem(ProductDescription product, int daysRented, boolean affectQuantity){
        LineItem lineItem = getLineItemByCodeAndDaysRented(product.getCode(), daysRented);
        GregorianCalendar now= new GregorianCalendar();
        SimpleDateFormat ft =  new SimpleDateFormat ("yyyy:MM:dd");
        String parsedate = ft.format(rental.getRentalDate().getTime());
        String[] pair = parsedate.split(":");
        int year= Integer.parseInt(pair[0]);
        int month=Integer.parseInt(pair[1]);
        int day=Integer.parseInt(pair[2]);
        GregorianCalendar then = new GregorianCalendar(year,month,day);
        
        then.add(Calendar.DAY_OF_MONTH, daysRented);
        int dayslate=0;
        while(then.before(now)){
            then.add(Calendar.DAY_OF_MONTH, 1);
            dayslate++;
        }
        if(lineItem!=null){
            lineItem.increaseQuantity();
            subTotal += ((RentalReturnLineItem)lineItem).getLateFee();
            product.decreaseQuantity();
        } 
        else{
            lineItem = new RentalReturnLineItem(product, dayslate, daysRented);
            lines.add(lineItem);
            subTotal += ((RentalReturnLineItem)lineItem).getLateFee();
            product.decreaseQuantity();
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
            return false;
        }
        return true;
    }
    
    @Override
    public String toString(){
        String output = "******RentalReturn******* \nReturn ID: " + id + "\n";
        for(int i = 0; i < lines.size(); i++){
            output += lines.get(i).toString();
        }
        return output;
    }
}