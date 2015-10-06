package process.sale.prototypes;
import java.util.ArrayList;

public class Sale {
    private float total;
    private float saleTotal;
    private ArrayList<SaleLineItem> lines;
    private ArrayList<Payment> payments; //for when we need to do returns
    private int id;
    
    public Sale(){
        total = 0;
        payments = new ArrayList<Payment>();
        lines = new ArrayList<SaleLineItem>();
    }
    
    public float getTotal(){
        return total;
    }
    public float getSaleTotal(){
        return saleTotal;
    }
    public void addPayment(Payment payment){
        payments.add(payment);
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
        boolean found = false;
        for(int i = 0; i < lines.size(); i++){
            if(lines.get(i).getProduct().getCode() == coupon.getProductCode()){
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
    
    public ArrayList<Payment> getPayments(){
        return payments;
    }
    
    public int getId(){
        return id;
    }
    
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
        String output = "";
        for(int i = 0; i < lines.size(); i++){
            output += lines.get(i).toString() + "\n";
        }
        return output;
    }
}