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
    
    public abstract void printTotals();
    @Override
    public abstract String toString();
}
