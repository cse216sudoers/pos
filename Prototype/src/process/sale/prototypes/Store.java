
package process.sale.prototypes;

public class Store {
    private String name;
    private int storeCode;
    private Store store;
    private Store(){
        
    }
    public Store getStore(){
        if(store==null){
            store=new Store();
        }
        return store;
    }
}
