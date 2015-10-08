/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package process.sale.prototypes;

import java.util.ArrayList;

/**
 *
 * @author Pikachu
 */
public class RegisterManager {
    private ArrayList<Register> registers;
    private static RegisterManager instance;
    private int nextId = 0;
    
    private RegisterManager(){
        registers = new ArrayList<Register>();
    }
    
    public static synchronized RegisterManager getInstance(){
        if(instance == null){
            instance = new RegisterManager();
        }
        return instance;
    }
    
    public void addRegister(Register register){
        registers.add(register);
    }
    
    public void removeRegister(Register register){
        registers.remove(register);
    }
    
    public ArrayList<Register> getRegisters(){
        return registers;
    }
    public Register getRegisterById(int id){
        for(int i = 0; i < registers.size(); i++){
            if(registers.get(i).getId() == id)
                return registers.get(i);
        }
        return null;
    }
    public int getNextId(){
        return ++nextId;
    }
}
