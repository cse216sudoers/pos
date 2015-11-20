/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package process.sale.prototypes;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Pikachu
 */
public class RentalReturnController extends TransactionController{
    private RentalReturn rentalReturn;
    private Scanner scanner;
    
    /**
     *
     * @param rental
     */
    public RentalReturnController(Rental rental){
        rentalReturn = new RentalReturn(rental);
    }
    
    /**
     *
     * @param rentalReturn
     */
    public RentalReturnController(RentalReturn rentalReturn){
        this.rentalReturn = rentalReturn;
        display();
    }
    
    /**
     *
     */
    @Override
    public void start(){
        boolean done = false;
        //continuous retnal loop
        while(!done){
            //try{
                System.out.print("Please enter 'void', <code>, 'override', 'suspend', or 'close': ");
                scanner = new Scanner(System.in);
                input = scanner.next();
                //void item
                if(input.equalsIgnoreCase("void")){
                    processVoid();
                    display();
                }

                //override
                else if(input.equalsIgnoreCase("override")){
                    //manager override
                }
                //add item to rentalReturn
                else if (input.charAt(0) >= '0' && input.charAt(0) <= '9'){
                    processProduct(Integer.parseInt(input));
                    display();
                }
                //end rentalReturn
                else if (input.equalsIgnoreCase("close")){
                    // Close rentalReturn and get payment
                    close();
                    done = true;
                }
                //suspend Sale
                else if (input.equalsIgnoreCase("suspend")){
                    // Close Sale and get payment
                    processSuspend();
                    done = true;
                }
                else{
                    System.out.println("Invalid input: " + input);
                }
//            }catch(Exception e){
//                System.out.println(e.toString());
//                System.out.println("Invalid input");
//            }
        }
    }
    
    /**
     *
     */
    @Override
    protected void processSuspend(){
       // RentalManager.getInstance().addSuspendedRental(rentalReturn);
    }
    
    /**
     *
     */
    @Override
    protected void close() {
        String paymentType;
        boolean validType;
        
        // Give total price (subtotal, tax, and total)
        rentalReturn.printTotals();
        leftToPay = rentalReturn.getTotal();
        while(leftToPay > 0){            
            validType= false;

            System.out.println("Please enter a form of payment (cash, credit, or debit): ");
            paymentType = scanner.next();

            if(paymentType.equalsIgnoreCase("credit")||paymentType.equalsIgnoreCase("debit")||paymentType.equalsIgnoreCase("cash"))
                validType = true;
            while(!validType){
                System.out.println("Invalid payment type. \nPlease enter a form of payment (cash, credit, or debit): ");
                paymentType = scanner.next();
                if(paymentType.equalsIgnoreCase("credit")||paymentType.equalsIgnoreCase("debit")||paymentType.equalsIgnoreCase("cash"))
                    validType = true;
            }

            if(paymentType.equalsIgnoreCase("cash"))
                processCashPayment();
            else if(paymentType.equalsIgnoreCase("credit"))
                processCreditPayment();
            else if(paymentType.equalsIgnoreCase("debit"))
                processDebitPayment();
            if(leftToPay > 0)
                System.out.printf("Total: $%7.2f \n", leftToPay);
        }
        rentalReturn.getRental().addRentalreturn(rentalReturn);
        // Thank customer, and close
        printReceipt();
        System.out.println("\nThank for you shopping with us. Have a nice day!");
    }
    
    /**
     *
     */
    protected void processCashPayment(){
        float payment = 0;
        System.out.println("Please enter total cash payment: ");
        try{
            payment = scanner.nextFloat();
        }catch(Exception e){
            System.out.println("Invalid payment.");
        }
        if(payment > leftToPay){
            System.out.printf("Your change is $%.2f\n", payment - leftToPay);
            rentalReturn.addPayment(new CashPayment(payment, leftToPay));
            leftToPay = 0;
        }
        else if(payment == leftToPay){
            rentalReturn.addPayment(new CashPayment(payment, payment));
            leftToPay-=payment;
        }
        else{
            rentalReturn.addPayment(new CashPayment(payment, payment));
            leftToPay-=payment;
        }
    }
    
    /**
     *
     */
    protected void processCreditPayment(){
        float payment = 0;
        boolean invalid = true;
        boolean accepted; //for payment
        String cardNum = "";
        String secNum = "";
        
        System.out.println("Please enter total credit payment or enter 'total' to pay the whole balance: of type cancel");
        do{
            try{
                input = scanner.next();
                if(input.equalsIgnoreCase("total"))
                    payment = leftToPay;
                else if(input.equals("cancel"))
                    return;
                else{
                    payment = Float.parseFloat(input);
                    if(payment > leftToPay)
                        System.out.println("Payment is more than total.");
                }
            }catch(Exception e){
                System.out.println("Invalid payment.");
            }
        }while(payment > leftToPay);
        
        try{
            System.out.println("Please enter card number or type cancel: ");
            cardNum = scanner.next();
            if(input.equals("cancel"))
                return;

            System.out.println("Please enter security code or type cancel: ");
            input = scanner.next();
            if(input.equals("cancel"))
                return;
            secNum = input;
        }catch(Exception e){
            System.out.println("Invalid input.");
        }
        
        CreditPayment credit = new CreditPayment(cardNum, secNum, payment);
        accepted = processCreditPayment(credit);
        if(accepted){
            rentalReturn.addPayment(credit);
            leftToPay -= payment;
        }
        else{
            System.out.println("Card rejected.");
        }
    }
    
