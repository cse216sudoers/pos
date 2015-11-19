package process.sale.prototypes;
import java.util.ArrayList;

/**
 *
 * @author Pikachu
 */
public class Sale extends Transaction{    
    /**
     *
     */
    public Sale(){
        total = 0;
        subTotal= 0;
        id = SaleManager.getInstance().getNextId();
        payments = new ArrayList<>();
        lines = new ArrayList<>();
    }
    public Sale(int id){
        total = 0;
        subTotal= 0;
        this.id = id;
        payments = new ArrayList<>();
        lines = new ArrayList<>();
    }
    
    /**
     *
     * @param product
     */
    public void addItem(ProductDescription product){
        if(product.productLeft()){
            LineItem item = getLineItemByCode(product.getCode()); 
            ProductCatalog.getCatalog().findProductByCode(product.getCode()).decreaseQuantity();
            if(item == null){
                lines.add(new LineItem(product));
                subTotal += product.getPrice();
                return;
            }
            item.increaseQuantity();
            subTotal += product.getPrice();
        }
        else
            ;//System.out.println("Out of Stock");
    }
    
    /**
     *
     * @param coupon
     */
    public void addCoupon(Coupon coupon){
        boolean found = false;
        for(int i = 0; i < lines.size(); i++){
            if(lines.get(i).getProduct().getCode() == coupon.getProductCode()){
                lines.get(i).setCoupon(coupon);
                subTotal-=coupon.apply();
                found = true;
                break;
            }
        }
        if(!found){
            System.out.println("Invalid coupon. Item not scanned: " + coupon.getCode());
        }
    }
    
    /**
     *
     * @param product
     */
    @Override
    public void removeItem(ProductDescription product){
        boolean found = false;
        for(int i = 0; i < lines.size(); i++){
            if(lines.get(i).getProduct().getCode() == product.getCode()){
                product.increaseQuantity();
                if(lines.get(i).getQuantity() == 1){
                    lines.remove(i);
                }
                else{
                    lines.get(i).decreaseQuantity();
                }
                subTotal-=product.getPrice();
                found = true;
                break;
            }
        }
        if(!found){//item not in Sale
            System.out.println("item not found");
        }
    }
    
    /**
     *
     * @param code
     * @return
     */
    public LineItem getLineItemByCode(int code){
        for(int i = 0; i< lines.size(); i++){
            if(lines.get(i).getProduct().getCode() == code)
                return lines.get(i);
        }
        return null;
    }
    
    /**
     *
     */
    @Override
    public void printTotals() {
        // Calculate tax and total
        float tax = TaxCalculator.getTax(subTotal);
        total = subTotal + tax;
        
        // Set up ability to format print statements right so everything aligns
        int digits = ((Float)total).toString().length();
        String format = "%" + digits + ".2f";
        
        System.out.println("\n" + toString());
        System.out.printf("Subtotal: $" + format + "\n", subTotal);
        System.out.printf("Tax:      $" + format + "\n", tax);
        System.out.printf("Total:    $" + format + "\n", total);
    }
    
    @Override
    public String toString(){
        String output = "******Sale******* \nSale ID: " + id + "\n";
        for(int i = 0; i < lines.size(); i++){
            output += lines.get(i).toString();
        }
        return output;
    }
}