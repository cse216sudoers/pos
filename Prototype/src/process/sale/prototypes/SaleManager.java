/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package process.sale.prototypes;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Pikachu
 */
public class SaleManager {
    private HashMap<Integer, Sale> sales;
    private HashMap<Integer, Sale> suspendedSales;
    private int nextId = 0;
    private static SaleManager instance;
    
    private SaleManager(){
        sales = new HashMap(89);
        suspendedSales = new HashMap(89);
        try{
            Scanner read = new Scanner(new File("Sales.txt"));
            read.useDelimiter("\\|");
            String check="";
            while(read.hasNext()&&!check.contains("suspended")){
                check = read.next();
                if(check.contains("suspended")){
                    read.nextLine();
                    break;
                }
                int id = Integer.parseInt(check);
                Sale saleI = new Sale(id);
                String itemlist = read.next();
                Scanner split = new Scanner(itemlist);
                split.useDelimiter(">");
                
                while(split.hasNext()){
                    String line = split.next();
                    String[] pair = line.split("\\+");
                    int itemid = Integer.parseInt(pair[0]);
                    int quantity = Integer.parseInt(pair[1]);
                    int coupon = Integer.parseInt(pair[2]);
                    saleI.addItem(ProductCatalog.getCatalog().findProductByCode(itemid), false);
                    int q = 1;
                    while(q<quantity){
                        ProductCatalog.getCatalog().findProductByCode(itemid).increaseQuantity();
                        saleI.addItem(ProductCatalog.getCatalog().findProductByCode(itemid), false);
                        q++;
                    }
                    if(coupon!=0)
                        saleI.addCoupon(CouponCatalog.getCatalog().findCouponByCode(coupon));
                }
                String payment = read.next();
                Scanner splitpay = new Scanner(payment);
                splitpay.useDelimiter(">");
                while(splitpay.hasNext()){
                    String[] pair = splitpay.next().split("\\+");
                    float amount = Float.parseFloat(pair[0]);
                    String type = pair[1];
                    Payment pay;
                    if(type.contains("CASH")){
                        float cash = Float.parseFloat(pair[2]);
                        pay = new CashPayment(cash,amount);
                    }
                    else if(type.contains("DEBIT")){
                        String cardnum = pair[2];
                        int pin = Integer.parseInt(pair[3]);
                        pay = new DebitPayment(cardnum,pin,amount);
                    }
                    else if(type.contains("CREDIT")){
                        String cardnum = pair[2];
                        String sec = pair[3];
                        pay = new CreditPayment(cardnum,sec,amount);
                    }
                    else
                        break;
                    saleI.addPayment(pay);
                }
                read.nextLine();
                //Sale sale = new Sale();
                sales.put(saleI.getId(),saleI);
            }
            while(read.hasNext()){
                int id = Integer.parseInt(read.next());
                Sale saleI = new Sale(id);
                String itemlist = read.next();
                Scanner split = new Scanner(itemlist);
                split.useDelimiter(">");
                while(split.hasNext()){
                    String[] pair = split.next().split("\\+");
                    int itemid = Integer.parseInt(pair[0]);
                    int quantity = Integer.parseInt(pair[1]);
                    int coupon = Integer.parseInt(pair[2]);
                    saleI.addItem(ProductCatalog.getCatalog().findProductByCode(itemid), false);
                    int q = 1;
                    while(q<quantity){
                        ProductCatalog.getCatalog().findProductByCode(itemid).increaseQuantity();
                        saleI.addItem(ProductCatalog.getCatalog().findProductByCode(itemid), false);
                        q++;
                    }
                    if(coupon!=0)
                        saleI.addCoupon(CouponCatalog.getCatalog().findCouponByCode(coupon));
                }
                suspendedSales.put(saleI.getId(),saleI);
                read.nextLine();
            }
        }
        catch(FileNotFoundException | NumberFormatException e){
            System.out.println(e.toString());
        }
    }
    
    /**
     *
     * @return
     */
    public static synchronized SaleManager getInstance(){
        if (instance == null){
            instance = new SaleManager();
        }
        return instance;
    }
    
    /**
     *
     * @param id
     * @return
     */
    public Sale getSaleById(int id){
        return sales.get(id);
    }
    
    /**
     *
     * @param id
     * @return
     */
    public Sale getSuspendedSaleById(int id){
        return suspendedSales.get(id);
    }
    
    /**
     *
     * @return
     */
    public HashMap<Integer, Sale> getSales(){
        return sales;
    }
    
    /**
     *
     * @return
     */
    public HashMap<Integer, Sale> getSuspendedSales(){
        return suspendedSales;
    }
    
    /**
     *
     * @param sale
     */
    public void addSale(Sale sale){
        sales.put(sale.getId(), sale);
        updateFile();
    } 
    
    /**
     *
     * @param sale
     */
    public void addSuspendedSale(Sale sale){
        suspendedSales.put(sale.getId(), sale);
        updateFile();
    }
    /**
     *
     * @return
     */
    public int getNextId(){
        return ++nextId;
    }
    public synchronized void updateFile(){
        File store = new File("Sales.txt");
        try{
            FileOutputStream fos = new FileOutputStream(store);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            for (Sale sale : sales.values()) {
                // System.out.println(item.getCode()+"|"+item.getDescription()+"|"+item.getPrice()+"|"+item.getQuantity()+"|"+item.getIsRentable()+"|"+item.getRentalPrice()+"|\n");
                bw.write(sale.id+"|");
                for(LineItem item: sale.getLines()){
                    
                    if(item.getCoupon()==null)
                        bw.write(item.getProduct().getCode()+"+"+item.getQuantity()+"+"+0+">");
                    else
                        bw.write(item.getProduct().getCode()+"+"+item.getQuantity()+"+"+item.getCoupon()+">");
                }
                bw.write("|");
                for(Payment pay: sale.getPayments()){
                    bw.write(pay.getAmount()+"+"+pay.getType());
                    if(pay.getType()==Payment.PaymentType.CASH){
                        bw.write("+"+((CashPayment)pay).getCashGiven());
                    }
                    else if(pay.getType()==Payment.PaymentType.DEBIT){
                        bw.write("+"+((DebitPayment)pay).getCardNum()+"+"+((DebitPayment)pay).getPin());
                    }
                    else if(pay.getType()==Payment.PaymentType.CREDIT){
                        bw.write("+"+((CreditPayment)pay).getCardNum()+"+"+((CreditPayment)pay).getSecurityCode());
                    }
                }
                bw.write("|");
                bw.newLine();
            }
            bw.write("suspended|");
            bw.newLine();
            for (Sale sale : suspendedSales.values()) {
                // System.out.println(item.getCode()+"|"+item.getDescription()+"|"+item.getPrice()+"|"+item.getQuantity()+"|"+item.getIsRentable()+"|"+item.getRentalPrice()+"|\n");
                bw.write(sale.id+"|");
                for(LineItem item: sale.getLines()){
                    if(item.getCoupon()==null)
                        bw.write(item.getProduct().getCode()+"+"+item.getQuantity()+"+"+0+">");
                    else
                        bw.write(item.getProduct().getCode()+"+"+item.getQuantity()+"+"+item.getCoupon()+">");
                }
                bw.write("|");
                bw.newLine();
            }
            bw.close();
        } catch(Exception e){
            System.out.println(e.toString());
        }
    }
}
