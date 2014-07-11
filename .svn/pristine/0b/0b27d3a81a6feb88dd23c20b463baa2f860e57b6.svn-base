/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modele.DAO;

import Modele.Abonne;
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
public class AbonneDAO extends DAO<Abonne>{
    
    public AbonneDAO(Connection conn){
        super(conn);
    }
    
     public AbonneDAO(){
        super();
    }
    

    @Override
    public boolean create(Abonne obj) {
        String create="INSERT INTO ABONNES(id_Personne, date_Fin_Abonnement, confirme) VALUES (?,?,?)";
        try {

            PreparedStatement pstmt = connexion.prepareStatement(create);
            
            pstmt.setInt(1, obj.getPersonne().getId_Personne());
            pstmt.setDate(2, obj.getDateFinAbonnement());
            pstmt.setBoolean(3, obj.isConfirme());
                        
            pstmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(MediaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return true;
    }
    

    @Override
    public boolean delete(int id) {
        
        String deleteQuery = "DELETE FROM ABONNES WHERE id_Abonnes = ? ";
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
    public boolean update(Abonne obj) {
        String updateQuery = "UPDATE ABONNES SET id_Personne = ? , "
                    + "date_Fin_Abonnement = ? ,"
                    + "confirme = ? "
                    + "WHERE id_Abonne = ?";
        try {
            
            PreparedStatement pstmt = connexion.prepareStatement(updateQuery);
            pstmt.setInt(1, obj.getPersonne().getId_Personne());
            pstmt.setDate(2, obj.getDateFinAbonnement());
            pstmt.setBoolean(3, obj.isConfirme());
            pstmt.setInt(4, obj.getIdAbonne());
            pstmt.executeUpdate();
           
        } catch (SQLException ex) {
            
            Logger.getLogger(MediaDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        return true;
    }

    @Override
    public Abonne find(int id) {
        
        String updateQuery = "SELECT * FROM ABONNES WHERE id_Abonne = ?";
        Abonne abonne = null;
        
        try {
            
            PreparedStatement pstmt = connexion.prepareStatement(updateQuery);
            pstmt.setInt(1, id);
            resultSet = pstmt.executeQuery();
            
            PersonneDAO pdao = new PersonneDAO(connexion);
                        
            if(resultSet.first()){
                
                
               abonne = new Abonne(resultSet.getInt(1),
                       pdao.find(resultSet.getInt(2)),
                       resultSet.getBoolean(4),
                       resultSet.getDate(5));
                       
                       }
           
        } catch (SQLException ex) {
            
            Logger.getLogger(MediaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return abonne;
        
    }

  
    public ArrayList<Abonne> findAll() {
        
        ArrayList<Abonne> list = new ArrayList();
        String updateQuery = "SELECT * FROM ABONNES ";
        Abonne abonne;
        
        try {
            
            PreparedStatement pstmt = connexion.prepareStatement(updateQuery);
            resultSet = pstmt.executeQuery();
            
            PersonneDAO pdao = new PersonneDAO(connexion);
              
            
            while (resultSet.next()){
                
               abonne = new Abonne(resultSet.getInt(1),
                       pdao.find(resultSet.getInt(2)),
                       resultSet.getBoolean(4),
                       resultSet.getDate(5));
                       
                list.add(abonne);
  
            }
           
        } catch (SQLException ex) {
            
            Logger.getLogger(MediaDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        return list;
    }
    
}
