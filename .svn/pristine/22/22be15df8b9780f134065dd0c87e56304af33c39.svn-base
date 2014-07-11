/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modele.DAO;

import Modele.Genre;
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
public class GenreDAO extends DAO<Genre>{

            
    public GenreDAO(Connection conn){
        super(conn);
          
    }
    
     public GenreDAO(){
        super();
     }

    @Override
    public boolean create(Genre obj) {
        
        String createQuery = "INSERT INTO GENRE (libelle_Genre, code_Genre)  VALUES (?,?)";
        try {
            
            PreparedStatement pstmt = connexion.prepareStatement(createQuery);
            pstmt.setString(1, obj.getLibelle());
            pstmt.setString(2, obj.getCode());
            pstmt.executeUpdate();
           
        } catch (SQLException ex) {
            Logger.getLogger(MediaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return true;
    }

    @Override
    public boolean delete(int id) {
        
       String deleteQuery = "DELETE FROM GENRE WHERE id_Genre = ? ";
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
    public boolean update(Genre obj) {
        String updateQuery = "UPDATE GENRE SET libelle_genre = ? AND code_Genre = ? WHERE id_Genre = ?";
        try {
            
            PreparedStatement pstmt = connexion.prepareStatement(updateQuery);
            pstmt.setString(1, obj.getLibelle());
            pstmt.setString(2, obj.getCode());
            pstmt.setInt(3, obj.getIdGenre());
            pstmt.executeUpdate();
           
        } catch (SQLException ex) {
            
            Logger.getLogger(MediaDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        return true;
    }
    

    @Override
    public Genre find(int id) {
        
        String updateQuery = "SELECT * FROM GENRE WHERE id_Genre = ?";
        Genre genre = null;
        
        try {
            
            PreparedStatement pstmt = connexion.prepareStatement(updateQuery);
            pstmt.setInt(1, id);
            resultSet = pstmt.executeQuery();
            
            if(resultSet.first()){
                genre = new Genre(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3));
            }
           
        } catch (SQLException ex) {
            
            Logger.getLogger(MediaDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        return genre;
    }

    
  
    public ArrayList<Genre> findAll() {
        
        ArrayList<Genre> list = new ArrayList();
        String updateQuery = "SELECT * FROM GENRE ";
        Genre genre;
        
        try {
            
            
            PreparedStatement pstmt = connexion.prepareStatement(updateQuery);
            resultSet = pstmt.executeQuery();
            
            while (resultSet.next()){
                genre = new Genre(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3));
                list.add(genre);
            }
           
        } catch (SQLException ex) {
            
            Logger.getLogger(MediaDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        }  
        
        return list;
        
    }

    public boolean existerGenreLibelle(String libelleGenre) throws ClassNotFoundException, SQLException
    {
        boolean resultat = false;
        ResultSet rs = null;
        try
        {
            String req = "SELECT *  FROM GENRE where libelle_Genre = '"+libelleGenre+"' ";
            Statement stmt = connexion.createStatement();
            rs = stmt.executeQuery(req);
            if(rs.next()) resultat = true;
            else resultat = false;
        }
        catch(Exception e)
        {
             System.out.println("erreur classe Genre fonction existerGenreLibelle()");
        }
        return resultat;
    }
    
    public boolean existerGenreCode(int codeGenre) throws ClassNotFoundException, SQLException
    {
        boolean resultat = false;
        ResultSet rs = null;
        try
        {
            String req = "SELECT *  FROM GENRE where code_genre = '"+codeGenre+"' ";
            Statement stmt = connexion.createStatement();
            rs = stmt.executeQuery(req);
            if(rs.next()) resultat = true;
            else resultat = false;
        }
        catch(Exception e)
        {
             System.out.println("erreur classe Genre fonction existerGenreCode()");
        }
        return resultat;
    }
}
