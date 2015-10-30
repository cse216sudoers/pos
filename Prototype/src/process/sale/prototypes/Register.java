
package process.sale.prototypes;

import java.util.Scanner;

public class Register {
    private int id;
    private Cashier currentCashier;
    private RegisterManager registerManager;
    private CashierManager cashierManager;
    private TransactionController currentTransaction;
    private SaleManager saleManager;
    
    public Register(){
        registerManager = RegisterManager.getInstance();
        saleManager = SaleManager.getInstance();
        cashierManager = CashierManager.getInstance();
        currentCashier = null;
        currentTransaction = null;
        id = registerManager.getNextId();
    }
    
    public void checkCashier(){
        if(currentCashier == null){
            promptLogIn();
        }
    }
    
    private void promptLogIn(){
        Scanner scan = new Scanner(System.in);
        String username;
        System.out.println("****Log In****");
        System.out.println("Username: ");
        while(true){
            username = scan.next();
            if(cashierManager.getCashierByUsername(username) != null){
                if(checkPassword(username))
                    break;
            }else{
                continue;
            }
            System.out.println("Incorrect. \nUsername: ");
        }
        System.out.println("****Log in successful****");
        currentCashier = cashierManager.getCashierByUsername(username);
    }
    
    private boolean checkPassword(String username){
        Scanner scan = new Scanner(System.in);
        String password;
        System.out.println("Password: ");
        while(true){
            password= scan.next();
            if(password.equals(cashierManager.getCashierByUsername(username).getPassword())){
                return true;
            }
            System.out.println("Incorrect. \nPassword: ");
        }
    }
    
    public boolean logOffRegister(){
        currentCashier = null;
        return true; //successful (idk if we will have unsuccessful cases later)
    }
    public void processSuspendedSale(Sale sale){
        currentTransaction = new SaleController(sale);
        currentTransaction.start();
    }
    
    public void processSuspendedReturn(Return ret){
        currentTransaction = new ReturnController(ret);
        currentTransaction.start();
    }
    
    public void processSuspendedRental(Rental rental){
        currentTransaction = new RentalController();
        currentTransaction.start();
    }

    public void processSale(){
        currentTransaction = new SaleController();
        currentTransaction.start();
    }
    
    public void processReturn(){
        Scanner scan = new Scanner(System.in);
        Sale sale = null;
        do{
            try{
                System.out.println("Please enter receipt ID: ");
                sale = saleManager.getSaleById(scan.nextInt());
            }catch(Exception e){
                System.out.println("Invalid input");
            }
            
        }while(sale == null);
        currentTransaction = new ReturnController(sale.getId());
        currentTransaction.start();
    }
    
    public void processRental(){
        currentTransaction = new RentalController();
        currentTransaction.start();
    }
    
    public int getId(){
        return id;
    }
}
