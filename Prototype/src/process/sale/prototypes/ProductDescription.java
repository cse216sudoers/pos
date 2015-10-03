
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


    int getCode() {
        return this.productCode;
    }
}
