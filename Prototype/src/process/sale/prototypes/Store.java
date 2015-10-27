
package process.sale.prototypes;

import java.util.Scanner;

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
        registerManager.addRegister(new Register());
    }
    public static synchronized Store getStore(){
        if(store==null){
            store=new Store();
        }
        return store;
    }
    
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        
        Store mainStore = Store.getStore();
        CashierManager cashierManager = CashierManager.getInstance();
        RegisterManager registerManager = RegisterManager.getInstance();
        registerManager.addRegister(new Register());
        Register currentRegister = registerManager.getRegisterById(1);
        String input;
        do{
            currentRegister.checkCashier();
            System.out.println("Enter 'sale', 'return', 'rental', or 'q' to quit: ");
            input = scan.next();
            if(input.equalsIgnoreCase("q")){
                break;
            }
            System.out.println("Enter 'new' or 'suspended': ");
            String type = scan.next();
            
            if(input.equalsIgnoreCase("sale") && type.equalsIgnoreCase("new")){
                currentRegister.processSale();
            }else if(input.equalsIgnoreCase("return") && type.equalsIgnoreCase("new")){
                currentRegister.processReturn();
            }else if(input.equalsIgnoreCase("rental") && type.equalsIgnoreCase("new")){
                currentRegister.processRental();
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
                        currentRegister.processSuspendedSale(sale);
                    else
                        System.out.printf("Suspended sale with id %d does not exist.\n", id);
                }else if(input.equalsIgnoreCase("return")){
                    Return ret = ReturnManager.getInstance().getSuspendedReturnById(id);
                    if(ret != null)
                        currentRegister.processSuspendedReturn(ret);
                    else
                        System.out.printf("Suspended return with id %d does not exist.\n", id);
                }else if(input.equalsIgnoreCase("rental")){
                    Rental rental = RentalManager.getInstance().getSuspendedRentalById(id);
                    if(rental != null)
                        currentRegister.processSuspendedRental(rental);
                    else
                        System.out.printf("Suspended rental with id %d does not exist.\n", id);
                }
            }else if(!input.equalsIgnoreCase("q")){
                System.out.println("Invalid Input");
            }
        }while(!input.equalsIgnoreCase("q"));
    }
}
