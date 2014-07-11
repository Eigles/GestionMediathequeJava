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
public class Adherent {
    
    private int id;
    private String dateAdhesion;
    private Boolean confirme;
    private Personne personne;
    private String finAdhesion;
    
    public Adherent(int id, String adhesion, Boolean confirme, Personne pers, String finAdhesion){
        
        this.id=id;
        this.dateAdhesion = adhesion;
        this.confirme = confirme;
        this.personne = pers;
        this.finAdhesion = finAdhesion;
        
    }

    public Adherent() {
  
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateAdhesion() {
        return dateAdhesion;
    }

    public void setDateAdhesion(String dateAdhesion) {
        this.dateAdhesion = dateAdhesion;
    }

    public Boolean isConfirme() {
        return confirme;
    }

    public void setConfirme(Boolean confirme) {
        this.confirme = confirme;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public String getFinAdhesion() {
        return finAdhesion;
    }

    public void setFinAdhesion(String finAdhesion) {
        this.finAdhesion = finAdhesion;
    }

    @Override
    public String toString() {
        return personne.getNom_Personne() + " " + personne.getPrenom_Personne() ;
    }
    
}
