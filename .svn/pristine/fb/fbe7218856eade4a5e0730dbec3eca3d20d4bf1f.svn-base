/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modele.DAO;

import Modele.TypeMedia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author martineau.cdi01
 */
public class TypeMediaDAO extends DAO<TypeMedia> {
    
   //private Connection connexion = null;
   private PreparedStatement pstmt = null;
   private ResultSet resultSet = null;
         
    public TypeMediaDAO(Connection conn){
        super(conn);          
    
    }
    
     public TypeMediaDAO(){
        super();          
    
    }

    @Override
    public boolean create(TypeMedia obj) {
        
        String createQuery = "INSERT INTO TYPE_MEDIA (libelle_Type)  VALUES (?)";
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
        
       String deleteQuery = "DELETE FROM TYPE_MEDIA WHERE id_TypeMedia = ? ";
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
    public boolean update(TypeMedia obj) {
        String updateQuery = "UPDATE TYPE_MEDIA SET libelle_type = ? WHERE id_TypeMedia = ?";
        try {
            
            PreparedStatement pstmt = connexion.prepareStatement(updateQuery);
            pstmt.setString(1, obj.getLibelle());
            pstmt.setInt(2, obj.getIdTypeMedia());
            pstmt.executeUpdate();
           
        } catch (SQLException ex) {
            
            Logger.getLogger(MediaDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        return true;
    }
    

    @Override
    public TypeMedia find(int id) {
        
        String updateQuery = "SELECT * FROM TYPE_MEDIA WHERE id_TypeMedia = ?";
        TypeMedia tm = null;
        
        try {
            
            //connexion = getConnection();
            PreparedStatement pstmt = connexion.prepareStatement(updateQuery);
            pstmt.setInt(1, id);
            resultSet = pstmt.executeQuery();
            
            if(resultSet.first()){
                tm = new TypeMedia(resultSet.getInt(1),resultSet.getString(2));
            }
           
        } catch (SQLException ex) {
            
            Logger.getLogger(MediaDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        }
                
        return tm;
    }

   
    public ArrayList<TypeMedia> findAll() {
        
        ArrayList<TypeMedia> list = new ArrayList();;
        String updateQuery = "SELECT * FROM TYPE_MEDIA ";
        TypeMedia tm;
        
        try {
            
            PreparedStatement pstmt = connexion.prepareStatement(updateQuery);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()){
                tm = new TypeMedia(resultSet.getInt(1),resultSet.getString(2));
                list.add(tm);
            }
           
        } catch (SQLException ex) {
            
            Logger.getLogger(MediaDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        return list;
        
    }   
    public boolean existerType(String libelleType) throws ClassNotFoundException, SQLException
    {
        boolean resultat = false;
        ResultSet rs = null;
        try
        {
            String req = "SELECT *  FROM TYPE_MEDIA where libelle_Type = '"+libelleType+"' ";
            Statement stmt = connexion.createStatement();
            rs = stmt.executeQuery(req);
            if(rs.next()) resultat = true;
            else resultat = false;
        }
        catch(Exception e)
        {
             System.out.println("erreur classe TypeMediaDAO fonction existeType()");
        }
        return resultat;
    }
}
    


