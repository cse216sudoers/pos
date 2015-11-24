/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.sale.prototypes;

import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author noahreifsnyder
 */
public class TestTest {
    public void Testing() throws IOException{
        InputStream is = getClass().getResourceAsStream("Users.txt");
        int data=is.read();
        while (data != -1){
            while ((char)data != '\n' && data !=-1){
            System.out.print((char)data);
            data=is.read();
            }
            System.out.println();
            data=is.read();
        }
    }
}
