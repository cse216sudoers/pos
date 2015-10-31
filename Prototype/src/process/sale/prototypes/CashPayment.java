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
        this.amount = amount;
        this.type = Payment.PaymentType.CASH;
        this.cashGiven = cashGiven;
    }
    public float getCashGiven(){
        return cashGiven;
    }
    public void setCashGiven(float cashGiven){
        this.cashGiven = cashGiven;
    }
    @Override
    public String toString(){
        String output = "";
        output += String.format("%2s %16s\t$%-7.2f", "Cash", " ",cashGiven);
        output += "\n";
        if(cashGiven - amount > 0){
            output += String.format("%2s %16s\t$%-7.2f", "", "Change ",cashGiven - amount);
            output += "\n";
        }
        return output;
    }
}