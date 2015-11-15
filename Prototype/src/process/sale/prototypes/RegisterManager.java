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
        registers = new ArrayList<>();
    }
    
    /**
     * Get singleton
     * @return singleton
     */
    public static synchronized RegisterManager getInstance(){
        if(instance == null){
            instance = new RegisterManager();
        }
        return instance;
    }
    
    /**
     * add a new register
     * @param register
     */
    public void addRegister(Register register){
        registers.add(register);
    }
    
    /**
     * remove a register
     * @param register
     */
    public void removeRegister(Register register){
        registers.remove(register);
    }
    
    /**
     * get all registers in store
     * @return all registers
     */
    public ArrayList<Register> getRegisters(){
        return registers;
    }
    /**
     * Get register by ID
     * @param id unique id
     * @return register
     */
    public Register getRegisterById(int id){
        for(int i = 0; i < registers.size(); i++){
            if(registers.get(i).getId() == id)
                return registers.get(i);
        }
        return null;
    }
    /**
     *
     * @return unique id
     */
    public int getNextId(){
        return ++nextId;
    }
}
