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
    private int cardNum;
    private int securityCode;
    
    public int getSecurityCode(){
        return securityCode;
    }
    public void setSecurityCode(int securityCode){
        this.securityCode = securityCode;
    }
    public int getCardNum(){
        return cardNum;
    }
    public void setCardNum(int cardNum){
        this.cardNum = cardNum;
    }
    public float getAmount(){
        return amount;
    }
    public void setAmount(float amount){
        this.amount = amount;
    }
}
