/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cripter;

/**
 *
 * @author Bruno Haveroth
 */
public class Criptografar {
    private String texto;
    
    
    public Criptografar()
    {
        this.texto="";
    }
    public void criptografar(String text)
    {
        String S_letra;
        char  C_letra, C_chave;
        int I_numero, I_inverter;
        int x1= 0, I_nRand, I_chave;
          
           for (int x=1; x<=text.length();x++)
           {   
               /*Randomiza um numero*/
               I_nRand = 5+(int)( 6*Math.random() );              
               /**/
               
               I_chave = I_nRand + 90;
               C_chave = (char) I_chave;
               
               
               S_letra = text.substring(x1, x);
               C_letra = S_letra.charAt(0);
               I_numero = (int) C_letra;
               I_numero = I_numero + I_nRand ;
               C_letra = (char) I_numero;
               
               this.texto += C_chave;
               this.texto += C_letra;
               
               x1=x1+1;
               
           }
           I_inverter = 0+(int)( 21*Math.random() );
           if (I_inverter >= 10)
           {
                I_inverter +=90;
                C_letra = (char) I_inverter;
                this.texto = C_letra + inverte(this.texto);
           }
           else 
           {
               I_inverter +=90;
               C_letra = (char) I_inverter;
               this.texto = C_letra + this.texto;
           }
           //this.texto=("lol: "+(char)97);
          
        
    }
    
    public String inverte(String text)
    {
        String novoTexto = "", S_letra= "";
        int x1 = text.length() -1;
        
        for (int x=text.length(); x>=1; x--)
        {   
           S_letra = text.substring(x1, x); 
           x1 -= 1;
           novoTexto += S_letra;
               
        }
        return novoTexto;
    }        
    
    public String getTexto()
    {
        return this.texto;
        
    }
    
    public void limpaTexto()
    {
        this.texto = "";
    }
    
}
