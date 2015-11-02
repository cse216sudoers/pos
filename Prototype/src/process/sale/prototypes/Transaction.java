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
    protected float total;
    protected ArrayList<LineItem> lines;
    protected ArrayList<Payment> payments; //for when we need to do returns
    protected int id;
    
    public float getTotal(){
        return total;
    }

    public void addPayment(Payment payment){
        payments.add(payment);
    }
    
    public ArrayList<LineItem> getLines(){
        return lines;
    }
        
    public abstract void removeItem(ProductDescription product);
    
    public ArrayList<Payment> getPayments(){
        return payments;
    }
    
    public int getId(){
        return id;
    }
    
    public abstract void printTotals();
    @Override
    public abstract String toString();
}
