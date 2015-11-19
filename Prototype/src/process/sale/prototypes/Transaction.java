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
    
    /**
     *
     */
    public abstract void printTotals();
    @Override
    public abstract String toString();
}
