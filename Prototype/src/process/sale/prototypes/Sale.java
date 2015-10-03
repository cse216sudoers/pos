
package process.sale.prototypes;

import java.util.ArrayList;

public class Sale {
    private float total;
    private ArrayList<SaleLineItem> lines;
    
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
    public void addItem(Item item){
        boolean found = false;
        for(int i = 0; i < lines.size(); i++){
            if(lines.get(i).getItem().getProductCode().equals(item.getProductCode())){
                lines.get(i).increaseQuantity();
                found = true;
                break;
            }
        }
        if(!found){
            lines.add(new SaleLineItem(item));
        }
    }
}
