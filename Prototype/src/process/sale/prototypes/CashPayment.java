/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package process.sale.prototypes;

/**
 *
 * @author Pikachu
 */
public class CashPayment extends Payment{
    private float cashGiven;
    
    public CashPayment(float cashGiven, float amount){
        super(PaymentType.CREDIT, amount);
        type = PaymentType.CASH;
        this.cashGiven = cashGiven;
    }
    public float getCashGiven(){
        return cashGiven;
    }
    public void setCashGiven(float cashGiven){
        this.cashGiven = cashGiven;
    }
}
