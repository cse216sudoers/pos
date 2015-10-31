
package process.sale.prototypes;

public class ProductDescription {
    private int productCode;
    private float price;
    private float rentalPrice;
    private String description;
    private static int nextCode = 1;
    
    public ProductDescription(String description, float price){
        this.price=price;
        this.description = description;
        // this.productCode = DB.ProductDescription.getNextCode();
        this.productCode = getNextCode();
        // update(); - Caused Stack Overflow due to infinite looping
    }
    public ProductDescription(String description, float price,int ID){
        this.price=price;
        this.description = description;
        // this.productCode = DB.ProductDescription.getNextCode();
        this.productCode = ID;
        // update(); - Caused Stack Overflow due to infinite looping
    }
    private void update(){
        ProductCatalog cat=ProductCatalog.getCatalog();
        cat.addItem(this);
    }
    public int getCode() {
        return this.productCode;
    }
    
    private int getNextCode() {
        return nextCode++;
    }

    public float getPrice(){
        return price;
    }
    
    public float getRentalPrice() {
        return rentalPrice;
    }
    
    public String getDescription(){
        return description;
    }
    
    @Override
    public String toString(){
        return String.format("%2d %-15.15s\t%7.2f", productCode, description, price);
    }
}
