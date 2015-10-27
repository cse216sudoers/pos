/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package process.sale.prototypes;

/**
 *
 * @author Pikachu
 */
public abstract class Payment {
    protected float amount;
    protected enum PaymentType{
        CREDIT, DEBIT, CASH
    }
    protected PaymentType type;
    
    public float getAmount(){
        return amount;
    }
    public void setAmount(float amount){
        this.amount = amount;
    }
}
