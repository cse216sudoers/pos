
package process.sale.prototypes;

public class ProductCatalog {
    private ProductCatalog Cat;
    private ProductCatalog(){
        
    }
    public ProductCatalog getCatalog(){
        if (Cat==null){
            Cat=new ProductCatalog();
        }
        return Cat;
    }
}
