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
    protected float leftToPay; //so we can do multiple tupes of payments
    protected String input;
    
    public abstract void start();
    protected abstract void close();
    protected abstract void processSuspend();
    protected abstract void processVoid();
    protected abstract void processProduct(int code);
    protected abstract void display();
    protected abstract void printReceipt();
}
