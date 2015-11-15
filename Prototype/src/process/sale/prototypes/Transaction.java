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
     *
     */
    protected float total;
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
        // return total;
        total = 0;
        for (LineItem line : lines)
            total += line.getPrice();
        return total;
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
     * @param product
     */
    public abstract void removeItem(ProductDescription product);
    
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
    
    /**
     *
     */
    public abstract void printTotals();
    @Override
    public abstract String toString();
}
