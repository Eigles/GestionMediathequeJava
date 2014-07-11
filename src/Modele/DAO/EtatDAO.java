/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modele.DAO;


import Modele.Etat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author martineau.cdi01
 */
public class EtatDAO extends DAO<Etat>{
    
     public EtatDAO(Connection conn){
        super(conn);
          
    }
     
     public EtatDAO(){
         super();
         
     }

    @Override
    public boolean create(Etat obj) {
             
        String createQuery = "INSERT INTO ETAT (libelle_Etat)  VALUES (?)";
        try {
            
            PreparedStatement pstmt = connexion.prepareStatement(createQuery);
            pstmt.setString(1, obj.getLibelle());
            pstmt.executeUpdate();
           
        } catch (SQLException ex) {
            Logger.getLogger(MediaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return true;
    }

    @Override
    public boolean delete(int id) {
         
        String deleteQuery = "DELETE FROM ETAT WHERE id_Etat = ? ";
        try {
            
            PreparedStatement pstmt = connexion.prepareStatement(deleteQuery);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
           
        } catch (SQLException ex) {
            Logger.getLogger(MediaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return true;
    }
    

    @Override
    public boolean update(Etat obj) {
        
          String updateQuery = "UPDATE ETAT SET libelle_Etat = ? WHERE id_Etat = ?";
        try {
            
            PreparedStatement pstmt = connexion.prepareStatement(updateQuery);
            pstmt.setString(1, obj.getLibelle());
            pstmt.setInt(2, obj.getIdEtat());
            pstmt.executeUpdate();
           
        } catch (SQLException ex) {
            
            Logger.getLogger(MediaDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        return true;
    }

    @Override
    public Etat find(int id) {
        
        String updateQuery = "SELECT * FROM ETAT WHERE id_Etat = ?";
        Etat tm = null;
        
        try {
            
            PreparedStatement pstmt = connexion.prepareStatement(updateQuery);
            pstmt.setInt(1, id);
            resultSet = pstmt.executeQuery();
            
            if(resultSet.first()){
                tm = new Etat(resultSet.getInt(1),resultSet.getString(2));
            }
           
        } catch (SQLException ex) {
            
            Logger.getLogger(MediaDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        }/*finally {
            
            fermeturesSilencieuses( pstmt, connexion );
        }*/
        return tm;
    }

   
    public ArrayList<Etat> findAll() {
        
        ArrayList<Etat> list = new ArrayList();
        String updateQuery = "SELECT * FROM ETAT ";
        Etat etat;
        
        try {
           
            PreparedStatement pstmt = connexion.prepareStatement(updateQuery);
            resultSet = pstmt.executeQuery();
            
            while (resultSet.next()){
                etat = new Etat(resultSet.getInt(1),resultSet.getString(2));
                list.add(etat);
            }
           
        } catch (SQLException ex) {
            
            Logger.getLogger(MediaDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        }        
        return list;
    }
    
}
