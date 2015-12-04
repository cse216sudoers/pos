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
public abstract class Transaction {
    /**
     * total with tax
     */
    protected float total;
    /**
     *
     */
    protected float subTotal;
    /**
     * 
     */
    protected ArrayList<LineItem> lines;
    /**
     *
     */
    protected ArrayList<Payment> payments; //for when we need to do returns
    /**
     *
     */
    protected int id;
    
    /**
     *
     * @return
     */
    public float getTotal(){
        return total;
    }
    
    public float getSubTotal(){
        return subTotal;
    }

    /**
     *
     * @param payment
     */
    public void addPayment(Payment payment){
        payments.add(payment);
    }
    
    /**
     *
     * @return
     */
    public ArrayList<LineItem> getLines(){
        return lines;
    }
    
    /**
     *
     * @return
     */
    public ArrayList<Payment> getPayments(){
        return payments;
    }
    
    /**
     *
     * @return
     */
    public int getId(){
        return id;
    }
    
    public String printTotals() {
        String output = "";
        // Calculate tax and total
        float tax = TaxCalculator.getTax(subTotal);
        total = subTotal + tax;
        
        // Set up ability to format print statements right so everything aligns
        int digits = ((Float)total).toString().length();
        String format = "%" + digits + ".2f";
        
        output += (toString() + "\n");
        output += String.format("Subtotal: $" + format + "\n", subTotal);
        output += String.format("Tax:      $" + format + "\n", tax);
        output += String.format("Total:    $" + format + "\n", total);
        
        return output;
    }
    
    @Override
    public abstract String toString();
}
