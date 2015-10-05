package process.sale.prototypes;
import java.util.ArrayList;

public class Sale {
    private float total;
    private ArrayList<SaleLineItem> lines;
    private Payment payment; //for when we need to do returns
    private int id;
    
    public Sale(){
        total = 0;
        lines = new ArrayList<SaleLineItem>();
    }
    
    public float getTotal(){
        return total;
    }
    
    public ArrayList<SaleLineItem> getLines(){
        return lines;
    }
    
    public void addItem(ProductDescription product){
        boolean found = false;
        for(int i = 0; i < lines.size(); i++){
            if(lines.get(i).getProduct().getCode() == product.getCode()){
                lines.get(i).increaseQuantity();
                found = true;
                break;
            }
        }
        if(!found){
            lines.add(new SaleLineItem(product));
        }
        total += product.getPrice();
    }
    
    public void addCoupon(Coupon coupon){
        //System.out.println("Add coupon");
        boolean found = false;
        for(int i = 0; i < lines.size(); i++){
            if(lines.get(i).getProduct().getCode() == coupon.getCode()){
                lines.get(i).setCoupon(coupon);
                total-=coupon.getAmount();
                found = true;
                break;
            }
        }
        if(!found){
            System.out.println("Invalid coupon. Item not scanned: " + coupon.getCode());
        }
    }
    
    public void removeItem(ProductDescription product){
        boolean found = false;
        for(int i = 0; i < lines.size(); i++){
            if(lines.get(i).getProduct().getCode() == product.getCode()){
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
        if(!found){//item not in sale
            System.out.println("item not found");
        }
    }
    
    public Payment getPayment(){
        return payment;
    }
    
    public void setPayment(Payment payment){
        this.payment = payment;
    }
    
    public int getId(){
        return id;
    }
    
    @Override
    public String toString(){
        String output = "";
        for(int i = 0; i < lines.size(); i++){
            output += lines.get(i).toString() + "\n";
        }
        return output;
    }
}