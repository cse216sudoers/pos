
package process.sale.prototypes;

public class Store {
    private String name;
    private int storeCode;
    private ProductCatalog productCatalog;
    private static Store store;
    
    private Store(){
        productCatalog = ProductCatalog.getCatalog();
    }
    
    
    public static synchronized Store getStore(){
        if(store==null){
            store=new Store();
        }
        return store;
    }
}