/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package process.sale.prototypes;

/**
 *
 * @author Pikachu
 */
public class DebitPayment extends Payment{
    private int pin;
    private String cardNum;
    
    /**
     *
     * @param cardNum
     * @param pin
     * @param amount
     */
    public DebitPayment(String cardNum, int pin, float amount){
        type = PaymentType.DEBIT;
        this.amount = amount;
        this.cardNum= cardNum;
        this.pin = pin;
        
    }
    /**
     *
     * @return
     */
    public String getCardNum(){
        return cardNum;
    }
    
    /**
     *
     * @return
     */
    public int getPin(){
        return pin;
    }

    @Override
    public String toString(){
        return String.format("%2s %16s\t%-7.2f\n", "Debit", cardNum, amount);
    }
}
