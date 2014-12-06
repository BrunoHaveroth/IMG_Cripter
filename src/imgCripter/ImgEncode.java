/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imgCripter;

import cripter.Criptografar;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import view.FrmCodifica;

/**
 *
 * @author Bruno Haveroth
 */
public class ImgEncode implements Runnable{
    private String path;
    private String pathEnd;
    private FrmCodifica frmC;
    private Integer pos;

    public Integer getPos() {
        return pos;
    }

    public void setPos(Integer pos) {
        this.pos = pos;
    }

    public FrmCodifica getFrmC() {
        return frmC;
    }

    public void setFrmC(FrmCodifica frmC) {
        this.frmC = frmC;
    }

    public ImgEncode() {
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPathEnd() {
        return pathEnd;
    }

    public void setPathEnd(String pathEnd) {
        this.pathEnd = pathEnd;
    }
    
    public void encode() throws IOException{        
        BufferedImage Image = ImageIO.read(new File(path));
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(Image, "jpg", bos);
        bos.flush();
        
        byte[] imgBytes = bos.toByteArray();
        String imgText = "";
        BufferedWriter gravaTxt = new BufferedWriter(new FileWriter(pathEnd+".imgc", true));
        
        Criptografar cp = new Criptografar();
        String key = getKey();
        
        cp.criptografar(key);
        gravaTxt.write(cp.getTexto());
        cp.limpaTexto();
        gravaTxt.newLine();
        
        cp.criptografar(Integer.toString(imgBytes.length));
        gravaTxt.write(cp.getTexto());
        cp.limpaTexto();
        gravaTxt.newLine();
        
        Integer progress;
        for (int i =0; i<imgBytes.length; i++){
            cp.criptografar(Byte.toString(imgBytes[i]));
            imgText = cp.getTexto();
            cp.limpaTexto();
            
            gravaTxt.write(imgText);
            gravaTxt.newLine();
            
            progress = ((i+1) * 100)/imgBytes.length;
            this.frmC.alterStatus("Criptografando "+progress+"%", pos);
            //System.out.println(""+i+" de "+imgBytes.length);
        }    
        gravaTxt.close();
        frmC.addKey(key, pos);
        frmC.alterBar();
    }
    
    public String getKey(){
        String key = "";
        Random gerador = new Random();
        for(int i = 0; i < 32; i++){
            Integer type = gerador.nextInt(3);
        
            if(type == 0){
                key += Integer.toString(gerador.nextInt(10));

            }else if(type == 1){
                int keyMA =  gerador.nextInt(91);
                while(keyMA < 41){
                    keyMA =  gerador.nextInt(91);
                }
                key +=(char)keyMA;
            }else{
                int keyMI =  gerador.nextInt(123);
                while(keyMI < 97){
                    keyMI =  gerador.nextInt(123);
                }
                key +=(char)keyMI;
            }
        }
        
        return key;
    }

    @Override
    public void run() {
        try {
            encode();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
}
