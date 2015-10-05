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
    private float amount;
    public enum PaymentType{
        CREDIT, DEBIT, CASH
    }
    PaymentType type;
    public float getAmount(){
        return amount;
    }
    public void setAmount(float amount){
        this.amount = amount;
    }
}
