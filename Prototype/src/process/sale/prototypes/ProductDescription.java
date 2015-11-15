package process.sale.prototypes;

/**
 *
 * @author Pikachu
 */
public class ProductDescription {
    private int productCode;
    private float price;
    private float rentalPrice;
    private boolean isRentable;
    private int quantity;
    private String description;
    private static int nextCode = 1;
    
    /**
     *
     * @param description
     * @param price
     * @param quantity
     * @param isRentable
     * @param rentalPrice
     */
    public ProductDescription(String description, float price, int quantity, boolean isRentable, float rentalPrice){
        this.price=price;
        this.description = description;
        this.productCode = getNextCode();
        this.isRentable = isRentable;
        this.quantity = quantity;
        this.rentalPrice = rentalPrice;
        // update(); - Caused Stack Overflow due to infinite looping
    }
    /**
     *
     * @param description
     * @param price
     * @param ID
     * @param quantity
     * @param isRentable
     * @param rentalPrice
     */
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
    /**
     *
     * @return
     */
    public int getCode() {
        return this.productCode;
    }
    
    /**
     *
     */
    public void increaseQuantity(){
        quantity++;
    }
    /**
     *
     */
    public void decreaseQuantity(){
        quantity--;
    }
    /**
     *
     * @return
     */
    public int getQuantity(){
        return quantity;
    }
    
    private int getNextCode() {
        return nextCode++;
    }

    /**
     *
     * @return
     */
    public float getPrice(){
        return price;
    }
    
    /**
     *
     * @return
     */
    public float getRentalPrice() {
        return rentalPrice;
    }
    
    /**
     *
     * @return
     */
    public String getDescription(){
        return description;
    }
    
    /**
     *
     * @return
     */
    public boolean getIsRentable(){
        return isRentable;
    }
    
    /**
     *
     * @param newPrice
     */
    public void setPrice(float newPrice) {
        price = newPrice;
    }
    
    @Override
    public String toString(){
        return String.format("%2d %-15.15s\t%7.2f", productCode, description, price);
    }
}
