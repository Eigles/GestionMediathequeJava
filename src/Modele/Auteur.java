/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modele;

import java.sql.Date;

/**
 *
 * @author martineau.cdi01
 */
public class Auteur {
    
    private int idAuteur;
    private String nom;
    private String prenom;
    private String description;
    private Date dateNaissance;
    
    public Auteur(int id, String nom, String prenom, String description, Date dateNaissance){
        
        this.idAuteur = id;
        this.nom = nom;
        this.prenom = prenom;
        this.description = description;
        this.dateNaissance = dateNaissance;
        
    }
    
    public Auteur(String nom, String prenom)
    {
        this.nom = nom;        
        this.prenom = prenom;   
    }
    
    public Auteur()
    {
    }
        
    public int getIdAuteur() {
        return idAuteur;
    }

    public void setIdAuteur(int idAuteur) {
        this.idAuteur = idAuteur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    @Override
    public String toString() {
        return nom + " " + prenom;
    }
    
    
    
}
