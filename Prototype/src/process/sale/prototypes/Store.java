
package process.sale.prototypes;

public class Store {
    private String name;
    private int storeCode;
    private static Store store;
    private Store(){
        
    }
    public static synchronized Store getStore(){
        if(store==null){
            store=new Store();
        }
        return store;
    }
    
    public static void processSale(){
        
    }
    
    public static void main(String[] args){
        Store mainStore = Store.getStore();
        
        mainStore.processSale();
    }
}
