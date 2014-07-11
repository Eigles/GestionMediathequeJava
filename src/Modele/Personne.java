/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modele;

/**
 *
 * @author zemouri.cdi01
 */
public class Personne {
    private int id_Personne;
    private String nom_Personne;
    private String prenom_Personne;
    private String date_Naissance;
    private String mail_Personne;
    private Ville ville;
    private Boolean administrateur;
    private String telephone;
    private String adresse;
    
        
    public Personne(int id,String nom,String prenom,String date_naissance,String mail,Ville ville,Boolean administrateur,String telephone,String adresse)
    {
        this.id_Personne = id;
        this.nom_Personne = nom;
        this.prenom_Personne = prenom;
        this.date_Naissance = date_naissance;
        this.mail_Personne = mail;
        this.ville = ville; 
        this.administrateur = administrateur;
        this.telephone = telephone;
        this.adresse = adresse;
    }

    
    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Boolean isAdministrateur() {
        return administrateur;
    }

    public void setAdministrateur(Boolean administrateur) {
        this.administrateur = administrateur;
    }

    public Ville getVille() {
        return ville;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }
    

    public int getId_Personne() {
        return id_Personne;
    }

    public void setId_Personne(int id_Personne) {
        this.id_Personne = id_Personne;
    }

    public String getNom_Personne() {
        return nom_Personne;
    }

    public void setNom_Personne(String nom_Personne) {
        this.nom_Personne = nom_Personne;
    }

    public String getPrenom_Personne() {
        return prenom_Personne;
    }

    public void setPrenom_Personne(String prenom_Personne) {
        this.prenom_Personne = prenom_Personne;
    }

    public String getDate_Naissance() {
        return date_Naissance;
    }

    public void setDate_Naissance(String date_Naissance) {
        this.date_Naissance = date_Naissance;
    }

    public String getMail_Personne() {
        return mail_Personne;
    }

    public void setMail_Personne(String mail_Personne) {
        this.mail_Personne = mail_Personne;
    }
    
}
