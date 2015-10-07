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
    private String cardNum;
    
    public CreditPayment(String cardNum, int securityCode, float amount){
        super(PaymentType.CREDIT, amount);
        this.cardNum= cardNum;
        this.securityCode = securityCode;
    }
    public String getCardNum(){
        return cardNum;
    }
    public void setCardNum(String cardNum){
        this.cardNum = cardNum;
    }
    public int getSecurityCode(){
        return securityCode;
    }
    public void setSecurityCode(int securityCode){
        this.securityCode = securityCode;
    }
    @Override
    public String toString(){
        return String.format("%2s %16s\t$%-7.2f", "Credit", cardNum, amount);
    }
}
