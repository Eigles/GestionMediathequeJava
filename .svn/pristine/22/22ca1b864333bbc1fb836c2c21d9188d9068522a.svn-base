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
public class Media {
    
    private int idMedia;
    private String titre;
    private Auteur auteur;
    private Genre genre;
    private String localisation;
    private Etat etat;
    private TypeMedia typeMedia;
    private String isbn;
    private boolean disponibilite;
    private String lienPhoto;
    private String description;
    private int anneeParution;
    private String resume;
    private String tome;
    private int pages;
    private String format;
    private String maisonEdition;
    
    public Media(int id, String titre, Auteur aut, Genre genre, String loc, Etat etat, String isbn, String lien, TypeMedia typemedia, 
            String description, Boolean dispo, int anneeParution, String resume, String tome, int pages, String format, String maisonEdition){
        
    this.idMedia =  id;  
    this.titre = titre;
    this.auteur = aut;
    this.genre = genre;
    this.localisation = loc;
    this.etat = etat;
    this.isbn = isbn;
    this.lienPhoto = lien;
    this.description=description;
    this.typeMedia = typemedia;
    this.disponibilite = dispo;
    this.anneeParution = anneeParution;
    this.resume = resume;
    this.tome = tome;
    this.pages = pages;
    this.format =format;
    this.maisonEdition = maisonEdition;            
           
    }

    public Media() {
        
    }

    public boolean isDisponibilite() {
        return disponibilite;
    }
    
    public String getDisponibilite() {
        if(this.isDisponibilite()==true)
        return "disponible";
        else return "indisponible";
    }

    public void setDisponibilite(boolean disponibilite) {
        this.disponibilite = disponibilite;
    }
    
     public void setIdMedia(int idMedia) {
        this.idMedia = idMedia;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setAuteur(Auteur auteur) {
        this.auteur = auteur;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setLienPhoto(String lienPhoto) {
        this.lienPhoto = lienPhoto;
    }
    
     public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
     public int getIdMedia() {
        return idMedia;
    }

    public String getTitre() {
        return titre;
    }

    public Auteur getAuteur() {
        return auteur;
    }

    public Genre getGenre() {
        return genre;
    }

    public String getLocalisation() {
        return localisation;
    }

    public Etat getEtat() {
        return etat;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getLienPhoto() {
        return lienPhoto;
    }

    public TypeMedia getTypeMedia() {
        return typeMedia;
    }

    public void setTypeMedia(TypeMedia typeMedia) {
        this.typeMedia = typeMedia;
    }

    public int getAnneeParution() {
        return anneeParution;
    }

    public void setAnneeParution(int anneeParution) {
        this.anneeParution = anneeParution;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getTome() {
        return tome;
    }

    public void setTome(String tome) {
        this.tome = tome;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getMaisonEdition() {
        return maisonEdition;
    }

    public void setMaisonEdition(String maisonEdition) {
        this.maisonEdition = maisonEdition;
    }
    

    @Override
    public String toString() {
        return titre;
    }
    
}
