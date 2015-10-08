/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package process.sale.prototypes;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Pikachu
 */
public class CashierManager {
    private ArrayList<Cashier> cashiers;
    private static CashierManager instance;
    private int nextId = 0;
    
    private CashierManager(){
        cashiers = new ArrayList<Cashier>();
        try{
            Scanner read = new Scanner(new File("Users.txt"));
            read.useDelimiter("\\|");
            while(read.hasNext()){
                String name = read.next();
                int uid = Integer.parseInt(read.next());
                int access = Integer.parseInt(read.next());
                String username = read.next();
                String password = read.next();
                cashiers.add(new Cashier( name, username, password,uid));
                read.nextLine();
            }
        }
        catch(Exception e){
            //System.out.println(e.toString());
        }
    }
    
    public static synchronized CashierManager getInstance(){
        if(instance == null){
            instance = new CashierManager();
        }
        return instance;
    }
    
    public CashierManager(ArrayList<Cashier> cashiers){
        this.cashiers = cashiers;
    }
    
    public void addCashier(Cashier cashier){
        if(getCashierById(cashier.getId()) != null){  //id already exists, get new id by using other constructor
            cashier = new Cashier(cashier.getName(), cashier.getUsername(), cashier.getPassword());
        }
        cashiers.add(cashier);
    }
    
    public void removeCashier(Cashier cashier){
        cashiers.remove(cashier);
    }
    
    public ArrayList<Cashier> getCashiers(){
        return cashiers;
    }
    public Cashier getCashierByUsername(String username){
        for(int i = 0; i < cashiers.size(); i++){
            if(cashiers.get(i).getUsername().equals(username))
                return cashiers.get(i);
        }
        return null;
    }
    public Cashier getCashierById(int id){
        for(int i = 0; i < cashiers.size(); i++){
            if(cashiers.get(i).getId() == id)
                return cashiers.get(i);
        }
        return null;
    }
    public int getNextId(){
        return ++nextId;
    }
}
