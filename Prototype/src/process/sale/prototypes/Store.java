
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
    
    public void processSale(){
        SaleController controller = new SaleController();
        controller.startSale();
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
        ReturnController controller = new ReturnController(sale.getId());
        controller.startReturn();
    }
    
    public void processRental(){
        RentalController controller = new RentalController();
        controller.startRental();
    }
    
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        Store mainStore = Store.getStore();
        CashierManager cashierManager = CashierManager.getInstance();
        RegisterManager registerManager = RegisterManager.getInstance();
        String input;
        do{
            System.out.println("Enter sale, return, rental, or q to quit: ");
            input = scan.next();
            if(input.equalsIgnoreCase("sale"))
                mainStore.processSale();
            else if(input.equalsIgnoreCase("return"))
                mainStore.processReturn();
            else if(input.equalsIgnoreCase("rental"))
                mainStore.processRental();
            else
                System.out.println("Invalid Input");
        }while(!input.equalsIgnoreCase("q"));
    }
}