    /**
     *
     * @param payment
     * @return
     */
    protected boolean processCreditPayment(CreditPayment payment){
        String cardNum = payment.getCardNum();
        String secNum = payment.getSecurityCode();
        if(cardNum.length() == 16 && secNum.length() == 3)
            return true;
        return false;
    }
    
    /**
     *
     */
    protected void processDebitPayment(){
        float payment = 0;
        boolean invalid = true;
        boolean accepted; //for payment
        String cardNum = "";
        int pin = 0;
        
        System.out.println("Please enter total credit payment or enter 'total' to pay the whole balance: ");
        
        do{
            try{
                input = scanner.next();
                if(input.equalsIgnoreCase("total"))
                    payment = leftToPay;
                else if(input.equals("cancel"))
                    return;
                else{
                    payment = Float.parseFloat(input);
                    if(payment > leftToPay)
                        System.out.println("Payment is more than total.");
                }
            }catch(Exception e){
                System.out.println("Invalid payment.");
            }
        }while(payment > leftToPay);
        
        try{
            System.out.println("Please enter card number or type cancel: ");
            cardNum = scanner.next();
            if(input.equals("cancel"))
                return;

            System.out.println("Please enter pin or type cancel: ");
            input = scanner.next();
            if(input.equals("cancel"))
                return;
            pin = Integer.parseInt(input);                
        }catch(Exception e){
            System.out.println(e.getStackTrace() + "/nInvalid input.");
        }
        
        DebitPayment debit = new DebitPayment(cardNum, pin, payment);
        accepted = processDebitPayment(debit);
        if(accepted){
            rentalReturn.addPayment(debit);
            leftToPay-=payment;
        }
        else{
            System.out.println("Card rejected.");
        }
    }
    private boolean processDebitPayment(DebitPayment payment){
        String cardNum = "" + payment.getCardNum();
        String pin = "" + payment.getPin();
        if(cardNum.length() == 16 && pin.length() == 4)
            return true;
        return false;
    }
    
    /**
     *
     */
    @Override
    protected void processVoid(){
        System.out.print("Please enter a product code: ");
        int code = scanner.nextInt();
        ProductDescription product = ProductCatalog.getCatalog().findProductByCode(code);
        
        if(product == null){ //product does not exist
            System.out.println("Invalid product code: " + code);
            return;
        }
        rentalReturn.removeItem(product);
    }
    
    /**
     *
     * @param code
     */
    @Override
    protected void processProduct(int code){
        ProductDescription product = ProductCatalog.getCatalog().findProductByCode(code);
  
        if(product == null){ //product does not exist
            System.out.println("Invalid product code: " + code);
        }else if(!product.getIsRentable()){
            System.out.println("Item cannot be rented: " + code);
        }else{
            rentalReturn.addItem(product);
        }
    }
    
    private void processCoupon(){
        String next = "";
        int code;
        int productCode;
        float amount;
        try{
            System.out.print("Please enter coupon code: ");
            next = scanner.next();
            code = Integer.parseInt(next);
        }catch(Exception e){
            System.out.println("Invalid code: " + next);
            return;
        }
        
        try{
            System.out.print("Please enter product code: ");
            next = scanner.next();
            productCode = Integer.parseInt(next);
        }catch(Exception e){
            System.out.println("Invalid code: " + next);
            return;
        }

        try{
            System.out.print("Please enter coupon amount: ");
            next = scanner.next();
            DecimalFormat myFormatter = new DecimalFormat("0.00");
            amount = Float.parseFloat(next);
            amount = Float.parseFloat(myFormatter.format(amount));
        }catch(Exception e){
            System.out.println("Invalid amount: " + next);
            return;
        }
        
        rentalReturn.addCoupon(new Coupon(code, productCode, amount));
    }
    
    /**
     *
     */
    @Override
    protected final void display(){
        System.out.println(rentalReturn);
    }
    
    /**
     *
     */
    @Override
    protected void printReceipt(){
        System.out.print("******************************************");
        rentalReturn.printTotals();
        ArrayList<Payment> payments = rentalReturn.getPayments();
        for(int i = 0; i < payments.size(); i++){
            System.out.print(payments.get(i));
        }
        System.out.print("\n******************************************");
    }
}
