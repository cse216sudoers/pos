
package process.sale.prototypes;

public class Store {
    private String name;
    private int storeCode;
    private ProductCatalog productCatalog;
    private static Store store;
    private CashierManager cashierManager;
    private RegisterManager registerManager;
    private SaleManager saleManager; //save sales for return later
    
    private Store(){
        cashierManager = CashierManager.getInstance();
        registerManager = RegisterManager.getInstance();
        productCatalog = ProductCatalog.getCatalog();
        saleManager = SaleManager.getInstance();
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
    
    public static void main(String[] args){
        Store mainStore = Store.getStore();
        CashierManager cashierManager = CashierManager.getInstance();
        RegisterManager registerManager = RegisterManager.getInstance();
        cashierManager.addCashier(new Cashier("Bobbert", "bob", "bob"));
        
        mainStore.processSale();
    }
}
