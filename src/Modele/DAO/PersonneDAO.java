/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modele.DAO;

import Modele.Personne;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zemouri.cdi01
 */
public class PersonneDAO extends DAO<Personne>{
    public PersonneDAO(Connection connexion)
    {
        super(connexion);
    }
    
    public PersonneDAO()
    {
        super();
    }

    @Override
    public boolean create(Personne obj) {
        String createQuery = "INSERT INTO PERSONNE (nom_Personne, prenom_Personne, adresse_Personne, id_Ville, administrateur, date_Naissance, mail_Personne)  VALUES (?,?,?,?,?,?,?)";
        int idVille=0;
       
        try {
            
            PreparedStatement pstmt = connexion.prepareStatement(createQuery,PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, obj.getNom_Personne());
            pstmt.setString(2, obj.getPrenom_Personne());
            pstmt.setString(3, obj.getAdresse());
            //teste si la ville est null
            if (obj.getVille()!=null)
             pstmt.setInt(4, obj.getVille().getIdVille());
            else pstmt.setNull(4, idVille);
            pstmt.setBoolean(5, false);
            
            pstmt.setString(6, obj.getDate_Naissance());
            pstmt.setString(7, obj.getMail_Personne());
            pstmt.executeUpdate();
            ResultSet clefs = pstmt.getGeneratedKeys();
            if(clefs.next())
            {
               obj.setId_Personne((int)(long)clefs.getObject(1));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(MediaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return true;
    }

    
    @Override
    public boolean delete(int id) {
       
        String deleteQuery = "DELETE FROM PERSONNE WHERE id_Personne = ? ";
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
    public boolean update(Personne obj) {
        String updateQuery = "UPDATE PERSONNE SET nom_Personne = ?, prenom_Personne = ?, date_naissance= ?, mail_Personne = ?, tel_Personne = ?, adresse_Personne = ?, administrateur =?, id_Ville= ?  WHERE id_Personne = ?";
        int idVille =0;
                
        
        try {
            
            PreparedStatement pstmt = connexion.prepareStatement(updateQuery);
            pstmt.setString(1, obj.getNom_Personne());
            pstmt.setString(2, obj.getPrenom_Personne());
            pstmt.setString(3, obj.getDate_Naissance());
            pstmt.setString(4, obj.getMail_Personne());
            pstmt.setString(5, obj.getTelephone());
            pstmt.setString(6, obj.getAdresse());
            pstmt.setBoolean(7, obj.isAdministrateur());
            if (obj.getVille()!=null)
             pstmt.setInt(8, obj.getVille().getIdVille());
            else pstmt.setNull(8, idVille);
            pstmt.setInt(9, obj.getId_Personne());
            pstmt.executeUpdate();
           
        } catch (SQLException ex) {
            
            Logger.getLogger(MediaDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return true;
    }
    

    @Override
    public Personne find(int id) {
        
        String updateQuery = "SELECT * FROM PERSONNE WHERE id_Personne = ?";
        Personne pers = null;
        
        try {
            
            PreparedStatement pstmt = connexion.prepareStatement(updateQuery);
            pstmt.setInt(1, id);
            resultSet = pstmt.executeQuery();
            VilleDAO vdao = new VilleDAO(connexion);
            
            if(resultSet.first()){
               pers = new Personne(resultSet.getInt(1),
                       resultSet.getString(2),
                       resultSet.getString(3),
                       resultSet.getString(4),
                       resultSet.getString(5),
                       vdao.find(resultSet.getInt(9)),
                       resultSet.getBoolean(8),
                       resultSet.getString(6),
                       resultSet.getString(7));
              }
           
        } catch (SQLException ex) {
            
            Logger.getLogger(MediaDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        return pers;
    }
   
    
    
    public ArrayList<Personne> findAllSympathisant(int numeroPage, String recherche) {
        ArrayList<Personne> list = new ArrayList();
        int min = (numeroPage-1)*30;
        int nblignes = 30;
       
        String updateQuery = "SELECT * FROM SYMPATHISANT WHERE (prenom_personne LIKE ? OR nom_personne LIKE ?) ORDER BY nom_Personne LIMIT ?,?";
        Personne pers;
        
        try {
            
            PreparedStatement pstmt = connexion.prepareStatement(updateQuery);
            pstmt.setString(1, "%"+recherche+"%");
            pstmt.setString(2, "%"+recherche+"%");
            pstmt.setInt(3, min);
            pstmt.setInt(4, nblignes);
            resultSet = pstmt.executeQuery();
            VilleDAO vdao = new VilleDAO(connexion);
            
            while (resultSet.next()){
                 pers = new Personne(resultSet.getInt(1),
                       resultSet.getString(2),
                       resultSet.getString(3),
                       resultSet.getString(4),
                       resultSet.getString(5),
                       vdao.find(resultSet.getInt(9)),
                       resultSet.getBoolean(8),
                       resultSet.getString(6),
                       resultSet.getString(7));
                 
                 list.add(pers);
              }
           
        } catch (SQLException ex) {
            
            Logger.getLogger(MediaDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        }
                
        return list;
        
    } 
    
    public int compterLignesRecherche(String recherche){
        
        int nbLignes=0;
        
        String updateQuery = "SELECT COUNT(*) AS nblignes FROM SYMPATHISANT WHERE (prenom_personne LIKE ? OR nom_personne LIKE ?)";
                       
        
        try {
           PreparedStatement pstmt = connexion.prepareStatement(updateQuery);
            pstmt.setString(1, "%"+recherche+"%");
            pstmt.setString(2, "%"+recherche+"%");
            resultSet = pstmt.executeQuery();
           
                       
            resultSet = pstmt.executeQuery();
            resultSet.next();
            nbLignes = resultSet.getInt("nbLignes");
        } catch (SQLException ex) {
            
            Logger.getLogger(MediaDAO.class.getName()).log(Level.SEVERE, null, ex);  
   
        }
        
        return nbLignes;
    }
    
}
