/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imgCripter;

import cripter.Descriptografar;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author mizereke
 */
public class imgVisualiza {
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    
    public byte[] getBytes() throws FileNotFoundException, IOException{
        FileReader fr = new FileReader(this.path);
        BufferedReader lerHeader = new BufferedReader(fr);
        
        lerHeader.readLine();
        
        Descriptografar dc = new Descriptografar();
        
        dc.descriptografar(lerHeader.readLine());
        Integer txtSize = Integer.parseInt(dc.getTexto());
        dc.limpaTexto();

        lerHeader.close();

        byte[] txtByte = new byte[txtSize];
        String strByte = "";
        FileReader fr1 = new FileReader(this.path);
        BufferedReader lerTxt = new BufferedReader(fr1);

        lerTxt.readLine();
        lerTxt.readLine();
        for(int i=0; i<txtSize; i++){
            Integer progress = ((i+1) * 100)/txtSize;
            
            if(strByte != null){
                dc.descriptografar(lerTxt.readLine());
                strByte = dc.getTexto();
                dc.limpaTexto();
                txtByte[i] = Byte.parseByte(strByte);
            }
        }
        lerTxt.close();

        return txtByte;
    }

}
