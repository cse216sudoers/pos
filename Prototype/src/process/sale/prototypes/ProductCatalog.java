
package process.sale.prototypes;

import java.util.ArrayList;

public class ProductCatalog {
    private static ProductCatalog cat;
    private ArrayList<ProductDescription> items;
    
    private ProductCatalog(){
        items = new ArrayList<ProductDescription>();
        addItem(new ProductDescription("Pikachu Plushie", 24.95f));//ADDED THROUGH METHODS TO ARRAYLIST
        addItem(new ProductDescription("Potato Bread", 5.99f));//Allows for future additions
        addItem(new ProductDescription("iPhone 6S Plus", 299.99f));
        addItem(new ProductDescription("Pickles", 0.99f));
        addItem(new ProductDescription("Migraine Medication", 29.97f));
    }
    
    public static synchronized ProductCatalog getCatalog(){
        if (cat==null){
            cat=new ProductCatalog();
        }
        return cat;
    }
    public void addItem(ProductDescription desc){
        items.add(desc);
    }
    
    public ProductDescription findProductByCode(int code){
        for (ProductDescription item : items) {
            if (code == item.getCode()) {
                return item;
            }
        }
        return null;
    }
}
