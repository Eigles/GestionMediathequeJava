/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modele;

/**
 *
 * @author martineau.cdi01
 */
public class Emprunt {
    
    private int idEmprunt;
    private String dateEmprunt, dateRetour;
    private Personne personne;
    private Media media;
    private String etat;
    
    public Emprunt(int idEmprunt, String emprunt, Personne pers, Media med, String retour, String etat){
        this.idEmprunt = idEmprunt;
        this.dateEmprunt = emprunt;
        this.dateRetour = retour;
        this.etat = etat;
        this.personne = pers;
        this.media = med;
    }

    public String getDateEmprunt() {
        return dateEmprunt;
    }

    public void setDateEmprunt(String dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public String getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(String dateRetour) {
        this.dateRetour = dateRetour;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public int getIdEmprunt() {
        return idEmprunt;
    }

    public void setIdEmprunt(int idEmprunt) {
        this.idEmprunt = idEmprunt;
    }
    
    
}
