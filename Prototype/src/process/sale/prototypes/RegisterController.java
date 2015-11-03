/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package process.sale.prototypes;

import java.util.Scanner;

/**
 *
 * @author Pikachu
 */
public class RegisterController{
    private RegisterManager registerManager;
    private CashierManager cashierManager;
    private TransactionController currentTransaction;
    private SaleManager saleManager;
    private Register register;
    public Register getRegister(){
        return register;
    }
    public RegisterController(Register register){
        registerManager = RegisterManager.getInstance();
        saleManager = SaleManager.getInstance();
        cashierManager = CashierManager.getInstance();
        this.register = register;
        currentTransaction = null;
    }
    
    public RegisterController(int registerId){
        registerManager = RegisterManager.getInstance();
        saleManager = SaleManager.getInstance();
        cashierManager = CashierManager.getInstance();
        currentTransaction = null;
        register = registerManager.getRegisterById(registerId);
    }
    
    public void start(){
        Scanner scan = new Scanner(System.in);
        String input;
        do{
            String otherOptions = "";
            if(register.getCashier().getAccess() == Cashier.Access.Admin)
                    otherOptions += "user management, ";
            System.out.printf("Enter 'sale', 'return', 'rental', %sor 'q' to log off: \n", otherOptions);
            input = scan.next();
            if(input.equalsIgnoreCase("q")){
                register.logOffRegister();
                break;
            }else if (input.equalsIgnoreCase("user management")){
                processUserManagement();
            }
            
            System.out.println("Enter 'new' or 'suspended': ");
            String type = scan.next();
            
            if(input.equalsIgnoreCase("sale") && type.equalsIgnoreCase("new")){
                processSale();
            }else if(input.equalsIgnoreCase("return") && type.equalsIgnoreCase("new")){
                processReturn();
            }else if(input.equalsIgnoreCase("rental") && type.equalsIgnoreCase("new")){
                processRental();
            }else if(type.equalsIgnoreCase("suspended")){
                System.out.printf("Enter suspended %s id: \n", input);
                int id;
                
                do{
                    try{
                        id = scan.nextInt();
                        break;
                    }catch(Exception e){
                        System.out.println("Invalid id. Please enter an integer.");
                    }
                }while(true);
                
                if(input.equalsIgnoreCase("sale")){
                    Sale sale = SaleManager.getInstance().getSuspendedSaleById(id);
                    if(sale != null)
                        processSuspendedSale(sale);
                    else
                        System.out.printf("Suspended sale with id %d does not exist.\n", id);
                }else if(input.equalsIgnoreCase("return")){
                    Return ret = ReturnManager.getInstance().getSuspendedReturnById(id);
                    if(ret != null)
                        processSuspendedReturn(ret);
                    else
                        System.out.printf("Suspended return with id %d does not exist.\n", id);
                }else if(input.equalsIgnoreCase("rental")){
                    Rental rental = RentalManager.getInstance().getSuspendedRentalById(id);
                    if(rental != null)
                        processSuspendedRental(rental);
                    else
                        System.out.printf("Suspended rental with id %d does not exist.\n", id);
                }
            }else{
                System.out.println("Invalid Input");
            }
            ProductCatalog.getCatalog().updateFile();
        }while(!input.equalsIgnoreCase("q"));
    }
     
    public boolean verifyUsername(String username){
        if(cashierManager.getCashierByUsername(username) != null)
                return true;
        return false;
    }
    
    public Cashier verifyPassword(String username, String password){
        Cashier cashier = cashierManager.getCashierByUsername(username);
        if(password.equals(cashier.getPassword()))
            return cashier;
        return null;
    }
    
    public void processSuspendedSale(Sale sale){
        currentTransaction = new SaleController(sale);
        currentTransaction.start();
    }
    
    private void processSuspendedReturn(Return ret){
        currentTransaction = new ReturnController(ret);
        currentTransaction.start();
    }
    
    private void processSuspendedRental(Rental rental){
        currentTransaction = new RentalController(rental);
        currentTransaction.start();
    }

    private void processSale(){
        currentTransaction = new SaleController();
        currentTransaction.start();
    }
    
    private void processReturn(){
        Scanner scan = new Scanner(System.in);
        Sale sale = null;
        do{
            try{
                System.out.println("Please enter receipt ID or q to quit: ");
                String next = scan.next();
                if(next.equalsIgnoreCase("q")){
                    break;
                }
                sale = saleManager.getSaleById(scan.nextInt());
            }catch(Exception e){
                System.out.println("Invalid input");
            }
            
        }while(sale == null);
        
        if(sale != null){
            currentTransaction = new ReturnController(sale.getId());
            currentTransaction.start();
        }
    }
    
    private void processRental(){
        currentTransaction = new RentalController();
        currentTransaction.start();
    }
    
    private void processUserManagement(){
        UserManagementController userManagementController = new UserManagementController();
        userManagementController.start();
    }
}
