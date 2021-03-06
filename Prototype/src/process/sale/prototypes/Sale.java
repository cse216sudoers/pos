package process.sale.prototypes;
import java.util.ArrayList;
import javax.swing.JOptionPane;

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
    public void addItem(ProductDescription product, boolean affectQuantity){
        if(product.productLeft()){
            LineItem item = getLineItemByCode(product.getCode()); 
            if(true)
                ProductCatalog.getCatalog().findProductByCode(product.getCode()).decreaseQuantity();
            if(item == null){
                lines.add(new LineItem(product));
                subTotal += product.getPrice();
                return;
            }
            item.increaseQuantity();
            subTotal += product.getPrice();
        }
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
            JOptionPane.showMessageDialog (null, "Item not scanned: "+ coupon.getProductCode(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     *
     * @param product
     */
    public boolean removeItem(ProductDescription product, boolean affectQuantity){
        boolean found = false;
        for(int i = 0; i < lines.size(); i++){
            if(lines.get(i).getProduct().getCode() == product.getCode()){
                if(affectQuantity)
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
            JOptionPane.showMessageDialog (null, "Item not found", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
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

    @Override
    public String toString(){
        String output = "******Sale******* \nSale ID: " + id + "\n";
        for(int i = 0; i < lines.size(); i++){
            output += lines.get(i).toString();
        }
        return output;
    }
}