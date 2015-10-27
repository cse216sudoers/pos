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
    private String securityCode;
    private String cardNum;
    
    public CreditPayment(String cardNum, String securityCode, float amount){
        type = PaymentType.CREDIT;
        this.amount = amount;
        this.cardNum= cardNum;
        this.securityCode = securityCode;
    }
    public String getCardNum(){
        return cardNum;
    }
    public void setCardNum(String cardNum){
        this.cardNum = cardNum;
    }
    public String getSecurityCode(){
        return securityCode;
    }
    public void setSecurityCode(String securityCode){
        this.securityCode = securityCode;
    }
    @Override
    public String toString(){
        return String.format("%2s %16s\t$%-7.2f", "Credit", cardNum, amount);
    }
}
