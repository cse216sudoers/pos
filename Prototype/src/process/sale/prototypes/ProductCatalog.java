
package process.sale.prototypes;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
public class ProductCatalog {
    private static ProductCatalog cat;
    private ArrayList<ProductDescription> items;
    
    private ProductCatalog(){
        items = new ArrayList<>();
        try{
            Scanner read = new Scanner(new File("Product_description.txt"));
            read.useDelimiter("\\|");
            while(read.hasNext()){
//                while(read.hasNext()){
//                System.out.println(read.nextLine());
//                }
                int id = Integer.parseInt(read.next());
                String desc = read.next();
                float price = Float.parseFloat(read.next());
                int quantity = Integer.parseInt(read.next());
                int rentable = Integer.parseInt(read.next());
                boolean isRentable;
                if(rentable == 0)
                    isRentable = false;
                else
                    isRentable = true;
                float rentalPrice = read.nextFloat();
                ProductDescription product = new ProductDescription(desc,price,id, quantity, isRentable, rentalPrice);
                items.add(product);
                read.nextLine();
            }
        }
        catch(FileNotFoundException | NumberFormatException e){
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