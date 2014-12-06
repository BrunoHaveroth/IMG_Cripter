/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;

/**
 *
 * @author Bruno Haveroth
 */
public class DAO {
    
    private ObjectContainer banco;
    private String file = "DbImgCripter.db4o";
    private static DAO dao;
    
    private DAO(){
        banco = Db4oEmbedded.openFile("DbImgCripter.db4o");
    }
    
    public static DAO getDAO(){
        if (dao == null){
            dao = new DAO();
        }
        return dao;
    }
    
    public ObjectSet buscar(Object objeto){
        ObjectSet result = banco.queryByExample(objeto);
        return result;
    }
    
    
    public void gravar(Object objeto){
        banco.store(objeto);
    }
    
    public void deletar(Object objeto){
        banco.delete(objeto);
        
    }
    
}
