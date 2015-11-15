/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package process.sale.prototypes;

/**
 * Keep track of credit payment
 * @author Pikachu
 */
public class CreditPayment extends Payment{
    private String securityCode;
    private String cardNum;
    
    /**
     * Create Credit payment
     * @param cardNum
     * @param securityCode
     * @param amount total payment amount
     */
    public CreditPayment(String cardNum, String securityCode, float amount){
        type = PaymentType.CREDIT;
        this.amount = amount;
        this.cardNum= cardNum;
        this.securityCode = securityCode;
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
    public String getSecurityCode(){
        return securityCode;
    }

    @Override
    public String toString(){
        return String.format("%2s %16s\t$%-7.2f", "Credit", cardNum, amount);
    }
}
