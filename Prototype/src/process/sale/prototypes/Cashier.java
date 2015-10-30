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
    private int id;
    
    public Cashier(String name, String username, String password){
        this.username = username;
        this.name = name;
        this.password = password;
        id = CashierManager.getInstance().getNextId();
    }
    public Cashier(String name, String username, String password, int uid){
        this.username = username;
        this.name = name;
        this.password = password;
        this.id= uid;
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
