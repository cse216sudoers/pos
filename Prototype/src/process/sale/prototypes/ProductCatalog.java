
package process.sale.prototypes;

import java.util.ArrayList;

public class ProductCatalog {
    private static ProductCatalog cat;
    private ArrayList<ProductDescription> items;
    
    private ProductCatalog(){
        items = new ArrayList<ProductDescription>();
        items.add(new ProductDescription("Pikachu Plushie", 24.95f));
        items.add(new ProductDescription("Potato Bread", 5.99f));
        items.add(new ProductDescription("iPhone 6S Plus", 299.99f));
        items.add(new ProductDescription("Pickles", 0.99f));
        items.add(new ProductDescription("Migraine Medication", 29.97f));
    }
    
    public static synchronized ProductCatalog getCatalog(){
        if (cat==null){
            cat=new ProductCatalog();
        }
        return cat;
    }
    public void addItem(ProductDescription desc, int code, String name){
        boolean isIn=false;
        for (int i=0;i<items.size();i++){
            if (code==items.get(i).getCode()){
                isIn=true;
            }
        }
        if (!isIn){
            items.add(new ProductDescription(desc, code, name));//NEEDS TO BE UPDATED WHEN CONSTRUCTOR IS MADE
        }
    }
    
    public ProductDescription findProductByCode(int code){
        for (int i=0;i<items.size();i++){
            if (code==items.get(i).getCode()){
                return items.get(i);
            }
        }
        return null;
    }
}
