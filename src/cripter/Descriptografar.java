/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cripter;

/**
 *
 * @author Bruno Haveroth
 */
public class Descriptografar {
   
    private String textoDescript;
    
    public Descriptografar()
    {
        textoDescript="";
    }
    
    public void descriptografar(String text)
    {
        String S_letra = "", S_letraChave = "", S_chave = "", S_texto = "", S_inverter = "";
        char  C_letra, C_chave;
        int I_numero, I_chave = 0;
        int x1= 0;
     
           text = inverte(text);
           S_chave = pulaLetra(text, 1);
           text = pulaLetra(text, 2);    
           for (int x=1; x<=text.length();x++)
           {
               S_letra = text.substring(x1, x); 
               S_letraChave = S_chave.substring(x1, x);
               
               C_chave = S_letraChave.charAt(0);
               I_chave = (int) C_chave;
               I_chave -= 90;          
               
               C_letra = S_letra.charAt(0);
               I_numero = (int) C_letra;
                             
               I_numero = I_numero - (I_chave) ;
               C_letra = (char) I_numero;
               this.textoDescript=this.textoDescript+C_letra;
               x1=x1+1;
           }
            
    }
    
    public String inverte(String text)
    {
        String novoTexto = "", S_letra= "";
        int x1 = text.length() -1, I_inverter = 0;
        char C_inverter;
        
        S_letra = text.substring(0, 1);
        C_inverter = S_letra.charAt(0);
        I_inverter = (int) C_inverter;
        I_inverter -= 90;
        if  (I_inverter >= 10)
        {
        
            for (int x=text.length(); x>=2; x--)
            {   
                S_letra = text.substring(x1, x); 
                x1 -= 1;
                novoTexto += S_letra;
               
            }
        }
        else
        {
            x1 = 1;
            for (int x=2; x <= text.length(); x++)
            {   
                S_letra = text.substring(x1, x); 
                x1 += 1;
                novoTexto += S_letra;
               
            }
        }    
        
        
        return novoTexto;
    }        
        
    public String pulaLetra(String text, int num)
    {
        String S_newText ="", S_letra;
        int I_x =0, I_x1 = 1;
         
        if (num == 1)
        {
            for (int x=1; x<= text.length(); x+=2)
            {
                S_letra = text.substring(I_x, x);
                S_newText += S_letra; 
                I_x +=2;
            }
            
        }
        
        if (num == 2)
        {
            for (int x=2; x<= text.length(); x+=2)
            {
                S_letra = text.substring(I_x1, x);
                S_newText += S_letra; 
                I_x1 +=2;
            }
        
        }
        return S_newText;
     }
            
    public String getTexto()
    {
        return this.textoDescript;
    }
    
    public void limpaTexto()
    {
        this.textoDescript = "";
    }
   
    
}
