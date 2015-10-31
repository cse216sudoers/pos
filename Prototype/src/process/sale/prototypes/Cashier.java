/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package process.sale.prototypes;

/**
 *
 * @author Pikachu
 */
public class Cashier {
    private String username;
    private String name;
    private String password;
    public static enum Access{
        Cashier, Manager, Admin
    }
    private Access access;
    private int id;
    
    public Cashier(String name, String username, String password, Access access){
        this.username = username;
        this.name = name;
        this.password = password;
        id = CashierManager.getInstance().getNextId();
        this.access = access;
    }
    public Cashier(String name, String username, String password, int uid, Access access){
        this.username = username;
        this.name = name;
        this.password = password;
        this.id= uid;
        this.access = access;
    }
    
    public Access getAccess(){
        return access;
    }
    
    public void setAccess(Access access){
        this.access = access;
    }
    
    public String getUsername(){
        return username;
    }
    
    public String getName(){
        return name;
    }
    
    public String getPassword(){
        return password;
    }
    
    public void setUsername(String username){
        this.username = username;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public int getId(){
        return id;
    }
}
