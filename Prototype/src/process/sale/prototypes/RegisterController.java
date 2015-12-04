/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package process.sale.prototypes;

import java.util.Scanner;

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
    
    /**
     * Start/Turn on the register
     */
    public void start(){
        Scanner scan = new Scanner(System.in);
        String input;
        do{
            String otherOptions = "";
            if(register.getCashier().getAccess() == Cashier.Access.Admin)
                    otherOptions += "user management, ";
            System.out.printf("Enter 'sale', 'return', 'rental', %sor 'q' to log off: \n", otherOptions);
            input = scan.nextLine();
            if(input.equalsIgnoreCase("q")){
                register.logOffRegister();
                break;
            }else if (input.equalsIgnoreCase("User Management")){
                processUserManagement();
            }else if (input.equalsIgnoreCase("sale") || input.equalsIgnoreCase("return") || input.equalsIgnoreCase("rental")){
                System.out.println("Enter 'new' or 'suspended': ");
                String type = scan.nextLine();

                if(input.equalsIgnoreCase("sale") && type.equalsIgnoreCase("new")){
                    processSale();
                }else if(input.equalsIgnoreCase("return") && type.equalsIgnoreCase("new")){
                    //processReturn();
                }else if(input.equalsIgnoreCase("rental") && type.equalsIgnoreCase("new")){
                    processRental();
                }else if(type.equalsIgnoreCase("suspended")){
                    processSuspended(input);
                }else{
                    System.out.println("Invalid Input");
                }
            }else{
                System.out.println("Invalid Input");
            }
            ProductCatalog.getCatalog().updateFile();
        }while(!input.equalsIgnoreCase("q"));
    }
     
    private void processSuspended(String type){
        System.out.printf("Enter suspended %s id: \n", type);
        int id;
        Scanner scan = new Scanner(System.in);
        do{
            try{
                id = scan.nextInt();
                break;
            }catch(Exception e){
                System.out.println("Invalid id. Please enter an integer.");
            }
        }while(true);

        if(type.equalsIgnoreCase("sale")){
            Sale sale = SaleManager.getInstance().getSuspendedSaleById(id);
            if(sale != null)
                processSuspendedSale(sale);
            else
                System.out.printf("Suspended sale with id %d does not exist.\n", id);
        }else if(type.equalsIgnoreCase("return")){
            Return ret = ReturnManager.getInstance().getSuspendedReturnById(id);
            if(ret != null)
                processSuspendedReturn(ret);
            else
                System.out.printf("Suspended return with id %d does not exist.\n", id);
        }else if(type.equalsIgnoreCase("rental")){
            Rental rental = RentalManager.getInstance().getSuspendedRentalById(id);
            if(rental != null)
                processSuspendedRental(rental);
            else
                System.out.printf("Suspended rental with id %d does not exist.\n", id);
        }
    }
    /**
     * Check if username is valid
     * @param username
     * @return
     */
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
    
    //start user management (for Admin access level only)
    public void processUserManagement(){
        UserManagementController userManagementController = new UserManagementController();
        userManagementController.start();
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
            System.out.println("Username and Password conbination incorrect.");
        }
    }
}