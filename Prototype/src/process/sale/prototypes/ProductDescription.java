
package process.sale.prototypes;

public class ProductDescription {
    private int productCode;
    private float price;
    public ProductDescription(int productCode, float price){
        this.productCode=productCode;
        this.price=price;
    }

    ProductDescription(ProductDescription desc, int code, String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    int getCode() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
