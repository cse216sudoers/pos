/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package process.sale.prototypes;

/**
 *
 * @author Pikachu
 */
public class CreditPayment extends Payment{
    private int securityCode;
    private int cardNum;
    
    public CreditPayment(int cardNum, int securityCode, float amount){
        super(PaymentType.CREDIT, amount);
        this.cardNum= cardNum;
        this.securityCode = securityCode;
    }
    public int getCardNum(){
        return cardNum;
    }
    public void setCardNum(int cardNum){
        this.cardNum = cardNum;
    }
    public int getSecurityCode(){
        return securityCode;
    }
    public void setSecurityCode(int securityCode){
        this.securityCode = securityCode;
    }
}
