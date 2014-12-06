/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imgCripter;

import cripter.Descriptografar;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.FrmDecodifica;

/**
 *
 * @author Bruno Haveroth
 */
public class ImgDecode implements Runnable{
    private String path;
    private String pathEnd;
    private FrmDecodifica frmD;
    private Integer pos;

    public FrmDecodifica getFrmD() {
        return frmD;
    }

    public void setFrmD(FrmDecodifica frmD) {
        this.frmD = frmD;
    }

    public Integer getPos() {
        return pos;
    }

    public void setPos(Integer pos) {
        this.pos = pos;
    }

    public String getPathEnd() {
        return pathEnd;
    }

    public void setPathEnd(String pathEnd) {
        this.pathEnd = pathEnd;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    
    public void decode() throws IOException{
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
            frmD.alterStatus("Descriptografando "+progress+"%", this.pos);
            
            if(strByte != null){
                dc.descriptografar(lerTxt.readLine());
                strByte = dc.getTexto();
                dc.limpaTexto();
                txtByte[i] = Byte.parseByte(strByte);
            }
            //System.out.println("" + i + " de "+ txtSize);
        }
        lerTxt.close();

        String caminhoSalvar = this.pathEnd;  
        File file = new File(caminhoSalvar);  

        BufferedOutputStream boss = new BufferedOutputStream(new FileOutputStream(file));

        boss.write(txtByte);
        boss.close();      
    }
    
    public boolean verificaChave(String key) throws IOException{
        FileReader fr = new FileReader(this.path);
        BufferedReader lerHeader = new BufferedReader(fr);
        
        Descriptografar dc = new Descriptografar();
        dc.descriptografar(lerHeader.readLine());
        lerHeader.close();
        if(dc.getTexto().equals(key)){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void run() {
        try {
            decode();
        } catch (IOException ex) {
            Logger.getLogger(ImgDecode.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
