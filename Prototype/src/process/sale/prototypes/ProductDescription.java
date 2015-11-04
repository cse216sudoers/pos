package process.sale.prototypes;

public class ProductDescription {
    private int productCode;
    private float price;
    private float rentalPrice;
    private boolean isRentable;
    private int quantity;
    private String description;
    private static int nextCode = 1;
    
    public ProductDescription(String description, float price, int quantity, boolean isRentable, float rentalPrice){
        this.price=price;
        this.description = description;
        this.productCode = getNextCode();
        this.isRentable = isRentable;
        this.quantity = quantity;
        this.rentalPrice = rentalPrice;
        // update(); - Caused Stack Overflow due to infinite looping
    }
    public ProductDescription(String description, float price,int ID, int quantity, boolean isRentable, float rentalPrice){
        this.price=price;
        this.description = description;
        this.productCode = ID;
        this.isRentable = isRentable;
        this.quantity = quantity;
        this.rentalPrice = rentalPrice;
        // update(); - Caused Stack Overflow due to infinite looping
    }
    private void update(){
        ProductCatalog cat=ProductCatalog.getCatalog();
        cat.addItem(this);
    }
    public int getCode() {
        return this.productCode;
    }
    
    public void increaseQuantity(){
        quantity++;
    }
    public void decreaseQuantity(){
        quantity--;
    }
    public int getQuantity(){
        return quantity;
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
    
    public boolean getIsRentable(){
        return isRentable;
    }
    
    @Override
    public String toString(){
        return String.format("%2d %-15.15s\t%7.2f", productCode, description, price);
    }
}
