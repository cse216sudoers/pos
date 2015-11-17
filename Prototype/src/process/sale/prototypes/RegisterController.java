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
                String type = scan.next();

                if(input.equalsIgnoreCase("sale") && type.equalsIgnoreCase("new")){
                    processSale();
                }else if(input.equalsIgnoreCase("return") && type.equalsIgnoreCase("new")){
                    processReturn();
                }else if(input.equalsIgnoreCase("rental") && type.equalsIgnoreCase("new")){
                    processRental();
                }else if(type.equalsIgnoreCase("suspended")){
                    processSuspended(input);
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
    private void processSuspendedSale(Sale sale){
        currentTransaction = new SaleController(sale);
        currentTransaction.start();
    }
    
    //start a suspended return
    private void processSuspendedReturn(Return ret){
        currentTransaction = new ReturnController(ret);
        currentTransaction.start();
    }
    
    //start a suspended rental
    private void processSuspendedRental(Rental rental){
        currentTransaction = new RentalController(rental);
        currentTransaction.start();
    }
    
    //start a sale
    private void processSale(){
        currentTransaction = new SaleController();
        currentTransaction.start();
    }
    
    //start a return
    private void processReturn(){
        Scanner scan = new Scanner(System.in);
        Transaction t = null;
        String returnType = "";
        
        do{
            System.out.println("Rental or sale return: ");
            returnType = scan.next();
        }while(!returnType.equalsIgnoreCase("Rental") && !returnType.equalsIgnoreCase("Return"));
        
        do{
            try{
                System.out.println("Please enter receipt ID or q to quit: ");
                String next = scan.next();
                if(next.equalsIgnoreCase("q")){
                    return;
                }else if(returnType.equals("Return")){
                    t = saleManager.getSaleById(Integer.parseInt(next));
                }else if(returnType.equals("Rental")){
                    t = RentalManager.getInstance().getRentalById(Integer.parseInt(next));
                }else{
                    System.out.println("Invalid input");
                }
            }catch(Exception e){
                System.out.println("Invalid input");
            }
        }while(t == null);
        
        if(returnType.equals("return")){
            
        }else{
            currentTransaction = new RentalReturnController((Rental)t);
            currentTransaction.start();
        }
    }
   
    //start a rental
    private void processRental(){
        currentTransaction = new RentalController();
        currentTransaction.start();
    }
    
    //start user management (for Admin access level only)
    private void processUserManagement(){
        UserManagementController userManagementController = new UserManagementController();
        userManagementController.start();
    }
}
