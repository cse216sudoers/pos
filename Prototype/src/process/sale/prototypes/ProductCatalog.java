
package process.sale.prototypes;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
public class ProductCatalog {
    private static ProductCatalog cat;
    private ArrayList<ProductDescription> items;
    
    private ProductCatalog(){
        items = new ArrayList<ProductDescription>();
        try{
            Scanner read = new Scanner(new File("Product_description.txt"));
            read.useDelimiter("\\|");
            while(read.hasNext()){
                int id = Integer.parseInt(read.next());
                String desc = read.next();
                float price = Float.parseFloat(read.next());
                items.add(new ProductDescription(desc,price,id));
                read.nextLine();
            }
        }
        catch(Exception e){
            System.out.println(e.toString());
        }
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