/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package process.sale.prototypes;

/**
 * Cash Payment during a transaction
 */
public class CashPayment extends Payment{
    private float cashGiven;
    
    /**
     *
     * @param cashGiven cash given to the cashier
     * @param amount total of the transaction
     */
    public CashPayment(float cashGiven, float amount){
        this.amount = amount;
        this.type = Payment.PaymentType.CASH;
        this.cashGiven = cashGiven;
    }
    
    /**
     * @return amount of cash given
     */
    public float getCashGiven(){
        return cashGiven;
    }
    
    /**
     * @param cashGiven amount of cash given to the cashier
     */
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