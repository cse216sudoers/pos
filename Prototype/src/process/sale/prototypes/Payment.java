/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package process.sale.prototypes;

/**
 *
 * @author Pikachu
 */
public class Payment {
    protected float amount;
    protected enum PaymentType{
        CREDIT, DEBIT, CASH
    }
    protected PaymentType type;
    
    public Payment(PaymentType type, float amount){
        this.type = type;
        this.amount = amount;
    }
    public float getAmount(){
        return amount;
    }
    public void setAmount(float amount){
        this.amount = amount;
    }
}
