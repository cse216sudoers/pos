/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author shua
 * All of the DB files should be pipe (|) separated. 
 */
public class DBClass {
    public static BufferedReader open(String path){
        BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(path));
            } catch (IOException e) {
                e.printStackTrace();
            }
        return br;
    }
    
    public static void insert(String path, String row){

    }

    public static void delete(String path, int id){

    }

    public static void update(String path, int id){

    }
    
    /**
     *
     * @param path Path to the file you want to open
     * @param column Name of the column you want to find
     * @return The position of the column in this table, -1 if not found
     */
    public static int getColumnNumber(String path, String column){
        BufferedReader table = open(path);
        int columnNumber = -1;
        column = column.toLowerCase();
        String line = null;
        
        try {
            line= table.readLine();
        } catch (IOException ex) {
            Logger.getLogger(DBClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String[] columns = line.split("|");
        
        for (int i = 0; i < columns.length; i++){
            if (columns[i].toLowerCase().equals(column)){
                columnNumber = i;
                break;
            }
        }
        
        return columnNumber;
        
    }
    
    /**
     *
     * @param path The file you want the nextID in
     * @return The next available ID in this table
     */
    public static int nextId(String path){
        int id = 0;
        String line;
        BufferedReader table = open(path);
    
        try {
            while ((line = table.readLine()) != null) {
                String[] columns = line.split("|");
                id = Integer.parseInt(columns[0]);
            }
        } catch (IOException ex) {
            Logger.getLogger(DBClass.class.getName()).log(Level.SEVERE, null, ex);
        }

        return id + 1;
    }
    
    /**
     *
     * @param path : Name of file you want to search 
     * @param column : Name of column you want to test against
     * @param value : Value of the column you want to compare to 
     * @return : An ArrayList full of the rows that match where values in column
     * are equal to value. Returns null if no rows matched. The zeroth row in
     * the return will be the headers
     */
    public static ArrayList<String> select (String path, String column, String value){
        int columnNumber = getColumnNumber(path, column);
        String line;
        ArrayList<String> rows = new ArrayList<String>();
        
        if (columnNumber == -1)
            return null;
        
        BufferedReader table = open(path);
    
        try {
            line = table.readLine();
            rows.add(line);
            while ((line = table.readLine()) != null) {
                String[] columns = line.split("|");
                String test = columns[columnNumber];
                if (test.equals(value)){
                    rows.add(line);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(DBClass.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        return rows;
    }
    

}
