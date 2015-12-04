/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package process.sale.prototypes;

/**
 *
 * @author Pikachu
 */
public abstract class TransactionController {
    /**
     *
     */
    protected float leftToPay; //so we can do multiple tupes of payments
    /**
     *
     */
    protected String input;

    /**
     *
     */
    public abstract void processSuspend();
    /**
     *
     * @param code
     */
    public abstract void processProduct(int code, int amount);
    /**
     *
     */
    public abstract String display();
    /**
     *
     */
    public abstract String printReceipt();
    
    public float getLeftToPay(){
        return leftToPay;
    }
    
    public void setLeftToPay(float l){
        leftToPay = l;
    }
}
