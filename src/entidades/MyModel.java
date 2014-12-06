/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author mizereke
 */
public class MyModel extends AbstractTableModel{
     
    private ArrayList<Imagem> dados;
    private String[] colunas = {"Nome" ,"Chave","Status"};
     
    public MyModel(){
        dados = new ArrayList<Imagem>();
    }
     
    public void addRow(Imagem i){
        this.dados.add(i);
        this.fireTableDataChanged();
    }
 
    public String getColumnName(int num){
        return this.colunas[num];
    }
 
    @Override
    public int getRowCount() {
        return dados.size();
    }
 
    @Override
    public int getColumnCount() {
        return colunas.length;
    }
 
    @Override
    public Object getValueAt(int linha, int coluna) {
        switch(coluna){
            case 0: return dados.get(linha).getNome();
            case 1: return dados.get(linha).getChave();
            case 2: return dados.get(linha).getStatus();
        }  
        return null;
    }
    
    public void removeRow(int linha){
        this.dados.remove(linha);
        this.fireTableRowsDeleted(linha, linha);
    }
    
    public Imagem get(int linha){
        return this.dados.get(linha);
    }
    
    public boolean isCellEditable(int linha, int coluna) {
        return false;
    }
    
    public void setValueAt(Object valor, int linha, int coluna){
        if( valor == null) return;
         
        switch(coluna){
            case 0:  dados.get(linha).setNome( (String) valor);break;
            case 1:  dados.get(linha).setChave((String) valor);break;
            case 2:  dados.get(linha).setStatus((String) valor);break;
        }
        this.fireTableRowsUpdated(linha, linha);
    }
    
}
