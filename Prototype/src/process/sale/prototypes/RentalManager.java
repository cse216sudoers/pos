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
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Keeps track of all rentals
 * @author Pikachu
 */
public class RentalManager {
    private HashMap<Integer, Rental> rentals;
    private HashMap<Integer, Rental> suspendedRentals;
    private int nextId = 0;
    private static RentalManager instance;
    
    private RentalManager(){
        rentals = new HashMap(89);
        suspendedRentals = new HashMap(89);
        try{
            Scanner read = new Scanner(new File(Resources.getPath("Rentals.txt")));
            read.useDelimiter("\\|");
            String check="";
            while(read.hasNext()&&!check.contains("suspended")){
                check = read.next();
                if(check.contains("suspended")){
                    read.nextLine();
                    break;
                }
                int id = Integer.parseInt(check);
                Rental rentalI = new Rental(id);
                nextId++;
                String itemlist = read.next();
                Scanner split = new Scanner(itemlist);
                split.useDelimiter(">");
                
                while(split.hasNext()){
                    String line = split.next();
                    String[] pair = line.split("\\+");
                    int itemid = Integer.parseInt(pair[0]);
                    int quantity = Integer.parseInt(pair[1]);
                    int days = Integer.parseInt(pair[2]);
                    int coupon = Integer.parseInt(pair[3]);
                    rentalI.addItem(ProductCatalog.getCatalog().findProductByCode(itemid),days, false);
                    int q = 1;
                    while(q<quantity){
                        ProductCatalog.getCatalog().findProductByCode(itemid).increaseQuantity();
                        rentalI.addItem(ProductCatalog.getCatalog().findProductByCode(itemid),days, false);
                        q++;
                    }
                    if(coupon!=0)
                        rentalI.addCoupon(CouponCatalog.getCatalog().findCouponByCode(coupon));
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
                    rentalI.addPayment(pay);
                }
                String parsedate = read.next();
                //System.out.println(parsedate);
                String[] pair = parsedate.split(":");
                int year= Integer.parseInt(pair[0]);
                int month=Integer.parseInt(pair[1]);
                int day=Integer.parseInt(pair[2]);
                GregorianCalendar date = new GregorianCalendar(year,month-1,day);
                rentalI.startDate=date;
                read.nextLine();
                //Rental rental = new Rental();
                rentals.put(rentalI.getId(),rentalI);
            }
            while(read.hasNext()){
                nextId++;
                int id = Integer.parseInt(read.next());
                Rental rentalI = new Rental(id);
                String itemlist = read.next();
                Scanner split = new Scanner(itemlist);
                split.useDelimiter(">");
                while(split.hasNext()){
                    String[] pair = split.next().split("\\+");
                    int itemid = Integer.parseInt(pair[0]);
                    int quantity = Integer.parseInt(pair[1]);
                    int days = Integer.parseInt(pair[2]);
                    int coupon = Integer.parseInt(pair[3]);
                    rentalI.addItem(ProductCatalog.getCatalog().findProductByCode(itemid),days, false);
                    int q = 1;
                    while(q<quantity){
                        ProductCatalog.getCatalog().findProductByCode(itemid).increaseQuantity();
                        rentalI.addItem(ProductCatalog.getCatalog().findProductByCode(itemid),days, false);
                        q++;
                    }
                    if(coupon!=0)
                        rentalI.addCoupon(CouponCatalog.getCatalog().findCouponByCode(coupon));
                }
                suspendedRentals.put(rentalI.getId(),rentalI);
                read.nextLine();
            }
        }
        catch(FileNotFoundException | NumberFormatException e){
            //System.out.println(e.toString());
        }
    }
    
    /**
     * Get singleton
     * @return singleton
     */
    public static synchronized RentalManager getInstance(){
        if (instance == null){
            instance = new RentalManager();
        }
        return instance;
    }
    
    /**
     *
     * @param id unique id
     * @return rental
     */
    public Rental getRentalById(int id){
        return rentals.get(id);
    }
    
    /**
     *
     * @param id unique id
     * @return suspended rental
     */
    public Rental getSuspendedRentalById(int id){
        return suspendedRentals.get(id);
    }
    
    /**
     * return rentals
     * @return
     */
    public HashMap<Integer, Rental> getRentals(){
        return rentals;
    }
    
    /**
     * return suspended rentals
     * @return  suspended rentals
     */
    public HashMap<Integer, Rental> getSuspendedRentals(){
        return suspendedRentals;
    }
    
    /**
     * add rental
     * @param rental
     */
    public void addRental(Rental rental){
        rentals.put(rental.getId(), rental);
        updateFile();
        ProductCatalog.getCatalog().updateFile();
    } 
    
    /**
     * add suspended rental
     * @param rental suspended rental
     */
    public void addSuspendedRental(Rental rental){
        suspendedRentals.put(rental.getId(), rental);
        updateFile();
        ProductCatalog.getCatalog().updateFile();
    }
    /**
     * get next unique id
     * @return next unique id
     */
    public int getNextId(){
        return ++nextId;
    }
    public synchronized void updateFile(){
        File store = new File(Resources.getPath("Rentals.txt"));
        try{
            FileOutputStream fos = new FileOutputStream(store);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            for (Rental rental : rentals.values()) {
                // System.out.println(item.getCode()+"|"+item.getDescription()+"|"+item.getPrice()+"|"+item.getQuantity()+"|"+item.getIsRentable()+"|"+item.getRentalPrice()+"|\n");
                bw.write(rental.id+"|");
                for(LineItem item: rental.getLines()){
                    RentalLineItem ritem = (RentalLineItem)item;
                    if(ritem.getCoupon()==null)
                        bw.write(ritem.getProduct().getCode()+"+"+ritem.getQuantity()+"+"+ritem.getDaysRented()+"+"+0+">");
                    else
                        bw.write(item.getProduct().getCode()+"+"+item.getQuantity()+"+"+ritem.getDaysRented()+"+"+item.getCoupon()+">");
                }
                bw.write("|");
                for(Payment pay: rental.getPayments()){
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
                SimpleDateFormat ft =  new SimpleDateFormat ("yyyy:MM:dd");
                //System.out.println(ft.format(rental.startDate.getTime()));
                bw.write(ft.format(rental.startDate.getTime())+"|");
                bw.newLine();
            }
            bw.write("suspended|");
            bw.newLine();
            for (Rental rental : suspendedRentals.values()) {
                // System.out.println(item.getCode()+"|"+item.getDescription()+"|"+item.getPrice()+"|"+item.getQuantity()+"|"+item.getIsRentable()+"|"+item.getRentalPrice()+"|\n");
                bw.write(rental.id+"|");
                for(LineItem item: rental.getLines()){
                    RentalLineItem ritem = (RentalLineItem)item;
                    if(ritem.getCoupon()==null)
                        bw.write(ritem.getProduct().getCode()+"+"+ritem.getQuantity()+"+"+ritem.getDaysRented()+"+"+0+">");
                    else
                        bw.write(item.getProduct().getCode()+"+"+item.getQuantity()+"+"+ritem.getDaysRented()+"+"+item.getCoupon()+">");
                }
                bw.write("|");
                bw.newLine();
            }
            bw.close();
        } catch(Exception e){
            //System.out.println(e.toString());
        }
    }
}
