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
        super(PaymentType.CASH, amount);
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
        output += String.format("%2s %16s\t$%-7.2f", "Cash", " ",amount);
        output += "\n";
        output += String.format("%2s %16s\t$%-7.2f", "", "Change ",cashGiven - this.amount);
        return output;
    }
}
