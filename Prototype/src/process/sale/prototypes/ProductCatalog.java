
package process.sale.prototypes;

public class ProductCatalog {
    private ProductCatalog Cat;
    private ProductDescription[] items;
    private int itemCount=0;
    private ProductCatalog(){
        
    }
    public ProductCatalog getCatalog(){
        if (Cat==null){
            Cat=new ProductCatalog();
        }
        return Cat;
    }
    public void addItem(ProductDescription desc, int code, String name){
        boolean isIn=false;
        for (int i=0;i<itemCount;i++){
            if (code==items[i].getCode()){
                isIn=true;
            }
        }
        if (!isIn){
            items[itemCount]=new ProductDescription(desc, code, name);//NEEDS TO BE UPDATED WHEN CONSTRUCTOR IS MADE
        }
    }
}
