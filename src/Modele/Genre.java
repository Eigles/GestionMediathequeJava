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
public class Genre {
    
    private int idGenre;
    private String libelle;
    private String code;
    
    public Genre(int id, String code, String libelle){
        
        this.code=code;
        this.idGenre=id;
        this.libelle = libelle;
    }

    public Genre() {
     
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    
    public int getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(int idGenre) {
        this.idGenre = idGenre;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return libelle;
    }
    
}
