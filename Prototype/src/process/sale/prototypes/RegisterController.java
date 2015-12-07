/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package process.sale.prototypes;

import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 * Does operations for a register domain object
 * @author Pikachu
 */
public class RegisterController{
    private RegisterManager registerManager;
    private CashierManager cashierManager;
    private TransactionController currentTransaction;
    private SaleManager saleManager;
    private Register register;
    
    /**
     * 
     * @return register
     */
    public Register getRegister(){
        return register;
    }
    
    /**
     * Register to make a controller for
     * @param register
     */
    public RegisterController(Register register){
        registerManager = RegisterManager.getInstance();
        saleManager = SaleManager.getInstance();
        cashierManager = CashierManager.getInstance();
        this.register = register;
        currentTransaction = null;
    }
    
    public TransactionController getCurrentTransaction(){
        return currentTransaction;
    }
    /**
     * Register ID to make controller for
     * @param registerId
     */
    public RegisterController(int registerId){
        registerManager = RegisterManager.getInstance();
        saleManager = SaleManager.getInstance();
        cashierManager = CashierManager.getInstance();
        currentTransaction = null;
        register = registerManager.getRegisterById(registerId);
    }
    
    
    public boolean verifyUsername(String username){
        if(cashierManager.getCashierByUsername(username) != null)
                return true;
        return false;
    }
    
    /**
     * Check if cashier password is correct
     * @param username
     * @param password
     * @return
     */
    public Cashier verifyPassword(String username, String password){
        Cashier cashier = cashierManager.getCashierByUsername(username);
        if(password.equals(cashier.getPassword()))
            return cashier;
        return null;
    }
    
    //start a suspended sale
    public void processSuspendedSale(Sale sale){
        currentTransaction = new SaleController(sale);
    }
    
    //start a suspended return
    public void processSuspendedReturn(Return ret){
        currentTransaction = new ReturnController(ret);
    }
    
    //start a suspended rental
    public void processSuspendedRental(Rental rental){
        currentTransaction = new RentalController(rental);
    }
    
    //start a sale
    public void processSale(){
        currentTransaction = new SaleController();
        //currentTransaction.start();
    }
    
    //start a return
    public void processSaleReturn(Sale sale){
        currentTransaction = new ReturnController(sale.getId());
    }
    
    public void processRentalReturn(Rental rental){
        currentTransaction = new RentalReturnController(rental);
    }
    //start a rental
    public void processRental(){
        currentTransaction = new RentalController();
    }
    
    //log on register
    public void logOn(){
        Scanner scan = new Scanner(System.in);
        while(true){
            System.out.println("Username:");
            String username = scan.next();
            System.out.println("Password:");
            String password = scan.next();
            if(verifyUsername(username)){
                Cashier cashier = verifyPassword(username, password);
                if(cashier != null){
                    getRegister().setCashier(cashier);
                    cashierManager.addCurrentCashier(cashier);
                    break;
                }
            }
            JOptionPane.showMessageDialog (null, "Username and Password combination incorrect", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            System.out.println("Username and Password conbination incorrect.");
        }
    }
}