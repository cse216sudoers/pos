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
    
    public DebitPayment(String cardNum, int pin, float amount){
        type = PaymentType.DEBIT;
        this.amount = amount;
        this.cardNum= cardNum;
        this.pin = pin;
        
    }
    public String getCardNum(){
        return cardNum;
    }
    public void setCardNum(String cardNum){
        this.cardNum = cardNum;
    }
    public int getPin(){
        return pin;
    }
    public void setPin(int pin){
        this.pin = pin;
    }
    @Override
    public String toString(){
        return String.format("%2s %16s\t%-7.2f", "Debit", cardNum, amount);
    }
}
