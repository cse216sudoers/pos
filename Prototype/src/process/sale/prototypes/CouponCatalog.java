/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.sale.prototypes;

/**
 *
 * @author Ryan
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class CouponCatalog {
    private static CouponCatalog cat;
    private ArrayList<Coupon> coupons;
    
    private CouponCatalog(){
        coupons = new ArrayList<>();
        try{
            Scanner read = new Scanner(new File(Resources.getPath("Coupons.txt")));
            read.useDelimiter("\\|");
            while(read.hasNext()){
                int cid = Integer.parseInt(read.next());
                String fullamount = read.next();
                boolean percent = false;
                if (fullamount.contains("%")){
                    fullamount = fullamount.substring(0, fullamount.length()-1);
                    percent = true;
                }
                float amount = Float.parseFloat(fullamount);
                int pid = Integer.parseInt(read.next());
                Coupon coup = new Coupon(cid,pid,amount,percent);
                coupons.add(coup);
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
    public static synchronized CouponCatalog getCatalog(){
        if (cat==null){
            cat=new CouponCatalog();
        }
        return cat;
    }
    /**
     *
     * @param desc
     */
    public void addItem(Coupon desc){
        coupons.add(desc);
    }

    /**
     *
     * @param code
     * @return
     */
    public Coupon findCouponByCode(int code){
        for (Coupon coupon : coupons) {
            if (code == coupon.getCode()) {
                return coupon;
            }
        }
        return null;
    }
}
