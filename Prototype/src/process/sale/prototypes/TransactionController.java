/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package process.sale.prototypes;

import javax.swing.JOptionPane;

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
    //Check if debit payment is valid
    protected boolean processDebitPayment(DebitPayment payment){
        String cardNum = "" + payment.getCardNum();
        String pin = "" + payment.getPin();
        if(cardNum.length() == 16 && pin.length() == 4)
            return true;
        JOptionPane.showMessageDialog (null, "Card declined.Enter 16 digit card number and 4 digit pin.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    //check if payment is valid
    protected boolean processCreditPayment(CreditPayment payment){
        String cardNum = payment.getCardNum();
        String secNum = payment.getSecurityCode();
        if(cardNum.length() == 16 && secNum.length() == 3)
            return true;
        JOptionPane.showMessageDialog (null, "Card declined. Enter 16 digit card number and 3 digit security code.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        return false;
    }
}
