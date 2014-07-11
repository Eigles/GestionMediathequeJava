/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package calogiciel;

import Modele.Auteur;
import Modele.DAO.AuteurDAO;
import Modele.DAO.TypeMediaDAO;
import Modele.Media;
import Modele.TypeMedia;
import java.io.IOException;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import webservicegoogle.GoogleWebService;
import webservicegoogle.ObjectBook;

/**
 *
 * @author zemouri.cdi01
 */
public class Onload implements Runnable{
    private String isbn=null;
    ObjectBook ob;
    GoogleWebService gws;
    PanelMedia panel;
    ImageIcon valid,invalid;
    public Onload(PanelMedia formulaire) {
         this.panel = formulaire;
         valid = new ImageIcon("resources/valid.png");
         invalid = new ImageIcon("resources/invalid.png");
    }
/*Cdi321321*/
    @Override
    public void run() {
        try {
            gws = new GoogleWebService("toto","");
            ob = new ObjectBook();
            ob = gws.getResult(panel.getChampIsbn().getText());
            getChamps();
        } catch (IOException ex) {
            //Logger.getLogger(Onload.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur proxy");
        }
            
    }
    
    public void getChamps()
    {
        if (!(ob==null))
                    {
                        Media m = new Media();
                        m.setTitre(ob.getTitre());  
                        m.setAuteur(new Auteur(ob.getAuteurNom(),ob.getAuteurPrenom())); 
                        m.setTypeMedia(new TypeMedia(0,ob.getType())); 
                        m.setDescription(ob.getResume());
                        m.setIsbn(ob.getIsbn13());
                        String annee = ob.getDatePublication().substring(0, 4);
                        m.setAnneeParution(Integer.parseInt(annee));
                        m.setMaisonEdition(ob.getMaisonEdition());
                        m.setPages(ob.getNbPage());
                        System.out.println("auteur : "+m.getAuteur());
                        panel.getChampTitreMedia().setText(m.getTitre());                    

                        panel.getChampAuteur().removeAll();
                        List<Auteur> listeAuteur = (new AuteurDAO()).findAll();
                        boolean trouveAuteur = false;
                        for(Auteur obj : listeAuteur)
                        {
                            panel.getChampAuteur().addItem(obj);
                            if(obj.toString().equals(m.getAuteur().toString()))
                            {
                                panel.getChampAuteur().setSelectedItem(obj);
                                trouveAuteur = true;
                            }
                        }
                        if(trouveAuteur==false) ajouterAuteur(m.getAuteur());
                        panel.getChampAuteur().repaint();
                        panel.getChampAuteur().revalidate();
                        
                        panel.getChampType().removeAll();
                        List<TypeMedia> listeType = (new TypeMediaDAO()).findAll();
                        for(TypeMedia obj : listeType)
                        {
                            panel.getChampType().addItem(obj);
                            if(obj.toString().equals(m.getTypeMedia().toString()))
                            {
                                panel.getChampType().setSelectedItem(obj);
                            }
                        }
                        panel.getChampType().repaint();
                        panel.getChampType().revalidate();
                        
                        panel.getChampResume().setText(m.getDescription());                        
                        panel.getChampAnneeParution().setText(annee);
                        panel.getChampMaisonEdition().setText(m.getMaisonEdition());
                        panel.getChampNbPages().setText(""+m.getPages());
                        String image = ob.getImage();
                        System.out.println("image : "+image);
                        panel.getLabelEnter().setIcon(valid);
                        //copie dans un repertoire cible
                        /*FileChannel source = new FileInputStream(champPhoto.getText()).getChannel();
                        FileChannel dest = new FileOutputStream("tutu.png").getChannel();
                        source.transferTo(0, source.size(), dest);*/
                    }
                    else 
                    {
                        // affichage du message d'erreur d'isbn
                        panel.getLabelEnter().setIcon(invalid);
                        JOptionPane d = new JOptionPane();
                        d.showMessageDialog(panel.getFenetre(), "Cet isbn n'est pas valide.", "information", JOptionPane.INFORMATION_MESSAGE);                 
                    }
    }

    private void ajouterAuteur(Auteur a) 
    {
        AuteurDAO auteurDao = new AuteurDAO();
        Auteur auteur = new Auteur();
        auteur.setPrenom(a.getPrenom());
        auteur.setNom(a.getNom());
        auteurDao.create(auteur);
        
        // sélection dans la liste déroulante du nouvel auteur ajouté
        panel.getChampAuteur().removeAll();
        List<Auteur> listeAuteur = (new AuteurDAO()).findAll();
        for(Auteur obj : listeAuteur)
        {
            panel.getChampAuteur().addItem(obj);
            if(obj.toString().equals(auteur.toString()))
            {
                panel.getChampAuteur().setSelectedItem(obj);
            }
        }            
    }
}
