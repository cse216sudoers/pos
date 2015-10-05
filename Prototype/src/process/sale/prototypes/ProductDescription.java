
package process.sale.prototypes;

public class ProductDescription {
    private int productCode;
    private float price;
    private String description; 
    public ProductDescription(String description, float price){
        this.price=price;
        this.description = description;
        this.productCode = DB.ProductDescription.getNextCode();
    }

    public ProductDescription(ProductDescription desc, int code, String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getCode() {
        return this.productCode;
    }
    
    public float getPrice(){
        return price;
    }
    
    public String getDescription(){
        return description;
    }
    
    @Override
    public String toString(){
        return "" + productCode + "\t" + description + "\t" + price + "\n";
    }
}
