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
    private int cardNum;
    
    public DebitPayment(int cardNum, int pin){
        type = PaymentType.DEBIT;
        this.cardNum= cardNum;
        this.pin = pin;
    }
    public int getCardNum(){
        return cardNum;
    }
    public void setCardNum(int cardNum){
        this.cardNum = cardNum;
    }
    public int getPin(){
        return pin;
    }
    public void setPin(int pin){
        this.pin = pin;
    }
}
