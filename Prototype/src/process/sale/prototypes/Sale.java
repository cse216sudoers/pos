package process.sale.prototypes;
import java.util.ArrayList;

/**
 *
 * @author Pikachu
 */
public class Sale extends Transaction{
    private float saleTotal;
    
    /**
     *
     */
    public Sale(){
        total = 0;
        id = SaleManager.getInstance().getNextId();
        payments = new ArrayList<>();
        lines = new ArrayList<>();
    }
    
    /**
     *
     * @return
     */
    public float getSaleTotal(){
        // return saleTotal;
        saleTotal = 0;
        for (LineItem line : lines)
            saleTotal += line.getPrice();
        return saleTotal;
    }
    
    /**
     *
     * @param product
     */
    public void addItem(ProductDescription product){
        if(ProductCatalog.getCatalog().findProductByCode(product.getCode()).getQuantity()!=0){
        LineItem item = getLineItemByCode(product.getCode());
        
            ProductCatalog.getCatalog().findProductByCode(product.getCode()).decreaseQuantity();
            System.out.println("Quantity"+product.getQuantity());
            if(item == null){
                lines.add(new LineItem(product));
                total += product.getPrice();
                return;
            }
            item.increaseQuantity();
            total += product.getPrice();
        }
        else
            System.out.println("Out of Stock");
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
                total-=coupon.apply();
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
                ProductCatalog.getCatalog().findProductByCode(product.getCode()).increaseQuantity();
                if(lines.get(i).getQuantity() == 1){
                    lines.remove(i);
                }
                else{
                    lines.get(i).decreaseQuantity();
                }
                total-=product.getPrice();
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
        float tax = TaxCalculator.getTax(getTotal());
        saleTotal = getTotal() + tax;
        
        // Set up ability to format print statements right so everything aligns
        int digits = ((Float) saleTotal).toString().length();
        String format = "%" + digits + ".2f";
        
        System.out.println("\n" + toString());
        System.out.printf("Subtotal: $" + format + "\n", getTotal());
        System.out.printf("Tax:      $" + format + "\n", tax);
        System.out.printf("Total:    $" + format + "\n", saleTotal);
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