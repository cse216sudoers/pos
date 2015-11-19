/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package process.sale.prototypes;

/**
 *
 * @author Pikachu
 */
public abstract class Payment {
    /**
     *
     */
    protected float amount;
    /**
     * Payment type
     */
    protected enum PaymentType{
        /**
         * Credit payment
         */
        CREDIT,
        /**
         * Debit payment
         */
        DEBIT,
        /**
         * Cash payment
         */
        CASH
    }
    /**
     *
     */
    protected PaymentType type;
    
    /**
     * get amount payment was for
     * @return
     */
    public float getAmount(){
        return amount;
    }
    public PaymentType getType(){
        return type;
    }
    /**
     * set amount payment was for
     * @param amount
     */
    public void setAmount(float amount){
        this.amount = amount;
    }
}
