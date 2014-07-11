/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modele.DAO;

import Modele.Ville;
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
public class VilleDAO extends DAO<Ville>{
    
    public VilleDAO(Connection conn){
        super(conn);
        
    }
    
    public VilleDAO (){
        super();
    }
    

    @Override
    public boolean create(Ville obj) {
       
        String create="INSERT INTO VILLE(nom_Ville, cp_Ville) VALUES (?,?)";
        try {
            
            PreparedStatement pstmt = connexion.prepareStatement(create);
            
            pstmt.setString(1, obj.getNomVille());
            pstmt.setString(2, obj.getCp());
                    
            pstmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(MediaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return true;
    }
    

    @Override
    public boolean delete(int id) {
       String deleteQuery = "DELETE FROM VILLE WHERE id_Ville = ? ";
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
    public boolean update(Ville obj) {
       String updateQuery = "UPDATE VILLE SET nom_Ville = ?, cp_Ville = ? WHERE id_Ville = ?";
        try {
            
            PreparedStatement pstmt = connexion.prepareStatement(updateQuery);
            pstmt.setString(1, obj.getNomVille());
            pstmt.setString(2, obj.getCp());
            pstmt.setInt(3, obj.getIdVille());
            pstmt.executeUpdate();
           
        } catch (SQLException ex) {
            
            Logger.getLogger(MediaDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        return true;
    }

    @Override
    public Ville find(int id) {
         
        String updateQuery = "SELECT * FROM VILLE WHERE id_Ville = ?";
        Ville ville = null;
        
        try {
            
            //connexion = getConnection();
            PreparedStatement pstmt = connexion.prepareStatement(updateQuery);
            pstmt.setInt(1, id);
            resultSet = pstmt.executeQuery();
            
            if(resultSet.first()){
                ville = new Ville(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3));
            }
           
        } catch (SQLException ex) {
            
            Logger.getLogger(MediaDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        return ville;
    }

   
    public ArrayList<Ville> findAll() {
        ArrayList<Ville> list = new ArrayList();
        String updateQuery = "SELECT * FROM VILLE ";
        Ville ville;
        
        try {
            
            PreparedStatement pstmt = connexion.prepareStatement(updateQuery);
            resultSet = pstmt.executeQuery();
            
            while (resultSet.next()){
                ville = new Ville(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3));
                list.add(ville);
            }
           
        } catch (SQLException ex) {
            
            Logger.getLogger(MediaDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        return list;
    }
    
    
    public ArrayList<Ville> findAllCP(String cp) {
        ArrayList<Ville> list = new ArrayList();
        String updateQuery = "SELECT * FROM VILLE WHERE cp_Ville = ?";
        Ville ville;
        
        try {
            
            PreparedStatement pstmt = connexion.prepareStatement(updateQuery);
            pstmt.setString(1, cp);
            resultSet = pstmt.executeQuery();
            
            while (resultSet.next()){
                ville = new Ville(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3));
                list.add(ville);
            }
           
        } catch (SQLException ex) {
            
            Logger.getLogger(MediaDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        return list;
    }
    
}
