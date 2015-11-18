
package process.sale.prototypes;

import java.util.Scanner;
import DB.User;

/**
 *
 * @author Pikachu
 */
public class Store {
    private String name;
    private int storeCode;
    private ProductCatalog productCatalog;
    private static Store store;
    private CashierManager cashierManager;
    private RegisterManager registerManager;
    private SaleManager saleManager; //save sales for return later
    private RentalManager rentalManager;
    private ReturnManager returnManager;
    
    private Store(){
        cashierManager = CashierManager.getInstance();
        registerManager = RegisterManager.getInstance();
        productCatalog = ProductCatalog.getCatalog();
        saleManager = SaleManager.getInstance();
        rentalManager = RentalManager.getInstance();
        returnManager = ReturnManager.getInstance();
    }
    /**
     *
     * @return
     */
    public static synchronized Store getStore(){
        if(store==null){
            store=new Store();
        }
        return store;
    }
    
    /**
     *
     * @param args
     */
    public static void main(String[] args){
        DB.User.insert("Test", 0, "Test", "Test");
        Scanner scan = new Scanner(System.in);
        Store mainStore = Store.getStore();
        CashierManager cashierManager = CashierManager.getInstance();
        RegisterManager registerManager = RegisterManager.getInstance();
        Register register = new Register(registerManager.getNextId());
        registerManager.addRegister(register);
        RegisterController currentRegisterController = new RegisterController(register);
        while(true){
            System.out.println("Username:");
            String username = scan.next();
            System.out.println("Password:");
            String password = scan.next();
            if(currentRegisterController.verifyUsername(username)){
                Cashier cashier = currentRegisterController.verifyPassword(username, password);
                if(cashier != null){
                    currentRegisterController.getRegister().setCashier(cashier);
                    break;
                }
            }
            System.out.println("Username and Password conbination incorrect.");
        }
        
        currentRegisterController.start();
    }
}
